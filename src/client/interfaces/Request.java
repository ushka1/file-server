package client.interfaces;

import java.io.File;
import java.io.IOException;

public interface Request {

  public void setMethod(String method);

  public String getMethod();

  public void setPath(String path);

  public String getPath();

  public void setParam(String key, String value);

  public String getParam(String key);

  public void setFile(File file);

  public File getFile();

  public void send() throws IOException;

}
