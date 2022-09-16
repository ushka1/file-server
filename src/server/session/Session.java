package server.session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import server.Main;
import server.interfaces.Request;
import server.interfaces.Response;
import server.logger.MyLogger;
import server.router.Router;

public class Session implements Runnable {

  private static final int SOCKET_TIMEOUT = 1 * 60 * 1000;
  private static final Logger logger = MyLogger.getInstance();
  private static final Router router = Router.getInstance();

  private Socket socket;

  public Session(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    logger.info("New client connected!");

    try (DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      socket.setSoTimeout(SOCKET_TIMEOUT);
      handleConnection(input, output);

    } catch (EOFException e) {
      logger.info("Client disconnected!");
    } catch (IOException e) {
      logger.severe(e.getMessage());
      e.printStackTrace();
    } finally {
      closeSocket();
    }
  }

  private void handleConnection(DataInputStream input, DataOutputStream output) throws IOException {
    while (!socket.isClosed()) {

      RequestParser parser = new RequestParser(input);
      parser.receiveInput();

      // XXX
      if (parser.getMethod().equals("EXIT")) {
        Main.kill();
        break;
      }

      RequestImpl.Builder reqBuilder = new RequestImpl.Builder();
      reqBuilder
          .method(parser.getMethod())
          .path(parser.getPath())
          .params(parser.getParams())
          .tempFile(parser.getTempFile());

      // additional request configuration

      Request req = new RequestImpl(reqBuilder);
      Response res = new ResponseImpl(output);

      if (parser.getError() != null) {
        res.setStatusCode(400);
        res.setParam("message", parser.getError());
        res.send();
        continue;
      }

      router.route(req, res);
    }
  }

  private void closeSocket() {
    try {
      socket.close();
    } catch (IOException e) {
      logger.severe(e.getMessage());
      e.printStackTrace();
    } finally {
      logger.info("Socket closed!");
    }
  }

}
