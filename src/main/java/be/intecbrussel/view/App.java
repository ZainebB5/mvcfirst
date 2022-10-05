package be.intecbrussel.view;

import be.intecbrussel.model.UserEntity;

import be.intecbrussel.service.MessageRepository;
import be.intecbrussel.service.UserRepository;
import be.intecbrussel.service.UserService;

import static java.lang.System.out;

public class App
{
    private  static final UserRepository userRepository = new UserRepository();
    private  static final UserService userService = new UserService();
    private static final MessageRepository messageRepository = new MessageRepository();


    public static void main( String[] args ) {

        UserEntity u1 = new UserEntity ( );
        u1.setFName ( "Amina" );
        u1.setLName ( "Bak" );
        u1.setEmail ( "Amina@bak.be" );
        u1.setHashedPassword ( "A1b23456" );
        u1.setValidation ( "12345678" );
        u1.setActive ( true );

        UserEntity u2 = new UserEntity ( );
        u2.setFName ( "Sakina" );
        u2.setLName ( "Bak" );
        u2.setEmail ( "Sakina@gmail.be" );
        u2.setHashedPassword ( "A1b23456" );
        u2.setValidation ( "12345678" );
        u2.setActive ( true );

        UserEntity u3 = new UserEntity ( );
        u3.setFName ( "Kawthar" );
        u3.setLName ( "Bak" );
        u3.setEmail ( "Kawthar@gmail.be" );
        u3.setHashedPassword ( "A1b23456" );
        u3.setValidation ( "12345678" );
        u3.setActive ( false );

        userRepository.create ( u1 );
        userRepository.create ( u2 );
        userRepository.create ( u3);
        out.println("##".repeat(30));
        out.println(userRepository.existsByEmail("Kawthar@gmail.be"));
        out.println("##".repeat(30));
        out.println(userRepository.isActiveByEmail("Sakina@gmail.be"));
        out.println("##".repeat(30));
        out.println(userService.login("KawtharGGG@gmail.be","A1b23456"));





    }
}
