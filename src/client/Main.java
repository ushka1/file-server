package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import client.config.Constants;
import client.implementations.RequestImpl;
import client.implementations.ResponseImpl;
import client.interfaces.Request;
import client.interfaces.Response;
import client.utils.RequestCreator;

@SuppressWarnings({ "java:S106" })
public class Main {

  public static void main(String[] args) {
    try (Socket socket = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      Request req = new RequestImpl(output);
      RequestCreator creator = new RequestCreator(req);
      creator.runCreator();

      req.send();
      System.out.println("The request was sent.");

      /* ============================================================ */

      Response res = new ResponseImpl(input);
      res.receive();

      System.out.println(res.getParam("message"));

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

}
