package client.implementations;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import client.interfaces.Response;

public class ResponseImpl implements Response {

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
      String filename = params.getOrDefault("file-name", "unnamed.bin");
      long fileSize = Long.parseLong(params.get("file-size"));

      file = new File(filename);
      try (var fileOutput = new FileOutputStream(file)) {
        byte[] buffer = new byte[4 * 1024];
        while (fileSize > 0) {
          int bytesRead = input.read(buffer, 0, Math.min((int) fileSize, buffer.length));
          fileSize -= bytesRead;

          fileOutput.write(buffer);
        }
      }
    }
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
