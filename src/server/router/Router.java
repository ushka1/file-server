package server.router;

import server.config.Constants;
import server.i18n.I18nKey;
import server.interfaces.Request;
import server.interfaces.Response;
import server.storage.StorageController;

public class Router {

  private static final StorageController STORAGE_CONTROLLER = StorageController.getInstance();
  private static final Router INSTANCE = new Router();

  public static Router getInstance() {
    return INSTANCE;
  }

  /* ============================================================ */

  private Router() {
    //
  }

  public void route(Request req, Response res) {
    switch (req.getCommand()) {

      case Constants.ADD:
        STORAGE_CONTROLLER.addFile(req, res);
        break;

      case Constants.GET:
        STORAGE_CONTROLLER.getFile(req, res);
        break;

      case Constants.DELETE:
        STORAGE_CONTROLLER.deleteFile(req, res);
        break;

      default:
        res.write(req.t(I18nKey.INVALID_COMMAND, req.getCommand()));
    }
  }

}
