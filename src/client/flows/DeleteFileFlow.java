package client.flows;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import client.interfaces.Flow;

public class DeleteFileFlow extends Flow {

  public DeleteFileFlow(DataInputStream input, DataOutputStream output) {
    super(input, output);
  }

  @Override
  public void begin() {
    //
  }

}
