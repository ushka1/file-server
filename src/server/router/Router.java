package server.router;

import server.Main;
import server.config.Constants;
import server.interfaces.Request;
import server.interfaces.Response;
import server.storage.StorageController;

public class Router {

  private static final StorageController storageController = StorageController.getInstance();
  private static final NotFoundController notFoundController = NotFoundController.getInstance();
  private static final Router instance = new Router();

  public static Router getInstance() {
    return instance;
  }

  private Router() {
    //
  }

  public void route(Request req, Response res) {
    switch (req.getMethod()) {

      case Constants.POST:
        storageController.addFile(req, res);
        break;

      case Constants.GET:
        storageController.getFile(req, res);
        break;

      case Constants.DELETE:
        storageController.deleteFile(req, res);
        break;

      case Constants.EXIT:
        Main.killServer();
        break;

      default:
        notFoundController.handle(req, res);
    }
  }

}
