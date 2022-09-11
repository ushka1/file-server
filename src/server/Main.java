package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import server.config.Constants;
import server.session.Session;
import shared.logger.MyLogger;

public class Main {

  private static Logger logger = MyLogger.getInstance();

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(
        Constants.PORT,
        Constants.MAX_PENDING_CONNECTIONS,
        InetAddress.getByName(Constants.ADDRESS))) {

      logger.info("Server started!");

      while (!server.isClosed()) {
        Socket socket = server.accept();
        Session session = new Session(socket);
        new Thread(session).start();
      }

    } catch (IOException e) {
      logger.severe(e.getMessage());
    } finally {
      logger.info("Server stopped!");
    }
  }

}
