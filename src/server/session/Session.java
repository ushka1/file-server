package server.session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import server.interfaces.Request;
import server.interfaces.Response;
import server.router.Router;

public class Session implements Runnable {

  private static final Router ROUTER = Router.getInstance();

  private Socket socket;

  public Session(Socket socket) {
    this.socket = socket;
  }

  @Override
  @SuppressWarnings({ "java:S106", "java:S1192" })
  public void run() {
    System.out.println("New client connected!");

    try (DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      handleConnection(input, output);

    } catch (EOFException e) {
      System.out.println("Client disconnected!");
    } catch (IOException e) {
      System.out.println("ERROR [socket]: " + e.getMessage());
    } finally {
      closeSocket();
    }
  }

  @SuppressWarnings({ "java:S106" })
  private void handleConnection(DataInputStream input, DataOutputStream output) throws IOException {

    while (!socket.isClosed()) {
      String userInput = input.readUTF();
      System.out.println("Received: " + userInput);

      try {
        Request req = new RequestImpl(userInput);
        Response res = new ResponseImpl();

        // log with params etc

        ROUTER.route(req, res);

        output.writeUTF(res.read());
        System.out.println("Sent: " + res.read());
      } catch (IllegalArgumentException e) {
        output.writeUTF(e.getMessage());
        System.out.println("ERROR [socket]: " + e.getMessage());
      }
    }
  }

  @SuppressWarnings({ "java:S106" })
  private void closeSocket() {
    try {
      socket.close();
    } catch (IOException e) {
      System.out.println("ERROR [socket]: " + e.getMessage());
    } finally {
      System.out.println("Socket closed!");
    }
  }

}
