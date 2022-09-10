package server.storage;

import java.util.HashSet;
import java.util.Set;

import server.interfaces.Storage;

public class StorageImpl implements Storage {

  private final Set<String> files = new HashSet<>();

  public boolean fileExists(String filename) {
    return files.contains(filename);
  }

  public void getFile(String filename) {
    //
  }

  public synchronized boolean addFile(String filename) {
    files.add(filename);
    return true;
  }

  public synchronized boolean deleteFile(String filename) {
    files.remove(filename);
    return true;
  }

}
