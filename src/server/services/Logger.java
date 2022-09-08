package server.services;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Logger {

  public enum LogMessages {

    FILE_ADD_SUCCESS("file_add_success"),
    FILE_ADD_FAILURE("file_add_failure"),
    FILE_GET_SUCCESS("file_get_success"),
    FILE_DELETE_SUCCESS("file_delete_success"),
    FILE_NOT_FOUND("file_not_found"),
    INVALID_COMMAND("invalid_command"),
    INVALID_LOCALE("invalid_locale");

    private String label;

    private LogMessages(String label) {
      this.label = label;
    }
  }

  private static final String RESOURCE_BASE_NAME = "server.resources.messages";
  private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

  private ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BASE_NAME, DEFAULT_LOCALE);

  public void changeLocale(Locale locale) {
    bundle = ResourceBundle.getBundle(RESOURCE_BASE_NAME, locale);

    if (!bundle.getLocale().equals(locale)) {
      log(LogMessages.INVALID_LOCALE, locale.toString());
      return;
    }
  }

  public void log(LogMessages message, Object... args) {
    MessageFormat formatter = new MessageFormat(bundle.getString(message.label));
    String output = formatter.format(args);
    System.out.println(output);
  }

}
