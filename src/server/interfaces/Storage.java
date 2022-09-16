package server.interfaces;

import java.io.File;

public interface Storage {

  public File getFile(String filename);

  public boolean addFile(String filename, File file);

  public boolean deleteFile(String filename);

}
