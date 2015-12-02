package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import helpers.VariablesHelper;
import models.User;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

/**
 * @author kawasima
 */
public class UserAdminController extends ProtectedBaseController {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @UnitOfWork
    @RolesAllowed("manageUser")
    public Result list(Context context) {
        EntityManager em = entityManagerProvider.get();
        List<User> users = em
                .createQuery("SELECT u FROM User u LEFT JOIN FETCH u.roles r", User.class)
                .getResultList();

        Map<String, Object> bindings = VariablesHelper.create(context);
        bindings.put("users", users);

        return Results.html().render(bindings);
    }

}
