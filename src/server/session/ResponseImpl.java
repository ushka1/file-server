package server.session;

import server.interfaces.Response;

public class ResponseImpl implements Response {

  private StringBuilder message = new StringBuilder();

  @Override
  public String read() {
    return message.toString();
  }

  @Override
  public void write(String str) {
    message.append(str);
  }

}
