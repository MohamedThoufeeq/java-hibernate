package org.demo.contoller.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;

public class JPAUtil {
    private static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory("my-jpa-unit");
    }

    public  static  EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    public  static void close(){
        emf.close();
    }
}
