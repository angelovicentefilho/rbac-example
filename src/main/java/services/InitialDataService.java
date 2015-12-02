package services;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;
import models.Permission;
import models.Role;
import ninja.lifecycle.Dispose;
import ninja.lifecycle.Start;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;

/**
 * @author kawasima
 */
@Singleton
public class InitialDataService {
    private static final Logger LOG = LoggerFactory.getLogger(InitialDataService.class);

    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Start(order = 90)
    public void startService() {
        insertData();
    }

    @Transactional
    protected void insertData() {
        EntityManager em = entityManagerProvider.get();
        Permission readIssue =  new Permission("readIssue");
        Permission writeIssue = new Permission("writeIssue");
        Permission manageUser = new Permission("manageUser");

        em.persist(readIssue);
        em.persist(writeIssue);
        em.persist(manageUser);

        Role adminRole = new Role("admin");
        adminRole.setPermissions(ImmutableSet.of(
                readIssue, writeIssue, manageUser
        ));
        em.persist(adminRole);

        Role developerRole = new Role("developer");
        developerRole.setPermissions(ImmutableSet.of(
                readIssue, writeIssue
        ));
        em.persist(developerRole);

        Role guestRole = new Role("guest");
        guestRole.setPermissions(ImmutableSet.of(
                readIssue
        ));
        em.persist(guestRole);

        LOG.info("setup initial data");
    }

    @Dispose(order = 90)
    public void stopService() {

    }
}
