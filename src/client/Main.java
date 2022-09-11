package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Logger;

import client.config.Constants;
import shared.logger.MyLogger;

public class Main {

  private static Logger logger = MyLogger.getInstance();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    try (Socket socket = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      logger.info("Client started!");

      while (!socket.isClosed()) {
        String message = scanner.nextLine();
        if (message.equals(Constants.EXIT))
          break;

        output.writeUTF(message);
        logger.info(() -> "Sent: " + message);

        String res = input.readUTF();
        logger.info(() -> "Received: " + res);
      }

    } catch (IOException e) {
      logger.severe(e.getMessage());
    }
    logger.info("Client terminated!");
  }

}
