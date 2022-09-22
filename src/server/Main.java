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

  private static final Logger logger = MyLogger.getLogger();
  private static ServerSocket server;

  public static void main(String[] args) {
    try (ServerSocket server = new ServerSocket(
        Constants.PORT,
        Constants.MAX_PENDING_CONNECTIONS,
        InetAddress.getByName(Constants.ADDRESS))) {

      Main.server = server;
      logger.info("Server listening at: " + Constants.ADDRESS + ":" + Constants.PORT);

      while (!server.isClosed()) {
        Socket socket = server.accept();
        logger.info("Socket connection opened.");

        Session session = new Session(socket);
        new Thread(session).start();
      }

    } catch (IOException e) {
      logger.severe("Server error: " + e);
      e.printStackTrace();
    } finally {
      logger.info("Server shutdown.");
    }
  }

  public static void shutdownServer() {
    try {
      if (server != null) {
        server.close();
      }
    } catch (IOException e) {
      logger.severe("Server shutdown error: " + e);
      e.printStackTrace();
    }
  }

}
