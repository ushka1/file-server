package server.storage;

import java.util.HashSet;
import java.util.Set;

public class HashSetStorage implements Storage {

  private final Set<String> files = new HashSet<>();

  public boolean fileExists(String filename) {
    return files.contains(filename);
  }

  public boolean addFile(String filename) {
    files.add(filename);
    return true;
  }

  public void getFile(String filename) {
    //
  }

  public boolean deleteFile(String filename) {
    files.remove(filename);
    return true;
  }

}
