package server.session;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import server.i18n.I18n;
import server.interfaces.Request;

public class RequestImpl implements Request {

  private final String method;
  private final String path;
  private final Map<String, String> params;
  private final File tempFile;
  private final Locale locale;

  public RequestImpl(Builder builder) {
    this.method = builder.method;
    this.path = builder.path;
    this.params = builder.params;
    this.tempFile = builder.tempFile;
    this.locale = builder.locale;
  }

  @Override
  public String getMethod() {
    return method;
  }

  @Override
  public String getPath() {
    return path;
  }

  @Override
  public String getParam(String key) {
    return params.getOrDefault(key, "");
  }

  @Override
  public void setParam(String key, String value) {
    params.put(key, value);
  }

  @Override
  public File getTempFile() {
    return tempFile;
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public void close() throws IOException {
    if (tempFile != null && tempFile.exists())
      Files.delete(tempFile.toPath());
  }

  public static class Builder {
    private String method;
    private String path;

    private Map<String, String> params = new HashMap<>();
    private File tempFile = null;
    private Locale locale = I18n.DEFAULT_LOCALE;

    public Builder method(String val) {
      method = val;
      return this;
    }

    public Builder path(String val) {
      path = val;
      return this;
    }

    public Builder params(Map<String, String> val) {
      params = val;
      return this;
    }

    public Builder tempFile(File val) {
      tempFile = val;
      return this;
    }

    public Builder locale(Locale val) {
      locale = val;
      return this;
    }
  }

}
