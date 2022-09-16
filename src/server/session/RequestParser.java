package server.session;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

  private static final String START_REGEX = "\\w+ [\\w/]+";
  private static final String PARAM_REGEX = "[\\w-]+=[\\w-.,() ]+";
  private static final File TEMP_DIR = new File("data/temp");

  private final DataInputStream input;

  private String method;
  private String path;
  private Map<String, String> params = new HashMap<>();
  private File tempFile;
  private String error = null;

  public RequestParser(DataInputStream input) {
    if (!TEMP_DIR.exists())
      TEMP_DIR.mkdirs();

    this.input = input;
  }

  public String getMethod() {
    return method;
  }

  public String getPath() {
    return path;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public File getTempFile() {
    return tempFile;
  }

  public String getError() {
    return error;
  }

  public void receiveInput() throws IOException {
    try {
      String startLine = input.readUTF();
      parseStartLine(startLine);

      String param;
      while (!(param = input.readUTF()).equals(""))
        parseParam(param);

      if (method.equals("POST"))
        receiveFile();

    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }
  }

  private void parseStartLine(String startLine) {
    if (!startLine.matches(START_REGEX))
      throw new IllegalArgumentException("Invalid start-line \"" + startLine + "\"");

    String[] parts = startLine.split(" ");
    method = parts[0].toUpperCase();
    path = parts[1];
  }

  private void parseParam(String param) {
    if (!param.matches(PARAM_REGEX))
      throw new IllegalArgumentException("Invalid param \"" + param + "\"");

    String[] parts = param.split("=");
    params.put(parts[0], parts[1]);
  }

  private void receiveFile() throws IOException {
    String name = "temp-" + new Date().getTime() + ".bin";
    tempFile = new File(TEMP_DIR, name);

    long size = 0;
    if (params.containsKey("file-size"))
      size = Long.parseLong(params.get("file-size"));
    else
      size = input.available();

    byte[] buffer = new byte[4 * 1024];
    try (FileOutputStream fileOutput = new FileOutputStream(tempFile)) {
      while (size > 0) {
        int bytesRead = input.read(buffer, 0, (int) Math.min(buffer.length, size));
        if (bytesRead == -1)
          break;

        fileOutput.write(buffer, 0, bytesRead);
        size -= bytesRead;
      }
    }
  }
}
