package client.session;

import static client.config.Constants.POST;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import client.interfaces.Request;

public class RequestImpl implements Request {

  private DataOutputStream output;

  private String method;
  private String path;
  private Map<String, String> params = new HashMap<>();
  private File file;

  public RequestImpl(DataOutputStream output) {
    this.output = output;
  }

  @Override
  public void setMethod(String method) {
    this.method = method;
  }

  @Override
  public String getMethod() {
    return method;
  }

  @Override
  public void setPath(String path) {
    this.path = path;
  }

  @Override
  public String getPath() {
    return path;
  }

  @Override
  public void setParam(String key, String value) {
    params.put(key, value);
  }

  @Override
  public String getParam(String key) {
    return params.getOrDefault(key, "");
  }

  @Override
  public void setFile(File file) {
    if (file == null || !file.exists())
      return;

    this.file = file;
    params.put("file-size", file.length() + "");
    params.put("file-name", file.getName());
  }

  @Override
  public File getFile() {
    return file;
  }

  @Override
  public void send() throws IOException {
    output.writeUTF(method + " " + path);

    for (var param : params.entrySet()) {
      output.writeUTF(param.getKey() + "=" + param.getValue());
    }
    output.writeUTF("");

    if (method.equals(POST) && file != null && file.exists()) {
      try (var fileInput = new BufferedInputStream(new FileInputStream(file))) {
        int bytesRead = 0;
        byte[] buffer = new byte[4 * 1024];

        while ((bytesRead = fileInput.read(buffer)) != -1) {
          output.write(buffer, 0, bytesRead);
        }
      }
    }

    output.flush();
  }

}
