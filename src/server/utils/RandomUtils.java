package server.utils;

import java.util.Random;

public class RandomUtils {
  private static final Random random = new Random();

  private RandomUtils() {
    //
  }

  public static String generateId() {
    return random.nextInt(10_000, 99_999) + "";
  }
}
