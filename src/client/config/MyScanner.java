package client.config;

import java.util.Scanner;

public class MyScanner {

  public static final Scanner scanner = new Scanner(System.in);

  public static Scanner getScanner() {
    return scanner;
  }

  private MyScanner() {
    //
  }

}
