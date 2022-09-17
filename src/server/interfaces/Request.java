package server.interfaces;

import java.io.File;

public interface Request extends Translator, AutoCloseable {

  public String getMethod();

  public String getPath();

  public String getParam(String key);

  public File getTempFile();

}
