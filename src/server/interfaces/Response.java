package server.interfaces;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public interface Response extends Closeable {

  public void setStatusCode(int statusCode);

  public int getStatusCode();

  public void setParam(String key, String value);

  public String getParam(String key);

  public void setFile(File file);

  public File getFile();

  public void send() throws IOException;

}
