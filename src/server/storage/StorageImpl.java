package server.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import server.interfaces.Storage;

public class StorageImpl implements Storage {

  private static final File DATA_DIR = new File("data/storage");
  private static final StorageImpl instance = new StorageImpl();

  public static StorageImpl getInstance() {
    return instance;
  }

  private StorageImpl() {
    if (!DATA_DIR.exists())
      DATA_DIR.mkdirs();
  }

  @Override
  // XXX to refactor
  public synchronized boolean addFile(String filename, File file) {
    try {
      File target = new File(DATA_DIR, filename);
      if (target.exists())
        return false;

      Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      return false;
    }

    return true;
  }

  @Override
  public synchronized boolean deleteFile(String filename) {
    try {
      File file = new File(DATA_DIR, filename);
      if (file.exists())
        Files.delete(file.toPath());
      else
        return false;
    } catch (IOException e) {
      return false;
    }

    return true;
  }

  // BUG
  /*
   * when another thread deletes or writes to that file and response is
   * reading it we have problem
   * possible solution - creating temporary file
   */
  @Override
  public File getFile(String filename) {
    File file = new File(DATA_DIR, filename);
    if (file.exists())
      return file;
    else
      return null;
  }

}
