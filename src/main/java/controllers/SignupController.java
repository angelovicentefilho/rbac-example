package controllers;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Role;
import models.User;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import ninja.params.Param;

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
    public Result signup(User user, @Param("role") String roleStr) {
        EntityManager em = entityManagerProvider.get();

        Role role = em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", roleStr)
                .getSingleResult();

        user.setRoles(ImmutableSet.of(role));
        em.merge(user);
        return Results.redirect("/login");
    }
}
