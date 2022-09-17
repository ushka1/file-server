package server.session;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import server.interfaces.Response;
import server.logger.MyLogger;

public class ResponseImpl implements Response {

  private static final Logger logger = MyLogger.getInstance();
  private final DataOutputStream output;

  private int statusCode = 200;
  private Map<String, String> params = new HashMap<>();
  private File file = null;
  private boolean sent = false;

  public ResponseImpl(DataOutputStream output) {
    this.output = output;
  }

  @Override
  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  @Override
  public void setParam(String key, String value) {
    params.put(key, value);
  }

  @Override
  public void setFile(File file) {
    this.file = file;
  }

  // BUG file read should have own try-catch and if file not read/found then set
  // content-length to 0 or somethign
  @Override
  public void send() throws IOException {
    if (sent) {
      return;
    }
    sent = true;

    if (file != null && file.exists()) {
      params.put("file-name", file.getName());
      params.put("file-size", file.length() + "");
    }

    output.writeUTF(statusCode + "");

    for (var param : params.entrySet()) {
      output.writeUTF(param.getKey() + "=" + param.getValue());
    }
    output.writeUTF("");

    if (file != null && file.exists()) {
      try (FileInputStream fileInput = new FileInputStream(file)) {
        int bytesRead = 0;
        byte[] buffer = new byte[4 * 1024];

        while ((bytesRead = fileInput.read(buffer)) != -1) {
          output.write(buffer, 0, bytesRead);
        }
      }
    }

    logger.info("Send: " + statusCode);
  }

}
