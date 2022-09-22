package client.flows;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import client.interfaces.Flow;

public class AddFileFlow extends Flow {

  public AddFileFlow(DataInputStream input, DataOutputStream output) {
    super(input, output);
  }

  @Override
  public void begin() {
    //
  }

}
