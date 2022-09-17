package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import client.config.Constants;
import client.implementations.RequestImpl;
import client.implementations.ResponseImpl;
import client.interfaces.Request;
import client.interfaces.Response;

@SuppressWarnings({ "java:S106", "java:S1192" })
public class Main {

  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    try (Socket socket = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      Request req = new RequestImpl(output);

      System.out.print("Enter action (1 - get a file, 2 - create a file, 3 - delete a file): ");
      String choice = scanner.nextLine();
      switch (choice) {
        case "1":
          req.setMethod("GET");
          break;

        case "2":
          req.setMethod("POST");
          break;

        case "3":
          req.setMethod("DELETE");
          break;

        case "exit":
          req.setMethod("EXIT");
          break;

        default:
          throw new IllegalArgumentException("Invalid method");
      }

      System.out.print("Enter filename: ");
      String filename = scanner.nextLine();
      req.setParam("file-name", filename);

      if (choice.equals("2")) {
        File file = new File(filename);
        if (!file.exists())
          throw new IllegalArgumentException("File does not exist");

        req.setFile(file);
      }

      req.send();

      /* ============================================================ */

      Response res = new ResponseImpl(input);
      res.receive();

      System.out.println(res.getStatusCode());

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
