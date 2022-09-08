package server;

public class Utils {

  private static final String FILENAME_REGEX = "^file[0-9]+$";

  public static boolean filenameValid(String filename) {
    if (!filename.matches(FILENAME_REGEX))
      return false;

    int idx = Integer.parseInt(filename.split("file")[1]);
    if (idx < 1 || 10 < idx)
      return false;

    return true;
  }

}
