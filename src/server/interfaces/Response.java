package server.interfaces;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

public interface Response extends Closeable {

  public void setStatusCode(int statusCode);

  public void setParam(String key, String value);

  public void setFile(File file);

  public void send() throws IOException;

}
