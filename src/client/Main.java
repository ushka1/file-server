package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import client.config.Constants;

public class Main {

  private static Scanner scanner = new Scanner(System.in);

  @SuppressWarnings({ "java:S106" })
  public static void main(String[] args) {

    try (Socket socket = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      System.out.println("Client started!");

      while (!socket.isClosed()) {
        String message = scanner.nextLine();
        if (message.equals(Constants.EXIT))
          break;

        output.writeUTF(message);
        System.out.println("Sent: " + message);

        String res = input.readUTF();
        System.out.println("Received: " + res);
      }

    } catch (IOException e) {
      System.out.println("ERROR: " + e.getMessage());
    }

    System.out.println("Client terminated!");
  }

}
