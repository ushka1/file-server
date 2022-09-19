package server.storage;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import server.utils.RandomUtils;
import server.utils.SerializationUtils;

public class IdManager implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final String FILENAME = "data/id.data";
  private static IdManager instance;

  public static IdManager getInstance() {
    if (instance == null) {
      try {
        instance = (IdManager) SerializationUtils.deserialize(FILENAME);
      } catch (IOException | ClassNotFoundException e) {
        instance = new IdManager();
      }
    }

    return instance;
  }

  /* ============================================================ */

  private HashMap<String, String> map = new HashMap<>();

  private IdManager() {
    //
  }

  private void saveState() {
    try {
      SerializationUtils.serialize(this, FILENAME);
    } catch (IOException e) {
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
