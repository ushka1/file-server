package server.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

public class MyLogger {

  private static final Logger instance = Logger.getLogger(MyLogger.class.getName());
  private static boolean initialized = false;

  public static Logger getInstance() {
    if (!initialized) {
      init();
      initialized = true;
    }

    return instance;
  }

  private static void init() {
    instance.setUseParentHandlers(false);

    ConsoleHandler handler = new ConsoleHandler();
    instance.addHandler(handler);

    Formatter formatter = new MyFormatter();
    handler.setFormatter(formatter);
  }

  private MyLogger() {
    //
  }

}
