package server;

import java.util.Scanner;

public class Main {

  private static void addFile(String filename) {
    if (!Utils.filenameValid(filename)) {
      System.out.println("Cannot add the file " + filename);
      return;
    }

    if (Storage.fileExists(filename)) {
      System.out.println("Cannot add the file " + filename);
      return;
    }

    Storage.addFile(filename);
    System.out.println("The file " + filename + " added successfully");
  }

  private static void getFile(String filename) {
    if (!Storage.fileExists(filename)) {
      System.out.println("The file " + filename + " not found");
      return;
    }

    System.out.println("The file " + filename + " was sent");
  }

  private static void deleteFile(String filename) {
    if (!Storage.fileExists(filename)) {
      System.out.println("The file " + filename + " not found");
      return;
    }

    Storage.deleteFile(filename);
    System.out.println("The file " + filename + " was deleted");
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    boolean run = true;
    while (run) {

      String command = scanner.next().toLowerCase();
      switch (command) {

        case Constants.ADD:
        case Constants.GET:
        case Constants.DELETE: {
          String filename = scanner.next();

          switch (command) {
            case Constants.ADD:
              addFile(filename);
              break;

            case Constants.GET:
              getFile(filename);
              break;

            case Constants.DELETE:
              deleteFile(filename);
              break;
          }

          break;
        }

        case Constants.EXIT:
          run = false;
          break;

        default:
          System.out.println("Command \"" + command + "\" does not exist");
      }
    }

    scanner.close();
  }
}
