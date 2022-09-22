package server.storage;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;

import server.config.Constants;
import server.logger.MyLogger;
import server.utils.RandomUtils;
import server.utils.SerializationUtils;

public class IdManager implements Serializable {

  private static final long serialVersionUID = 1L;

  private static final File CONFIG_DIR = new File(Constants.CONFIG_PATH);
  private static final File CONFIG_FILE = new File(CONFIG_DIR, "id_manager.data");
  private static final Logger logger = MyLogger.getLogger();
  private static IdManager instance;

  public static IdManager getInstance() {
    if (instance == null) {
      try {
        instance = (IdManager) SerializationUtils.deserialize(CONFIG_FILE.toString());
      } catch (IOException | ClassNotFoundException e) {
        instance = new IdManager();
      }
    }

    return instance;
  }

  private HashMap<String, String> map = new HashMap<>();

  private IdManager() {
    if (!CONFIG_DIR.exists())
      CONFIG_DIR.mkdirs();
  }

  private void saveState() {
    try {
      SerializationUtils.serialize(this, CONFIG_FILE.toString());
    } catch (IOException e) {
      logger.severe("IdManager error: " + e);
      e.printStackTrace();
    }
  }

  public String getById(String id) {
    return map.get(id);
  }

  public String add(String value) {
    String id = RandomUtils.generateId();
    map.put(id, value);

    saveState();
    return id;
  }

  public void removeById(String id) {
    map.remove(id);
    saveState();
  }

  public void removeByValue(String value) {
    map.values().remove(value);
    saveState();
  }

}
