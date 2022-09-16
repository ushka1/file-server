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

  @Override
  public void send() {
    if (sent)
      return;
    else
      sent = true;

    try {
      output.writeUTF(statusCode + "");

      for (var param : params.entrySet())
        output.writeUTF(param.getKey() + "=" + param.getValue());
      if (file != null && file.exists()) {
        output.writeUTF("file-size=" + file.length());
        output.writeUTF("file-name=" + file.getName());
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
    } catch (IOException e) {
      logger.severe(e.getMessage());
      e.printStackTrace();
    }
  }

}
