package client.interfaces;

import java.io.File;
import java.io.IOException;

public interface Request {

  public void setMethod(String method);

  public void setPath(String path);

  public void setParam(String key, String value);

  public void setFile(File file);

  public void send() throws IOException;

}
