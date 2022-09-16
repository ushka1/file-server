package server.interfaces;

import java.io.File;

public interface Response {

  public void send();

  public void setStatusCode(int statusCode);

  public void setParam(String key, String value);

  public void setFile(File file);

}
