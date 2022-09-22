package client.flows;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import client.interfaces.Flow;

public class MainFlow extends Flow {

  public MainFlow(DataInputStream input, DataOutputStream output) {
    super(input, output);
  }

  @Override
  public void begin() {
    printer.info("");

    String action = "";
    while (!action.equals("q")) {

      printer.action("""
          Select action:
          [1] - get file
          [2] - create file
          [3] - delete file
          [q] - exit""");

      action = scanner.nextLine();
      switch (action) {
        case "1":
          new GetFileFlow(input, output).begin();
          break;

        case "2":
          new AddFileFlow(input, output).begin();
          break;

        case "3":
          new DeleteFileFlow(input, output).begin();
          break;

        case "q":
          break;

        default:
          printer.warn("Invalid action selected '" + action + "'.");
      }

      printer.info("");

    }
  }

}
