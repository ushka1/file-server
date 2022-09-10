package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import client.config.Constants;

public class Main {

  @SuppressWarnings({ "all" })
  public static void main(String[] args) throws InterruptedException {

    try (Socket socket = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      System.out.println("Client started!");

      String message = "Give me everything you have!";
      output.writeUTF(message);
      System.out.println("Sent: " + message);

      String res = input.readUTF();
      System.out.println("Received: " + res);

    } catch (IOException e) {
      System.out.println("Client encountered error: " + e.getMessage());
    }

  }

}
