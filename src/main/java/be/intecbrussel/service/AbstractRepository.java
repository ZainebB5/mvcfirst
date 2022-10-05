package be.intecbrussel.service;

import be.intecbrussel.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public abstract class AbstractRepository {

    private final EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
    private final EntityManager manager = factory.createEntityManager();

    public EntityManager getEntitYManager(){
        return manager;
    }

    public void close(){
        factory.close();
        manager.close();

    }

}
