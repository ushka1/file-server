package server.i18n;

public enum I18nKey {

  FILE_GET_SUCCESS("file_get_success"),
  FILE_NOT_FOUND("file_not_found"),
  FILE_ADD_SUCCESS("file_add_success"),
  FILE_ADD_FAILURE("file_add_failure"),
  FILE_DELETE_SUCCESS("file_delete_success"),
  FILE_DELETE_FAILURE("file_delete_failure"),
  INVALID_INPUT("invalid_input"),
  INVALID_REQUEST("invalid_request"),
  INVALID_LOCALE("invalid_locale");

  private final String value;

  private I18nKey(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}