package client.utils;

import java.io.File;
import java.util.Scanner;

import client.config.Constants;
import client.interfaces.Request;

@SuppressWarnings({ "java:S106", "java:S1192" })
public class RequestCreator {

  private static final Scanner scanner = new Scanner(System.in);
  private static final File USER_DIR = new File(Constants.USER_PATH);

  private Request req;

  public RequestCreator(Request req) {
    this.req = req;
  }

  public void runCreator() {
    System.out.print("Enter action (1 - get a file, 2 - create a file, 3 - delete a file): ");
    String choice = scanner.nextLine();
    switch (choice) {
      case "1":
        createGetRequest();
        break;

      case "2":
        createPostRequest();
        break;

      case "3":
        createDeleteRequest();
        break;

      case "exit":
        createExitRequest();
        break;

      default:
        throw new IllegalArgumentException("Invalid method");
    }
  }

  private void createGetRequest() {
    req.setMethod(Constants.GET);

    System.out.print("Do you want to get the file by name or by id (1 - name, 2 - id): ");
    String choice = scanner.nextLine();
    switch (choice) {
      case "1":
        requestFilename();
        break;

      case "2":
        req.setPath("/id");
        requestFileId();
        break;

      default:
        throw new IllegalArgumentException("Invalid method");
    }
  }

  private void createPostRequest() {
    req.setMethod(Constants.POST);

    String filename = requestFilename();
    File file = new File(USER_DIR, filename);
    if (!file.exists())
      throw new IllegalArgumentException("File does not exist");
    req.setFile(file);

    requestServerFilename();
  }

  private void createDeleteRequest() {
    req.setMethod(Constants.DELETE);

    System.out.print("Do you want to delete the file by name or by id (1 - name, 2 - id): ");
    String choice = scanner.nextLine();
    switch (choice) {
      case "1":
        requestFilename();
        break;

      case "2":
        req.setPath("/id");
        requestFileId();
        break;

      default:
        throw new IllegalArgumentException("Invalid method");
    }
  }

  private void createExitRequest() {
    req.setMethod(Constants.EXIT);
  }

  /* ============================================================ */

  private String requestFilename() {
    System.out.print("Enter name of the file: ");
    String filename = scanner.nextLine();

    req.setParam("file-name", filename);
    return filename;
  }

  private String requestServerFilename() {
    System.out.print("Enter name of the file to be saved on server: ");
    String filename = scanner.nextLine();

    // FIXME
    if (filename.length() > 0)
      req.setParam("file-name", filename);
    return filename;
  }

  private String requestFileId() {
    System.out.print("Enter id: ");
    String id = scanner.nextLine();

    req.setParam("file-id", id);
    return id;
  }
}
