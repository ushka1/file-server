package server.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import server.i18n.I18nKey;
import server.interfaces.InputParser;
import server.interfaces.Request;

public class RequestImpl implements Request {

  private Locale locale;
  private String command = "";
  private List<String> parameters = new ArrayList<>();
  private List<String> options = new ArrayList<>();

  public RequestImpl(String input) {
    this(input, Locale.ENGLISH);
  }

  public RequestImpl(String input, Locale locale) {
    this.locale = locale;

    InputParser parser = new InputParserImpl(input);
    if (!parser.getUnrecognized().isEmpty()) {
      throw new IllegalArgumentException(t(I18nKey.INVALID_INPUT, parser.getUnrecognized().get(0)));
    }

    this.command = parser.getCommand();
    this.parameters = parser.getParameters();
    this.options = parser.getOptions();
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
  public String[] getParameters() {
    return parameters.toArray(new String[parameters.size()]);
  }

  @Override
  public String[] getOptions() {
    return options.toArray(new String[options.size()]);
  }

}
