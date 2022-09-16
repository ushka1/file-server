package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings({ "java:S106", "java:S1192" })
public class Handlers {

  private Handlers() {
    //
  }

  public static String sendRequest(DataOutputStream output, Scanner scanner) throws IOException {
    String startLine = "";
    Map<String, String> params = new HashMap<>();
    byte[] content = null;

    /* ============================================================ */

    System.out.print("Enter action (1 - get a file, 2 - create a file, 3 - delete a file): > ");
    String choice = scanner.nextLine();
    switch (choice) {
      case "1":
        startLine = "GET /";
        break;

      case "2":
        startLine = "POST /";
        break;

      case "3":
        startLine = "DELETE /";
        break;

      case "exit":
        startLine = "EXIT /";
        break;

      default:
        throw new IllegalArgumentException("Invalid action");
    }

    // XXX
    if (choice.equals("exit")) {
      output.writeUTF("EXIT /");
      output.writeUTF("");
      return choice;
    }

    /* ============================================================ */

    System.out.print("Enter filename: > ");
    String filename = scanner.nextLine();
    params.put("file-name", filename);

    if (choice.equals("2")) {
      System.out.print("Enter file content: > ");
      content = scanner.nextLine().getBytes();
      params.put("file-size", content.length + "");
    }

    /* ============================================================ */

    output.writeUTF(startLine);
    for (var param : params.entrySet())
      output.writeUTF(param.getKey() + "=" + param.getValue());
    output.writeUTF("");

    if (choice.equals("2") && content != null)
      output.write(content, 0, content.length);

    System.out.println("The request was sent.");
    return choice;
  }

  public static void receiveResponse(DataInputStream input, String choice) throws IOException {
    // XXX
    if (choice.equals("exit"))
      return;

    String statusCode;
    Map<String, String> params = new HashMap<>();
    byte[] content = null;

    statusCode = input.readUTF();

    String param;
    while (!(param = input.readUTF()).equals("")) {
      String[] parts = param.split("=");
      params.put(parts[0], parts[1]);
    }

    if (params.keySet().contains("file-size")) {
      int fileSize = Integer.parseInt(params.get("file-size"));
      content = new byte[fileSize];

      input.read(content, 0, fileSize);
    }

    if (statusCode.equals("200")) {
      if (choice.equals("1"))
        System.out.println("The content of the file is: " + new String(content));

      if (choice.equals("2"))
        System.out.println("The response says that file was created!");

      if (choice.equals("3"))
        System.out.println("The response says that the file was successfully deleted!");

    } else if (statusCode.equals("404")) {
      System.out.println("The response says that the file was not found!");
    } else if (statusCode.equals("403")) {
      System.out.printf("The response says that creating the file was forbidden!");
    }
  }

}
