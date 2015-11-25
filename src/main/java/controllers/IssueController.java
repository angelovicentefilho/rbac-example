package controllers;

import com.google.common.collect.ImmutableMap;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Issue;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author kawasima
 */
@Singleton
public class IssueController {
    @Inject
    Provider<EntityManager> entityManagerProvider;


    @UnitOfWork
    @RolesAllowed("readIssue")
    public Result list() {
        EntityManager em = entityManagerProvider.get();
        List<Issue> issues = em
                .createQuery("", Issue.class)
                .getResultList();

        return Results.html()
                .render(ImmutableMap.of(
                        "issues", issues));
    }

    @RolesAllowed("writeIssue")
    @Transactional
    public Result createIssue() {
        return Results.redirect("/issues");
    }
}
