package be.intecbrussel.view;

import be.intecbrussel.model.MessageEntity;
import be.intecbrussel.model.UserEntity;

import be.intecbrussel.service.MessageRepository;
import be.intecbrussel.service.UserRepository;
import be.intecbrussel.service.UserService;

import java.util.List;

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

        UserEntity u4 = new UserEntity ( );
        u3.setFName ( "Zahra" );
        u3.setLName ( "Bak" );
        u3.setEmail ( "Kawthar@gmail.be" );
        u3.setHashedPassword ( "A1b23456" );
        u3.setValidation ( "12345678" );
        u3.setActive ( true );

        UserEntity u5 = new UserEntity ( );
        u3.setFName ( "Hassan" );
        u3.setLName ( "Bak" );
        u3.setEmail ( "Hassan@gmail.be" );
        u3.setHashedPassword ( "A1b23456" );
        u3.setValidation ( "12345678" );
        u3.setActive ( true );

        userRepository.create ( u1 );
        userRepository.create ( u2 );
        userRepository.create ( u3);

        MessageEntity m1 = new MessageEntity();
        m1.setSender(u1);
        m1.setSubject("test");
        m1.setContent("intectest");
        m1.setActive(true);
        m1.setLikesCount(3);
        m1.setViewsCount(4);
        messageRepository.create(m1);
        out.println("##".repeat(30));

        out.println(messageRepository.findById(null));;

        out.println("##".repeat(30));
        List<UserEntity> users = userRepository.findAll();
        for(UserEntity u : users){
            out.println(u.getEmail());
        }
        out.println("##".repeat(30));
        out.println("Exist by email : " + userRepository.existsByEmail("Kawthar@gmail.be"));
        out.println("##".repeat(30));
        out.println("Active by email : " + userRepository.isActiveByEmail("Sakina@gmail.be"));
        out.println("##".repeat(30));
        UserService userService = new UserService();
        userService.login("Kawthar@gmail.be", "A1b23456");







    }
}
