package server.session;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import server.interfaces.Request;

public class RequestImpl implements Request {

  private static final Pattern INPUT_PATTERN = Pattern.compile("(\\w+)|(-\\w+)");

  private Locale locale;
  private String command = "";
  private String parameter = "";

  public RequestImpl(String input) {
    this(input, Locale.ENGLISH);
  }

  public RequestImpl(String input, Locale locale) {
    this.locale = locale;
    parseInput(input);
  }

  @Override
  public Locale getLocale() {
    return locale;
  }

  @Override
  public String getCommand() {
    return command;
  }

  @Override
  public String getParameter() {
    return parameter;
  }

  private void parseInput(String input) {
    Matcher matcher = INPUT_PATTERN.matcher(input.trim());

    int i = 0;
    while (matcher.find()) {
      if (i == 0)
        command = matcher.group();
      else if (i == 1)
        parameter = matcher.group();
      else
        break;

      i++;
    }
  }

}
