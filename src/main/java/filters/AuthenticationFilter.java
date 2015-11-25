package filters;

import models.Permission;
import models.UserPrincipal;
import ninja.*;
import ninja.session.Session;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kawasima
 */
public class AuthenticationFilter implements Filter {
    public Result filter(FilterChain filterChain, Context context) {
        Session session = context.getSession();
        if (session.get("account") == null) {
            return Results.redirect("/login");
        }
        Set<Permission> permissions = new HashSet<>();

        if (session.get("permissions") != null) {
            permissions = Arrays.asList(session.get("permissions").split(","))
                    .stream()
                    .map(pname -> new Permission(pname))
                    .collect(Collectors.toSet());
        }
        context.setAttribute("principal", new UserPrincipal(session.get("account"), permissions));

        return filterChain.next(context);
    }
}
