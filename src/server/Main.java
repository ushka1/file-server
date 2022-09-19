package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import server.config.Constants;
import server.logger.MyLogger;
import server.session.Session;

public class Main {

  private static Logger logger = MyLogger.getInstance();
  private static ServerSocket server;

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(
        Constants.PORT,
        Constants.MAX_PENDING_CONNECTIONS,
        InetAddress.getByName(Constants.ADDRESS))) {

      logger.info("Server started!");
      Main.server = server;

      while (!server.isClosed()) {
        Socket socket = server.accept();
        Session session = new Session(socket);
        new Thread(session).start();
      }

    } catch (IOException e) {
      logger.severe(e.getMessage());
      e.printStackTrace();
    } finally {
      logger.info("Server stopped!");
    }
  }

  public static void killServer() {
    try {
      if (server != null) {
        server.close();
      }
    } catch (IOException e) {
      logger.severe(e.getMessage());
      e.printStackTrace();
    }
  }

}
