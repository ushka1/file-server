package server.storage;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import server.config.Constants;
import server.interfaces.Storage;

public class StorageImpl implements Storage, Serializable {

  private static final File STORAGE_DIR = new File(Constants.STORAGE_PATH);
  private static final StorageImpl instance = new StorageImpl();

  public static StorageImpl getInstance() {
    return instance;
  }

  private StorageImpl() {
    if (!STORAGE_DIR.exists())
      STORAGE_DIR.mkdirs();
  }

  @Override
  public File getFile(String filename) {
    if (filename == null)
      return null;

    File file = new File(STORAGE_DIR, filename);
    if (file.exists())
      return file;
    else
      return null;
  }

  @Override
  public synchronized boolean addFile(String filename, File file) {
    if (filename == null || file == null)
      return false;

    File target = new File(STORAGE_DIR, filename);
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
    if (filename == null)
      return false;

    File file = new File(STORAGE_DIR, filename);
    if (!file.exists())
      return false;

    try {
      Files.delete(file.toPath());
    } catch (IOException e) {
      return false;
    }

    return true;
  }

}
