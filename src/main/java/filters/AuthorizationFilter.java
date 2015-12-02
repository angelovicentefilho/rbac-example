package filters;

import models.Permission;
import models.UserPrincipal;
import ninja.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author kawasima
 */
public class AuthorizationFilter implements Filter{
    public Result filter(FilterChain filterChain, Context context) {
        Method method = context.getRoute().getControllerMethod();
        RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
        PermitAll permitAll = method.getAnnotation(PermitAll.class);

        UserPrincipal principal = (UserPrincipal) context.getAttribute("principal");

        if (permitAll != null ||
                (rolesAllowed != null && contains(principal.getPermissions(), rolesAllowed.value()))) {
            return filterChain.next(context);
        } else {
            return Results.forbidden().html().template("views/403forbidden.ftl.html");
        }

    }

    boolean contains(Set<String> having, String[] permitteds) {
        for (String permitted : permitteds) {
            if (having.contains(permitted)) {
                return true;
            }
        }
        return false;
    }
}
