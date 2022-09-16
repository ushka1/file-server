package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import client.config.Constants;

@SuppressWarnings({ "java:S106", "java:S1192" })
public class Main {

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    try (Socket socket = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      String choice = Handlers.sendRequest(output, scanner);
      Handlers.receiveResponse(input, choice);

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
