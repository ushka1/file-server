package server.i18n;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class I18n {

  private static final String RESOURCE_BASE_NAME = "server.resources.messages";
  public static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
  private static final I18n instance = new I18n();

  public static I18n getInstance() {
    return instance;
  }

  /* ============================================================ */

  private Map<Locale, ResourceBundle> bundles = new HashMap<>();

  private I18n() {
    bundles.put(DEFAULT_LOCALE, ResourceBundle.getBundle(RESOURCE_BASE_NAME, DEFAULT_LOCALE));

    Locale plPL = Locale.forLanguageTag("pl");
    bundles.put(plPL, ResourceBundle.getBundle(RESOURCE_BASE_NAME, plPL));
  }

  private ResourceBundle getBundle(Locale locale) {
    if (bundles.containsKey(locale))
      return bundles.get(locale);
    else
      return getBundle(DEFAULT_LOCALE);
  }

  /* ============================================================ */

  public String get(I18nKey key) {
    return get(key, DEFAULT_LOCALE);
  }

  public String get(I18nKey key, Locale locale) {
    return getBundle(locale).getString(key.getValue());
  }

  /* ============================================================ */

  public String get(I18nKey key, Object... args) {
    return get(key, DEFAULT_LOCALE, args);
  }

  public String get(I18nKey key, Locale locale, Object... args) {
    MessageFormat formatter = new MessageFormat(getBundle(locale).getString(key.getValue()));
    return formatter.format(args);
  }

}
