package server.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class MyFormatter extends Formatter {

  @Override
  public String format(LogRecord rec) {
    return rec.getLevel() + ": " + rec.getMessage() + '\n';
  }

}