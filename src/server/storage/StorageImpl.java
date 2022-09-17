package server.storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import server.config.Constants;
import server.interfaces.Storage;

public class StorageImpl implements Storage {

  private static final File DATA_DIR = new File(Constants.DATA_DIR_PATH);
  private static final StorageImpl instance = new StorageImpl();

  public static StorageImpl getInstance() {
    return instance;
  }

  private StorageImpl() {
    if (!DATA_DIR.exists())
      DATA_DIR.mkdirs();
  }

  @Override
  public synchronized boolean addFile(String filename, File file) {
    File target = new File(DATA_DIR, filename);
    if (target.exists())
      return false;

    try {
      Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      return false;
    }

    return true;
  }

  @Override
  public synchronized boolean deleteFile(String filename) {
    File file = new File(DATA_DIR, filename);
    if (!file.exists())
      return false;

    try {
      Files.delete(file.toPath());
    } catch (IOException e) {
      return false;
    }

    return true;
  }

  // BUG
  // when another thread deletes or writes to that file and response is
  // reading it we have problem
  // possible solution - creating temporary file
  @Override
  public File getFile(String filename) {
    File file = new File(DATA_DIR, filename);
    if (file.exists())
      return file;
    else
      return null;
  }

}
