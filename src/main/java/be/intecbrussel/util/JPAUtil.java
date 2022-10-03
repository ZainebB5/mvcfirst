package be.intecbrussel.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "h2";


    private JPAUtil ( ) {

    }

    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory ( ) {

        final String env = "DEFAULT_DATABASE_VENDOR";

        if ( factory == null && System.getenv ( env ) == null ) {
            factory = Persistence.createEntityManagerFactory ( PERSISTENCE_UNIT_NAME );
        } else if ( factory == null && System.getenv ( env ) != null ) {
            factory = Persistence.createEntityManagerFactory ( System.getenv ( "DEFAULT_DATABASE_VENDOR" ) );
        }
        return factory;
    }

    public static void shutdown ( ) {
        if ( factory != null ) {
            factory.close ( );
        }
    }
}