package server.storage;

import java.io.File;

import server.i18n.I18nKey;
import server.interfaces.Request;
import server.interfaces.Response;
import server.interfaces.Storage;

@SuppressWarnings("java:S1192")
public class StorageController {

  private static final StorageController instance = new StorageController();

  public static StorageController getInstance() {
    return instance;
  }

  private IdManager idManager = IdManager.getInstance();
  private Storage storage = StorageImpl.getInstance();

  private StorageController() {
    //
  }

  public void getFileById(Request req, Response res) {
    String id = req.getParam("file-id");
    String filename = idManager.getById(id);
    req.setParam("file-name", filename);
    getFile(req, res);
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
      String id = idManager.add(filename);
      res.setStatusCode(200);
      res.setParam("message", req.t(I18nKey.FILE_ADD_SUCCESS, filename, id));
    } else {
      res.setStatusCode(403);
      res.setParam("message", req.t(I18nKey.FILE_ADD_FAILURE, filename));
    }
  }

  public void deleteFileById(Request req, Response res) {
    String id = req.getParam("file-id");
    String filename = idManager.getById(id);
    req.setParam("file-name", filename);
    deleteFile(req, res);
  }

  public void deleteFile(Request req, Response res) {
    String filename = req.getParam("file-name");

    if (storage.deleteFile(filename)) {
      idManager.removeByValue(filename);
      res.setStatusCode(200);
      res.setParam("message", req.t(I18nKey.FILE_DELETE_SUCCESS, filename));
    } else {
      res.setStatusCode(404);
      res.setParam("message", req.t(I18nKey.FILE_NOT_FOUND, filename));
    }
  }
}
