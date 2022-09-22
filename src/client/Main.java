package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Scanner;

import client.config.Constants;
import client.implementations.RequestImpl;
import client.implementations.ResponseImpl;
import client.interfaces.Request;
import client.interfaces.Response;
import client.utils.RequestCreator;

@SuppressWarnings({ "java:S106" })
public class Main {
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    try (Socket socket = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
        var input = new DataInputStream(new BufferedInputStream(socket.getInputStream(), 4096));
        var output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 4096))) {

      Request req = new RequestImpl(output);
      RequestCreator creator = new RequestCreator(req);
      creator.runCreator();

      req.send();

      /* ============================================================ */

      Response res = new ResponseImpl(input);
      res.receive();

      File file = res.getFile();
      if (file != null && file.exists()) {
        System.out.println("The file was downloaded! Specify a name for it: ");
        String filename = scanner.nextLine();

        if (filename.length() == 0) {
          filename = res.getParam("file-name");
        }

        try {
          Files.move(file.toPath(), file.toPath().resolveSibling(filename));
        } catch (IOException e) {
          //
        }
        System.out.println("File saved on the hard drive!");
      } else {
        System.out.print(res.getParam("message"));
      }

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
