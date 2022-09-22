package server.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

public class MyLogger {

  private static final Logger logger = Logger.getLogger(MyLogger.class.getName());
  private static boolean initialized = false;

  public static Logger getLogger() {
    if (!initialized) {
      init();
      initialized = true;
    }

    return logger;
  }

  private static void init() {
    logger.setUseParentHandlers(false);

    ConsoleHandler handler = new ConsoleHandler();
    logger.addHandler(handler);

    Formatter formatter = new MyFormatter();
    handler.setFormatter(formatter);
  }

  private MyLogger() {
    //
  }

}
