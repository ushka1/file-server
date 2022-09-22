package client.ui;

@SuppressWarnings("java:S106")
public class Printer {

  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_YELLOW = "\u001b[33m";
  private static final String ANSI_BLUE = "\u001B[34m";
  private static final String ANSI_RESET = "\u001B[0m";

  private static final Printer instance = new Printer();

  public static Printer getInstance() {
    return instance;
  }

  private Printer() {
    //
  }

  public void info(String message) {
    System.out.println(message);
  }

  public void action(String message) {
    System.out.print(ANSI_BLUE + message + "\n> " + ANSI_RESET);
  }

  public void warn(String message) {
    System.out.println(ANSI_YELLOW + message + ANSI_RESET);
  }

  public void error(String message) {
    System.out.println(ANSI_RED + message + ANSI_RESET);
  }

}
