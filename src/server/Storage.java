package server;

import java.util.HashSet;
import java.util.Set;

public class Storage {

  private static final Set<String> files = new HashSet<>();

  public static boolean fileExists(String filename) {
    return files.contains(filename);
  }

  public static void addFile(String filename) {
    files.add(filename);
  }

  public static void deleteFile(String filename) {
    files.remove(filename);
  }

}
