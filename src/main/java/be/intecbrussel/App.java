package be.intecbrussel;

import be.intecbrussel.model.UserEntity;

import be.intecbrussel.service.MessageRepository;
import be.intecbrussel.service.UserRepository;

import static java.lang.System.out;

public class App
{
    private  static final UserRepository repositoryU = new UserRepository();
    private static final MessageRepository repositoryM = new MessageRepository();


    public static void main( String[] args ) {
        final var u1 = new UserEntity();
        u1.setFName("Amina");
        u1.setMName("Sakina");
        u1.setLName("Kawthar");
        u1.setEmail("amsaka@gmail.com");
        u1.setHashedPassword("tototatatiti");
        u1.setValidation("12345678");
        u1.setActive(true);

        repositoryU.save( u1 );

        final var u2 = new UserEntity();



        repositoryU.findAll ( ).forEach ( out :: println );




    }
}
