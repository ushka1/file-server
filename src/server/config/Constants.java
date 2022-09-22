package server.config;

public class Constants {

  private Constants() {
    //
  }

  public static final String ADDRESS = "127.0.0.1";
  public static final int PORT = 8080;
  public static final int MAX_PENDING_CONNECTIONS = 50;

  public static final String STORAGE_PATH = "data/server/storage";
  public static final String CONFIG_PATH = "data/server/config";
  public static final String TEMP_PATH = "data/server/temp";

  public static final String GET = "GET";
  public static final String POST = "POST";
  public static final String DELETE = "DELETE";
  public static final String EXIT = "EXIT";

}