package server.utils;

public class Utils {

  private Utils() {
    //
  }

  private static final String FILENAME_REGEX = "^file[\\d]+$";

  public static boolean filenameValid(String filename) {
    if (!filename.matches(FILENAME_REGEX))
      return false;

    int idx = Integer.parseInt(filename.split("file")[1]);
    return 1 <= idx && idx <= 10;
  }

}
