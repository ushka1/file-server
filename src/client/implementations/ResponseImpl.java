package client.implementations;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import client.config.Constants;
import client.interfaces.Response;

public class ResponseImpl implements Response {

  private static final File USER_DIR = new File(Constants.USER_PATH);

  private DataInputStream input;

  private String statusCode;
  private Map<String, String> params = new HashMap<>();
  private File file;

  public ResponseImpl(DataInputStream input) {
    this.input = input;
  }

  public void receive() throws IOException {
    statusCode = input.readUTF();

    String param;
    while (!(param = input.readUTF()).equals("")) {
      int idx = param.indexOf("=");
      params.put(param.substring(0, idx), param.substring(idx + 1));
    }

    if (params.containsKey("file-size")) {
      long size = Long.parseLong(params.get("file-size"));
      String ext = getFileExt();

      file = Files.createTempFile(USER_DIR.toPath(), "temp-", ext).toFile();
      try (var fileOutput = new FileOutputStream(file)) {
        byte[] buffer = new byte[4 * 1024];
        while (size > 0) {
          int bytesRead = input.read(buffer, 0, Math.min((int) size, buffer.length));
          size -= bytesRead;

          fileOutput.write(buffer);
        }
      }
    }
  }

  private String getFileExt() {
    String filename = params.getOrDefault("file-name", "file.bin");
    String[] filenameParts = filename.split("\\.");

    String ext = ".bin";
    if (filenameParts.length >= 2) {
      ext = "." + filenameParts[filenameParts.length - 1];
    }

    return ext;
  }

  @Override
  public String getStatusCode() {
    return statusCode;
  }

  @Override
  public String getParam(String key) {
    return params.getOrDefault(key, "");
  }

  @Override
  public File getFile() {
    return file;
  }

}
