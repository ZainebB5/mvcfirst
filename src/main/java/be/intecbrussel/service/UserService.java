package be.intecbrussel.service;

import be.intecbrussel.model.UserEntity;
import jakarta.persistence.EntityManager;

import java.util.IllegalFormatCodePointException;

public class UserService extends AbstractRepository{

    private final UserRepository userRepository = new UserRepository();

    public boolean login(final String email, final String password){
        if( userRepository.existsByEmail(email)){
            throw new UserException(" User already exists ");
        }
        if ( ! userRepository.isActiveByEmail(email)){
            throw new UserException("The user with given email is deleted .");
        }
        return true;
    }

    public boolean register ( final String firstName,
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
        final EntityManager em = super.getEntitYManager();
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
    public static boolean isValidEmail(String emailAddress) {
        return emailAddress.contains(" ") == false && emailAddress.matches(".+@.+\\.[az]+");
    }

}
