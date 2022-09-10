package server.session;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import server.config.Constants;
import server.interfaces.Request;
import server.interfaces.Response;
import server.storage.StorageController;

public class Session implements Runnable {

  private StorageController storageController = StorageController.getInstance();
  private Socket socket;

  public Session(Socket socket) {
    this.socket = socket;
  }

  @Override
  @SuppressWarnings({ "java:S106", "java:S125" })
  public void run() {
    try (DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

      boolean run = true;
      while (run) {

        String userInput = input.readUTF();
        System.out.println("Received: " + userInput);

        Request req = new RequestImpl(userInput);
        Response res = new ResponseImpl();

        String command = req.getCommand();
        switch (command) {

          case Constants.ADD:
            storageController.addFile(req, res);
            break;

          case Constants.GET:
            storageController.getFile(req, res);
            break;

          case Constants.DELETE:
            storageController.deleteFile(req, res);
            break;

          case Constants.EXIT:
            run = false;
            break;

          default:
            // res.write(req.t(I18nKey.INVALID_COMMAND, command));
            res.write("All files were sent!");
        }

        output.writeUTF(res.read());
        System.out.println("Sent: " + res.read());

      }

    } catch (IOException e) {
      // System.out.println("Socket encountered error: " + e.getMessage());
    }
  }

}
