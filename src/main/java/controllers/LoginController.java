package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import models.Permission;
import models.Role;
import models.User;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import ninja.params.Param;
import ninja.session.Session;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kawasima
 */
@Singleton
public class LoginController {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    public Result index() {
        return Results.html();
    }

    @UnitOfWork
    public Result login(@Param("account") String account, @Param("password") String password, Session session) {
        EntityManager em = entityManagerProvider.get();

        try {
            User user = em
                    .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles r LEFT JOIN FETCH r.permissions p WHERE u.account = :account", User.class)
                    .setParameter("account", account)
                    .getSingleResult();

            session.put("account", account);
            Set<Permission> permissions = new HashSet<>();
            user.getRoles().stream()
                    .map(Role::getPermissions)
                    .forEach(permissionsInRole -> permissions.addAll(permissionsInRole));

            session.put("permissions", permissions.stream().map(Permission::getName)
                    .collect(Collectors.joining(",")));

            return Results.redirect("/");
        } catch (NoResultException e) {
            return Results.html().template("views/LoginController/index.ftl.html");
        }
    }

    @UnitOfWork
    public Result logout(Session session) {
        session.remove("account");
        session.remove("permissions");
        return Results.redirect("/login");
    }
}
