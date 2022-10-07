package be.intecbrussel.service;

import be.intecbrussel.model.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.IllegalFormatCodePointException;

public class UserService extends AbstractRepository{

    private final UserRepository userRepository = new UserRepository();

    public boolean login(final String email, final String hashedPassword){
        if(email == null || hashedPassword == null){
            throw new UserException("Email and password are required ! ");
        }
        if( userRepository.existsByEmail(email)){

            throw new UserException(" User already exists ");
        }
        if ( ! userRepository.isActiveByEmail(email)){
            throw new UserException("The user with given email is deleted .");
        }

        // TODO: controleer of de gebruikersnaam en het wachtwoord klopt zijn

        final EntityManager em = super.getFactory().createEntityManager();
        Query query = em.createQuery ( "SELECT u FROM UserEntity u WHERE u.email LIKE :email AND u.hashedPassword LIKE :hashedPassword" );
        query.setParameter("email", email);
        query.setParameter("hashedPassword", hashedPassword);
        if (query.getResultList().equals(email) != query.getResultList().equals(hashedPassword)){
            //if(! user.getEmail().equals(email) || ! user.getHashedPassword().equals(password)){
            throw new UserException(" Incorrect password ! ");
        }

        // TODO: als alles klopt, geef true terug
        return true;
    }

    public boolean register (UserEntity user) throws UserException {
        //Throws ConstrainViolationException als validatie faolt
        userRepository.create(user);
        System.out.println("User whit email : " + user.getEmail() + " already registred !");
        return true;
    }

    public boolean register1 (UserEntity user) throws UserException {

        // TODO: controleer of de gebruiker bestaat, return false als dat zo (true) is
        if(  userRepository.existsByEmail(user.getEmail())){
            throw new UserException("This email is already used !");
        }

        // TODO: controleer of de gebruiker actief is, return false als dat zo (true) is
        if ( ! userRepository.isActiveByEmail(user.getEmail())){
            throw new UserException("The user with given email is deleted .");
        }

        // TODO: als de wachtwoord minder dan 6 tekens is, return false

        // TODO: Secure wachtwoord: als de wachtwoord niet minstens 1 hoofdletter, 1 kleine letter en 1 cijfer bevat, return false
        if(! user.getHashedPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$")){

            throw new UserException("the password must contain minimum 6 character , 1 upper and lower case and 1 digit !");
        }

        userRepository.create(user);
        System.out.println("User whit email : " + user.getEmail() + " already registred !");
        return true;
    }

    public boolean register2 (UserEntity user) throws UserException {

        // TODO: controleer of de gebruiker bestaat, return false als dat zo (true) is
        if(  userRepository.existsByEmail(user.getEmail())){
            throw new UserException("This email is already used !");
        }

        // TODO: controleer of de gebruiker actief is, return false als dat zo (true) is
        if ( ! userRepository.isActiveByEmail(user.getEmail())){
            throw new UserException("The user with given email is deleted .");
        }

        // TODO: als de wachtwoord minder dan 6 tekens is, return false

        // TODO: Secure wachtwoord: als de wachtwoord niet minstens 1 hoofdletter, 1 kleine letter en 1 cijfer bevat, return false
        if(! user.getHashedPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$")){

            throw new UserException("the password must contain minimum 6 character , 1 upper and lower case and 1 digit !");
        }

        userRepository.create(user);
        System.out.println("User whit email : " + user.getEmail() + " already registred !");
        return true;
    }

    public boolean register2 ( final String firstName,
                              final String middelName,
                              final String lastName,
                              final String email,
                              final String password ) {


        // TODO: controleer of de gebruiker bestaat, return false als dat zo (true) is
        if(  userRepository.existsByEmail(email)){
            throw new UserException("This email is already used !");
        }

        // TODO: controleer of de gebruiker actief is, return false als dat zo (true) is
        if ( ! userRepository.isActiveByEmail(email)){
            throw new UserException("The user with given email is deleted .");
        }

        // TODO: als de wachtwoord minder dan 6 tekens is, return false
        if (password.length() < 6){
            throw new RuntimeException("the password must contain minimum 6 letters !");
        }
        // TODO: Secure wachtwoord: als de wachtwoord niet minstens 1 hoofdletter, 1 kleine letter en 1 cijfer bevat, return false
        int upperCount = 0;
        int lowerCount = 0;
        int digitCount = 0;
        for (int i = 0; i< password.length();i++){
            char checkPw = password.charAt(i);
            if (Character.isUpperCase(checkPw)){
                upperCount++;
            }else if (Character.isLowerCase(checkPw)){
                lowerCount++;
            }
            else if (Character.isDigit(checkPw)){
                digitCount++;
            }
        }
        if (upperCount < 1 && lowerCount < 1 && digitCount < 1){
            throw new UserException("the password must contain 1 upper and lower case and 1 digit !");
        }

        // TODO: als de gebruiker niet bestaat, maak een nieuwe gebruiker aan
        final EntityManager em = super.getFactory().createEntityManager();
        em.getTransaction().begin();
        UserEntity user = new UserEntity ( );

        user.setFName(firstName);
        user.setMName(middelName);
        user.setLName(lastName);
        user.setEmail(email);
        user.setHashedPassword(password);

        em.getTransaction().commit();
        super.close();

        return true;
    }

}
