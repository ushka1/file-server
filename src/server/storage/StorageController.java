package server.storage;

import server.i18n.I18nKey;
import server.interfaces.Request;
import server.interfaces.Response;
import server.interfaces.Storage;

public class StorageController {

  private static final StorageController INSTANCE = new StorageController(new StorageImpl());

  public static StorageController getInstance() {
    return INSTANCE;
  }

  /* ============================================================ */

  private Storage storage;

  private StorageController(Storage storage) {
    this.storage = storage;
  }

  private String extractFilename(Request req) {
    return req.getParameters().length == 0 ? "" : req.getParameters()[0];
  }

  public void addFile(Request req, Response res) {
    String filename = extractFilename(req);

    if (!StorageUtils.filenameValid(filename) || storage.fileExists(filename)) {
      res.write(req.t(I18nKey.FILE_ADD_FAILURE, filename));
      return;
    }

    storage.addFile(filename);
    res.write(req.t(I18nKey.FILE_ADD_SUCCESS, filename));
  }

  public void getFile(Request req, Response res) {
    String filename = extractFilename(req);

    if (!storage.fileExists(filename)) {
      res.write(req.t(I18nKey.FILE_NOT_FOUND, filename));
      return;
    }

    storage.getFile(filename);
    res.write(req.t(I18nKey.FILE_GET_SUCCESS, filename));
  }

  public void deleteFile(Request req, Response res) {
    String filename = extractFilename(req);

    if (!storage.fileExists(filename)) {
      res.write(req.t(I18nKey.FILE_NOT_FOUND, filename));
      return;
    }

    storage.deleteFile(filename);
    res.write(req.t(I18nKey.FILE_DELETE_SUCCESS, filename));
  }

}
