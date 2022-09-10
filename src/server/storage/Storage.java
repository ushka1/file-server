package server.storage;

public interface Storage {

  public boolean fileExists(String filename);

  public void getFile(String filename);

  public boolean addFile(String filename);

  public boolean deleteFile(String filename);

}
