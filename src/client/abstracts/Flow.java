package client.abstracts;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

import client.ui.Printer;

public abstract class Flow {

  protected static final Scanner scanner = new Scanner(System.in);
  protected static final Printer printer = Printer.getInstance();

  protected final DataInputStream input;
  protected final DataOutputStream output;

  protected Flow(DataInputStream input, DataOutputStream output) {
    this.input = input;
    this.output = output;
  }

  public abstract void begin();

}
