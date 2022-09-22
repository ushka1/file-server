package server.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
    try (var output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
      output.writeObject(obj);
    }
  }

  public static Object deserialize(String filename) throws IOException, ClassNotFoundException {
    try (var input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
      return input.readObject();
    }
  }
}