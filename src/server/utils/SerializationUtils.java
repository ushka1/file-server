package server.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils {

  private SerializationUtils() {
    //
  }

  public static void serialize(Object obj, String filename) throws IOException {
    try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
      output.writeObject(obj);
    }
  }

  public static Object deserialize(String filename) throws IOException, ClassNotFoundException {
    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
      return input.readObject();
    }
  }
}