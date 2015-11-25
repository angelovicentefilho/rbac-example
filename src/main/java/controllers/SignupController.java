package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.User;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;

import javax.persistence.EntityManager;

/**
 * @author kawasima
 */
@Singleton
public class SignupController {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @UnitOfWork
    public Result index() {
        return Results.html();
    }

    @Transactional
    public Result signup(User user) {
        EntityManager em = entityManagerProvider.get();

        em.merge(user);
        return Results.redirect("/login");
    }
}
