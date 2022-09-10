package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import server.config.Constants;
import server.session.Session;

public class Main {

  @SuppressWarnings({ "java:S2189", "java:S106" })
  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(
        Constants.PORT,
        Constants.MAX_PENDING_CONNECTIONS,
        InetAddress.getByName(Constants.ADDRESS))) {

      System.out.println("Server started!");

      while (true) {
        Socket socket = server.accept();
        Session session = new Session(socket);
        new Thread(session).start();
      }

    } catch (IOException e) {
      System.out.println("Server encountered error: " + e.getMessage());
    } finally {
      System.out.println("Server stopped!");
    }
  }

}
