package org.demo.contoller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContexts;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.demo.model.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class Controller implements CommandLineRunner {
    @PersistenceContext
    EntityManager entityManager;

    public void createUserInfoAndSave(String name, String mail) {
        UserInfo userInfo = new UserInfo(name, mail);
        try {
            entityManager.persist(userInfo);
            System.out.println("User "+name+", Transaction : "+true);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void getAllUsers() {
        try {
            Query query = entityManager.createQuery("FROM UserInfo", UserInfo.class);
            List<UserInfo> users = query.getResultList();
            System.out.println("\n--- Database Contents ---");
            for (UserInfo user : users) {
                System.out.println(user); // This calls your toString() method
            }
            System.out.println("-------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        createUserInfoAndSave("john", "hoj@gmail.com");
        createUserInfoAndSave("bob", "hoj@gmail.com");
        getAllUsers();
    }
}
