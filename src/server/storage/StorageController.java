package server.storage;

import java.io.File;

import server.i18n.I18nKey;
import server.interfaces.Request;
import server.interfaces.Response;
import server.interfaces.Storage;

@SuppressWarnings("java:S1192")
public class StorageController {

  private static final StorageController instance = new StorageController(StorageImpl.getInstance());

  public static StorageController getInstance() {
    return instance;
  }

  private Storage storage;

  private StorageController(Storage storage) {
    this.storage = storage;
  }

  public void getFile(Request req, Response res) {
    String filename = req.getParam("file-name");
    File file = storage.getFile(filename);

    if (file != null) {
      res.setStatusCode(200);
      res.setParam("message", req.t(I18nKey.FILE_GET_SUCCESS, filename));
      res.setFile(file);
    } else {
      res.setStatusCode(404);
      res.setParam("message", req.t(I18nKey.FILE_NOT_FOUND, filename));
    }
  }

  public void addFile(Request req, Response res) {
    String filename = req.getParam("file-name");

    if (storage.addFile(filename, req.getTempFile())) {
      res.setStatusCode(200);
      res.setParam("message", req.t(I18nKey.FILE_ADD_SUCCESS, filename));
    } else {
      res.setStatusCode(403);
      res.setParam("message", req.t(I18nKey.FILE_ADD_FAILURE, filename));
    }
  }

  public void deleteFile(Request req, Response res) {
    String filename = req.getParam("file-name");

    if (storage.deleteFile(filename)) {
      res.setStatusCode(200);
      res.setParam("message", req.t(I18nKey.FILE_DELETE_SUCCESS, filename));
    } else {
      res.setStatusCode(404);
      res.setParam("message", req.t(I18nKey.FILE_DELETE_FAILURE, filename));
    }
  }
}
