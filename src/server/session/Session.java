package server.session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
  @SuppressWarnings({ "java:S106", "java:S125" })
  public void run() {
    System.out.println("New client connected!");

    try (DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      while (true) {
        String userInput = input.readUTF();
        System.out.println("Received: " + userInput);

        Request req = new RequestImpl(userInput);
        Response res = new ResponseImpl();

        if (req.isTerminating())
          break;

        ROUTER.route(req, res);

        output.writeUTF(res.read());
        System.out.println("Sent: " + res.read());
      }

    } catch (IOException e) {
      System.out.println("Socket encountered error: " + e.getMessage());
    }

    System.out.println("Client disconnected!");
  }

}
