package server.services;

import java.util.HashSet;
import java.util.Set;

public class Storage {

  private final Set<String> files = new HashSet<>();

  public boolean fileExists(String filename) {
    return files.contains(filename);
  }

  public void addFile(String filename) {
    files.add(filename);
  }

  public void getFile(String filename) {

  }

  public void deleteFile(String filename) {
    files.remove(filename);
  }

}
