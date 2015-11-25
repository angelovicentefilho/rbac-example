package services;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Permission;
import models.Role;
import ninja.lifecycle.Start;

import javax.persistence.EntityManager;

/**
 * @author kawasima
 */
@Singleton
public class InitialDataService {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Transactional
    @Start(order = 90)
    public void startService() {
        EntityManager em = entityManagerProvider.get();
        Permission readIssue = new Permission("readIssue");
        Permission writeIssue = new Permission("writeIssue");
        Permission manageUser = new Permission("manageUser");


        Role adminRole = new Role("admin");
        adminRole.setPermissions(ImmutableList.of(
                readIssue, writeIssue, manageUser
        ));
        em.persist(adminRole);

        Role developerRole = new Role("developer");
        adminRole.setPermissions(ImmutableList.of(
                readIssue, writeIssue
        ));
        em.persist(developerRole);

        Role guestRole = new Role("guest");
        adminRole.setPermissions(ImmutableList.of(
                readIssue
        ));
        em.persist(guestRole);

    }
}
