package conf;

import controllers.ApplicationController;
import controllers.LoginController;
import controllers.SignupController;
import ninja.Router;
import ninja.application.ApplicationRoutes;

/**
 * @author kawasima
 */
public class Routes implements ApplicationRoutes {
    public void init(Router router) {
        router.GET().route("/").with(ApplicationController.class, "index");
        router.GET().route("/login").with(LoginController.class, "index");
        router.POST().route("/login").with(LoginController.class, "login");
        router.POST().route("/logout").with(LoginController.class, "logout");
        router.GET().route("/signup").with(SignupController.class, "index");
        router.POST().route("/signup").with(SignupController.class, "signup");
    }
}
