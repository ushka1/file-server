package client.interfaces;

import java.io.File;
import java.io.IOException;

public interface Response {

  public String getStatusCode();

  public String getParam(String key);

  public File getFile();

  public void receive() throws IOException;

  public String getMessage();

}
