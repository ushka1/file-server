package shared.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;

public class MyLogger {

  private static final Logger INSTANCE = Logger.getLogger(MyLogger.class.getName());
  private static boolean ready = false;

  public static Logger getInstance() {
    if (!ready) {
      init();
      ready = true;
    }

    return INSTANCE;
  }

  private static void init() {
    INSTANCE.setUseParentHandlers(false);

    ConsoleHandler handler = new ConsoleHandler();
    INSTANCE.addHandler(handler);

    Formatter formatter = new MyFormatter();
    handler.setFormatter(formatter);
  }

  private MyLogger() {
    //
  }

}
