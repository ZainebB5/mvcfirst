package be.intecbrussel.service;

import be.intecbrussel.model.MessageEntity;
import be.intecbrussel.model.UserEntity;
import be.intecbrussel.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class MessageRepository extends AbstractRepository{

    public void create(MessageEntity message){
        if (message == null){
            throw new MessageException("Message is null ! ");
        }
        final EntityManager em = super.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(message);
        em.getTransaction().commit();
        em.close();
    }

    public void updateSubjectAndContentById ( Long id, String subject, String content ) {

        if (id == null){
            throw new MessageException("Id is required ! ");
        }
        if (subject == null || content == null){
            throw new MessageException("Subject and content are required ! ");
        }

        final EntityManager em = super.getFactory().createEntityManager();
        MessageEntity foundMessage = em.find(MessageEntity.class, id);
        if (foundMessage == null){
            throw new MessageException("Not found");
        }
        foundMessage.setSubject(subject);
        foundMessage.setContent(content);
        em.getTransaction().commit();
        em.close();

    }

    public void delete ( Long id) {
        if (id == null){
            throw new MessageException("Id is required ! ");
        }
        final EntityManager em = super.getFactory().createEntityManager();
        em.getTransaction().begin();
        MessageEntity foundMessage = em.find(MessageEntity.class, id);
        if (foundMessage == null){
            throw new MessageException("Not found ! ");
        }
        em.remove(foundMessage);
        em.getTransaction().commit();
        em.close();

    }

    public Optional<MessageEntity> findById (Long id ) {
        if (id == null){
            throw new MessageException("Id is required ! ");
        }
        final EntityManager em = super.getFactory().createEntityManager();
        MessageEntity foundMessage = em.find(MessageEntity.class, id);
        if (foundMessage == null){
            throw new MessageException("Not found ! ");
        }

        em.close();
        return Optional.ofNullable(foundMessage);
    }

    /*public List<MessageEntity> findMessageBySender (Long senderId) {
        if (senderId == null){
            throw new MessageException("Id is required ! ");
        }
        final EntityManager em = super.getFactory().createEntityManager();
        Query query = em.createQuery("SELECT u FROM MessageEntity m join UserEntity u where m.sender.id = senderId ");//, UserEntity.class);

        query.setParameter("senderId", senderId);
        List < MessageEntity > messages = query.getResultList ( );
        return messages;
    }*/

}
