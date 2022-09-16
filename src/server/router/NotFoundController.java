package server.router;

import server.i18n.I18nKey;
import server.interfaces.Request;
import server.interfaces.Response;

public class NotFoundController {

  private static final NotFoundController instance = new NotFoundController();

  public static NotFoundController getInstance() {
    return instance;
  }

  private NotFoundController() {
    //
  }

  public void handle(Request req, Response res) {
    res.setStatusCode(404);
    res.setParam("message", req.t(I18nKey.INVALID_COMMAND, req.getMethod()));
    res.send();
  }
}
