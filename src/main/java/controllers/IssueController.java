package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import helpers.VariablesHelper;
import models.Issue;
import models.User;
import models.UserPrincipal;
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
@Singleton
public class IssueController extends ProtectedBaseController {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @UnitOfWork
    @RolesAllowed("readIssue")
    public Result list(Context context) {
        EntityManager em = entityManagerProvider.get();
        List<Issue> issues = em
                .createQuery("SELECT i FROM Issue i", Issue.class)
                .getResultList();

        Map<String, Object> bindings = VariablesHelper.create(context);
        bindings.put("issues", issues);
        return Results.html().render(bindings);
    }

    @UnitOfWork
    @RolesAllowed("writeIssue")
    public Result newIssue(Context context) {
        return Results.html().render(VariablesHelper.create(context));
    }

    @RolesAllowed("writeIssue")
    @Transactional
    public Result create(Issue issue, Context context) {
        EntityManager em = entityManagerProvider.get();

        User posterBy = em.createQuery("SELECT u FROM User u WHERE u.account = :account", User.class)
                .setParameter("account", ((UserPrincipal) context.getAttribute("principal")).getName())
                .getSingleResult();

        issue.setPostedBy(posterBy);
        em.merge(issue);

        return Results.redirect("/issues/");
    }
}
