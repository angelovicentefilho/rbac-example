package filters;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
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
        Set<String> permissions = new HashSet<>();

        if (session.get("permissions") != null) {
            permissions = ImmutableSet.copyOf(Splitter.on(',').split(session.get("permissions")));

        }
        context.setAttribute("principal", new UserPrincipal(session.get("account"), permissions));

        return filterChain.next(context);
    }
}
