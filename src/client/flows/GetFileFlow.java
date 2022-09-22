package client.flows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import client.interfaces.Flow;
import client.interfaces.Request;
import client.interfaces.Response;
import client.session.RequestImpl;
import client.session.ResponseImpl;

public class GetFileFlow extends Flow {

  private Request req = new RequestImpl(output);
  private Response res = new ResponseImpl(input);

  public GetFileFlow(DataInputStream input, DataOutputStream output) {
    super(input, output);
  }

  @Override
  public void begin() {
    req.setMethod("GET");

    while (req.getPath() == null) {
      printer.action("""
          Select variant:
          [1] - get by name
          [2] - get by ID
          [q] - return""");

      String variant = scanner.nextLine();
      switch (variant) {
        case "1":
          req.setPath("/");
          requestName();
          break;

        case "2":
          req.setPath("/id");
          requestId();
          break;

        case "q":
          return;

        default:
          printer.warn("Invalid variant selected '" + variant + "'.");
      }
    }

    try {
      req.send();
      printer.info("Request send.");
    } catch (IOException e) {
      printer.error("Request error: " + e.getMessage());
    }

    try {
      res.receive();
    } catch (IOException e) {
      printer.error("Response error: " + e.getMessage());
    }

    if (res.getStatusCode().equals("200")) {
      printer.info("File was downloaded.");
      requestNameToSave();
    } else {
      if (res.getParam("message").length() > 0) {
        printer.warn(res.getParam("message"));
      } else {
        printer.warn("Server responded with code: " + res.getStatusCode() + ".");
      }
    }
  }

  private void requestName() {
    printer.action("Enter name of the file:");
    String name = scanner.nextLine();
    req.setParam("file-name", name);
  }

  private void requestId() {
    printer.action("Enter ID:");
    String id = scanner.nextLine();
    req.setParam("file-id", id);
  }

  private void requestNameToSave() {
    printer.action("Specify name for file:");
    String name = scanner.nextLine();

    File file = res.getFile();

    try {
      Files.move(file.toPath(), file.toPath().resolveSibling(name));
    } catch (IOException e) {
      //
    }

    printer.info("File saved with name '" + name + "'.");
  }

}
