package server.session;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import server.interfaces.InputParser;

public class InputParserImpl implements InputParser {

  /*
   * group 1 - parameters, format "parameter"
   * group 2 - options, format "-option"
   * group 3 - unrecognized
   */
  private static final Pattern INPUT_PATTERN = Pattern.compile("(\\w+)|(-\\w+)|([^ ]+)");

  private String command = "";
  private List<String> parameters = new ArrayList<>();
  private List<String> options = new ArrayList<>();
  private List<String> unrecognized = new ArrayList<>();

  public InputParserImpl(String input) {
    parse(input);
  }

  private void parse(String input) {
    Matcher matcher = INPUT_PATTERN.matcher(input.trim());

    boolean first = true;
    while (matcher.find()) {
      if (first) {
        command = matcher.group();
        first = false;
        continue;
      }

      String val;
      if ((val = matcher.group(1)) != null)
        parameters.add(val);
      else if ((val = matcher.group(2)) != null)
        options.add(val);
      else if ((val = matcher.group(3)) != null)
        unrecognized.add(val);
    }
  }

  @Override
  public String getCommand() {
    return command;
  }

  @Override
  public List<String> getParameters() {
    return parameters;
  }

  @Override
  public List<String> getOptions() {
    return options;
  }

  @Override
  public List<String> getUnrecognized() {
    return unrecognized;
  }

}
