package server.interfaces;

import java.util.Locale;

import server.i18n.I18n;
import server.i18n.I18nKey;

public interface Translator {

  public Locale getLocale();

  default String t(I18nKey key) {
    return I18n.getInstance().get(key, getLocale());
  }

  default String t(I18nKey key, Object... args) {
    return I18n.getInstance().get(key, getLocale(), args);
  }

}
