package client.flows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import client.abstracts.Flow;
import client.config.Constants;
import client.interfaces.Request;
import client.interfaces.Response;
import client.session.RequestImpl;
import client.session.ResponseImpl;

public class AddFileFlow extends Flow {

  private static final File USER_DIR = new File(Constants.USER_PATH);

  private Request req = new RequestImpl(output);
  private Response res = new ResponseImpl(input);

  public AddFileFlow(DataInputStream input, DataOutputStream output) {
    super(input, output);
  }

  @Override
  @SuppressWarnings("java:S2583")
  public void begin() {
    boolean proceed = true;

    proceed = prepareRequest();
    if (!proceed)
      return;

    proceed = sendRequest();
    if (!proceed)
      return;

    receiveResponse();
  }

  private boolean prepareRequest() {
    req.setMethod(Constants.POST);
    req.setPath("/");
    askForFile();
    askForNameToBeSaved();

    return true;
  }

  @SuppressWarnings("java:S135")
  private void askForFile() {
    File file;
    while (true) {
      printer.action("Enter name of the file:");
      String name = scanner.nextLine();

      if (name.length() < 1) {
        continue;
      }

      if (!Files.exists(new File(USER_DIR, name).toPath())) {
        printer.warn("File with name '" + name + "' does not exist.");
        continue;
      }

      file = new File(USER_DIR, name);
      break;
    }
    req.setFile(file);
  }

  @SuppressWarnings("java:S135")
  private void askForNameToBeSaved() {
    String name = "";
    while (true) {
      printer.action("Enter name of the file to be saved on server:");
      name = scanner.nextLine();

      if (name.length() == 0) {
        name = req.getParam("file-name");
      }

      if (name.length() < 3) {
        printer.warn("Name must be at least 3 characters length.");
        continue;
      }

      break;
    }
    req.setParam("file-name", name);
  }

  private boolean sendRequest() {
    try {
      req.send();
    } catch (IOException e) {
      printer.error("Request error: " + e.getMessage());
      return false;
    }

    return true;
  }

  private boolean receiveResponse() {
    try {
      res.receive();
    } catch (IOException e) {
      printer.error("Response error: " + e.getMessage());
      return false;
    }

    if (res.getStatusCode().equals("200")) {
      printer.info(res.getMessage());
    } else {
      printer.warn(res.getMessage());
    }

    return true;
  }

}
