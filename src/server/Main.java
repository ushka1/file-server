package server;

import java.util.Scanner;

import server.Logger.LogMessages;

public class Main {

  private static Logger logger = new Logger();

  private static void addFile(String filename) {
    if (!Utils.filenameValid(filename) || Storage.fileExists(filename)) {
      logger.log(LogMessages.FILE_ADD_FAILURE, filename);
      return;
    }

    Storage.addFile(filename);
    logger.log(LogMessages.FILE_ADD_SUCCESS, filename);
  }

  private static void getFile(String filename) {
    if (!Storage.fileExists(filename)) {
      logger.log(LogMessages.FILE_NOT_FOUND, filename);
      return;
    }

    Storage.getFile(filename);
    logger.log(LogMessages.FILE_GET_SUCCESS, filename);
  }

  private static void deleteFile(String filename) {
    if (!Storage.fileExists(filename)) {
      logger.log(LogMessages.FILE_NOT_FOUND, filename);
      return;
    }

    Storage.deleteFile(filename);
    logger.log(LogMessages.FILE_DELETE_SUCCESS, filename);
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
          logger.log(LogMessages.INVALID_COMMAND, command);
      }
    }

    scanner.close();
  }
}
