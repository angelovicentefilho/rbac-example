package conf;

import controllers.*;
import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

/**
 * @author kawasima
 */
public class Routes implements ApplicationRoutes {
    public void init(Router router) {
        router.GET().route("/").with(ApplicationController.class, "index");
        router.GET().route("/assets/{fileName: .*}").with(AssetsController.class, "serveStatic");

        router.GET().route("/login").with(LoginController.class, "index");
        router.POST().route("/login").with(LoginController.class, "login");
        router.POST().route("/logout").with(LoginController.class, "logout");
        router.GET().route("/signup").with(SignupController.class, "index");
        router.POST().route("/signup").with(SignupController.class, "signup");

        router.GET().route("/issues/").with(IssueController.class, "list");
        router.GET().route("/issue/new").with(IssueController.class, "newIssue");
        router.POST().route("/issues/").with(IssueController.class, "create");

        router.GET().route("/users/").with(UserAdminController.class, "list");
    }
}
