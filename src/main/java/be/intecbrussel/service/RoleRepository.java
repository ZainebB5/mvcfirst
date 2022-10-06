package be.intecbrussel.service;

import be.intecbrussel.model.RoleEntity;
import be.intecbrussel.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.Optional;

public class RoleRepository extends AbstractRepository{


    public void create(RoleEntity role){
        if(role == null){
            throw new RoleException("Role is null ! ");
        }
        final EntityManager em = super.getFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(role);
        em.getTransaction().commit();
        em.close();
    }
    public void updateTitleAndDescriptionById ( Long id ,String title, String description ) {
        if (id == null){
            throw new RoleException("Id is required ! ");
        }

        if(title == null || title.isBlank()
                || description == null || description.isBlank()){
            throw new UserException("Firstname and lastname are required !");
        }
        final EntityManager em = super.getFactory().createEntityManager();
        em.getTransaction().begin();
        final RoleEntity foundRole = em.find(RoleEntity.class, id);
        if (foundRole == null){
            throw new RoleException("RoleEntity not found ! ");
        }
        foundRole.setTitle(title);
        foundRole.setDescription(description);
        if (foundRole == null){
            throw new RoleException("RoleEntity not found ! ");
        }
        em.getTransaction().commit();
        em.close();

    }

    public void delete ( Long id) {
        if (id == null){
            throw new RoleException("Id is required ! ");
        }
        final EntityManager em = super.getFactory().createEntityManager();
        em.getTransaction().begin();
        final RoleEntity foundRole = em.find(RoleEntity.class, id);
        if (foundRole == null){
            throw new RoleException("RoleEntity not found ! ");
        }
        em.remove(foundRole);
        em.getTransaction().commit();
        em.close();
    }

    public Optional<RoleEntity> findById (Long id ) {
        final EntityManager em = super.getFactory().createEntityManager();
        em.getTransaction().begin();
        final RoleEntity foundRole = em.find(RoleEntity.class, id);
        em.getTransaction().commit();
        return Optional.ofNullable(foundRole);

    }

}
