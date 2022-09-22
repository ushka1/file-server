package client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import client.config.Constants;
import client.flows.MainFlow;
import client.ui.Printer;

public class Main {

  private static final Printer printer = Printer.getInstance();
  private static final File USER_DIR = new File(Constants.USER_PATH);

  public static void main(String[] args) {

    if (!USER_DIR.exists())
      USER_DIR.mkdirs();

    try (Socket socket = new Socket(InetAddress.getByName(Constants.SERVER_ADDRESS), Constants.SERVER_PORT);
        var input = new DataInputStream(new BufferedInputStream(socket.getInputStream(), 4096));
        var output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream(), 4096))) {

      printer.info("Connected to server.");

      new MainFlow(input, output).begin();

    } catch (IOException e) {
      printer.error("Connection error: " + e.getMessage());
    } finally {
      printer.info("Disconnected from server.");
    }
  }

}
