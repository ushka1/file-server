package server.interfaces;

import java.util.List;

public interface InputParser {

  public String getCommand();

  public List<String> getParameters();

  public List<String> getOptions();

  public List<String> getUnrecognized();

}
