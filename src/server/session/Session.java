package server.session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Logger;

import server.interfaces.Request;
import server.interfaces.Response;
import server.router.Router;
import shared.logger.MyLogger;

public class Session implements Runnable {

  private static Logger logger = MyLogger.getInstance();
  private static final Router ROUTER = Router.getInstance();

  private Socket socket;

  public Session(Socket socket) {
    this.socket = socket;
  }

  @Override
  public void run() {
    logger.info("New client connected!");

    try (DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      handleConnection(input, output);

    } catch (EOFException e) {
      logger.info("Client disconnected!");
    } catch (IOException e) {
      logger.severe(e.getMessage());
    } finally {
      closeSocket();
    }
  }

  private void handleConnection(DataInputStream input, DataOutputStream output) throws IOException {
    while (!socket.isClosed()) {
      String userInput = input.readUTF();
      logger.info(() -> "Received: " + userInput);

      try {
        Request req = new RequestImpl(userInput);
        Response res = new ResponseImpl();

        logger.info(() -> "Parsed:"
            + "\n\tcommand: " + req.getCommand()
            + "\n\tparams: " + Arrays.toString(req.getParameters())
            + "\n\toptions: " + Arrays.toString(req.getOptions()));

        ROUTER.route(req, res);

        output.writeUTF(res.read());
        logger.info(() -> "Sent: " + res.read());
      } catch (IllegalArgumentException e) {
        output.writeUTF(e.getMessage());
        logger.severe(e.getMessage());
      }
    }
  }

  private void closeSocket() {
    try {
      socket.close();
    } catch (IOException e) {
      logger.severe(e.getMessage());
    } finally {
      logger.info("Socket closed!");
    }
  }

}
