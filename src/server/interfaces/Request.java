package server.interfaces;

public interface Request extends Translator {

  public String getCommand();

  public String getParameter();

}
