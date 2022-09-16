package server.interfaces;

import java.io.File;
import java.util.Map;

public interface Request extends Translator {

  public String getMethod();

  public String getPath();

  public Map<String, String> getParams();

  public File getTempFile();

}
