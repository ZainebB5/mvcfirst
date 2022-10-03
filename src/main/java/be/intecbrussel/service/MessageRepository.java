package be.intecbrussel.service;

import be.intecbrussel.model.MessageEntity;
import be.intecbrussel.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class MessageRepository {
    private final EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
    private final EntityManager manager = factory.createEntityManager();

    public void save(MessageEntity messageEntity){
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(messageEntity);
        transaction.commit();
    }

}
