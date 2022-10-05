package be.intecbrussel.service;

import be.intecbrussel.model.UserEntity;
import be.intecbrussel.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

public class UserRepository extends AbstractRepository{


    public void create ( UserEntity user ){
        if (user == null){
            throw new UserException("UserEntity is null");
        }
        final EntityManager em = super.getEntitYManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        //super.close();
    }

    public void createAll(List<UserEntity> users){
        if (users == null){
            throw new UserException("UserEntity is null");
        }
        final EntityManager em = super.getEntitYManager();
        em.getTransaction().begin();
        for (UserEntity u : users){
            em.persist(u);
        }
        em.getTransaction().commit();
        super.close();
    }

    public void updateFullNameById(final Long id,
                                   final String firstName,
                                   final String middelName,
                                   final String lastName){
        if (id == null){
            throw new UserException("Id is required !");

        }

        if(firstName == null || firstName.isBlank()
        || lastName == null || lastName.isBlank()){
            throw new UserException("Firstname and lastname are required !");
        }

        final EntityManager em = super.getEntitYManager();
        em.getTransaction().begin();

        final UserEntity foundUser = em.find(UserEntity.class, id);

        if (foundUser == null){
            throw new UserException ( "UserEntity not found!" );
        }
        foundUser.setFName(firstName);
        foundUser.setMName(middelName);
        foundUser.setLName(lastName);

        em.getTransaction().commit();
        super.close();

    }

    public void updateEmailById(Long id , String email){
        if(id == null){
            throw new UserException("Id is required");
        }
        final EntityManager em = super.getEntitYManager();
        em.getTransaction().begin();
        UserEntity foundUser = em.find(UserEntity.class, id);
        foundUser.setEmail(email);
        em.getTransaction().commit();
        super.close();
    }


    public void deleteById(Long id){
        if(id == null){
            throw new UserException("Id is required");
        }
        final EntityManager em = super.getEntitYManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        UserEntity foundUser = em.find(UserEntity.class, id);
        if (foundUser != null){
            em.remove(foundUser);
        }else throw new UserException("User not found");
        transaction.commit();

    }

    public Optional<UserEntity> findById(Long id){
        if(id == null){
            throw new UserException("Id is required");
        }
        final EntityManager em = super.getEntitYManager();
        UserEntity foundUser = em.find(UserEntity.class, id);

        return Optional.ofNullable(foundUser);
    }

    public List<UserEntity> findAll(){
        final EntityManager em = super.getEntitYManager();
        Query query = em.createQuery("SELECT u FROM UserEntity u");
        List<UserEntity> users = query.getResultList();
        return users;
    }

    public Boolean existsByEmail(String email){
        if(email == null){
            throw new UserException("Email is required");
        }
        final EntityManager em = super.getEntitYManager();
        Query query = em.createQuery ( "SELECT u FROM UserEntity u WHERE u.email LIKE :email " );
        query.setParameter("email", email);
        if (query.getResultList().size() != 1){
            throw new UserException("the email doesn't exist");
        }

        return true;
    }

    public Boolean isActiveByEmail(String email){
        if(email == null){
            throw new UserException("Email is required");
        }
        final EntityManager em = super.getEntitYManager();
        Query query = em.createQuery ( "SELECT u FROM UserEntity u WHERE u.email LIKE :email AND u.active = true " );
        query.setParameter("email", email);
        if (query.getResultList().size() != 1){
            throw new UserException("the email isn't actif");
        }

        return true;

    }
}


