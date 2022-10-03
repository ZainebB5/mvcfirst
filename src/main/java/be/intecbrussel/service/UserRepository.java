package be.intecbrussel.service;

import be.intecbrussel.model.UserEntity;
import be.intecbrussel.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
    private final EntityManager manager = factory.createEntityManager();

    public void save(UserEntity user){
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        manager.persist(user);
        transaction.commit();
    }

   /* public List<UserEntity> findALlByFirstNameAndMidNameAndLastName(String fN,String mN,String lN){
        Query query= manager.createQuery("select s from UserEntity s WHERE s.fName LIKE:fN and s.lName like :lN and s.mName like:mN");
        query.setParameter("fName",fN);
        query.setParameter("mName",mN);
        query.setParameter("lName",lN);
        List<UserEntity>userEntitieslist=query.getResultList();
        return userEntitieslist;
    }*/

    public List<UserEntity> findAll(){
        Query query = manager.createQuery("SELECT u FROM UserEntity u");
        List<UserEntity> users = query.getResultList();
        return users;
    }


    public Optional<UserEntity> findById(long id){
        UserEntity foundUser = manager.find(UserEntity.class, id);

        return Optional.ofNullable(foundUser);
    }

    public Optional<UserEntity> findByEmail(String email){
        UserEntity foundEmail = manager.find(UserEntity.class, email);
        return Optional.ofNullable(foundEmail);
    }
}
