package server.router;

import static server.config.Constants.DELETE;
import static server.config.Constants.EXIT;
import static server.config.Constants.GET;
import static server.config.Constants.POST;

import server.Main;
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
    String method = req.getMethod();
    String path = req.getPath();

    switch (method + " " + path) {

      case GET + " /":
        storageController.getFile(req, res);
        break;

      case GET + " /id":
        storageController.getFileById(req, res);
        break;

      case POST + " /":
        storageController.addFile(req, res);
        break;

      case DELETE + " /":
        storageController.deleteFile(req, res);
        break;

      case DELETE + " /id":
        storageController.deleteFileById(req, res);
        break;

      case EXIT + " /":
        Main.shutdownServer();
        break;

      default:
        notFoundController.handle(req, res);
    }
  }

}
