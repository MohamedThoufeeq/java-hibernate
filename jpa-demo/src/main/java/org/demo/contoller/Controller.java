package org.demo.contoller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.demo.contoller.util.JPAUtil;
import org.demo.model.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Controller {

    public void createUserInfoAndSave(String name, String mail) {
        EntityManager entityManager = JPAUtil.getEntityManager();
        UserInfo userInfo = new UserInfo(name, mail);
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userInfo);
            entityManager.getTransaction().commit();
            System.out.println("User "+name+", Transaction : "+true);
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
    // 2. NEW Method to READ data
    public void getAllUsers() {
        EntityManager entityManager = JPAUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            // HQL Query: Use the Class name "UserInfo", not the table name
            Query query = entityManager.createQuery("FROM UserInfo", UserInfo.class);
            List<UserInfo> users = query.getResultList();
            System.out.println("\n--- Database Contents ---");
            for (UserInfo user : users) {
                System.out.println(user); // This calls your toString() method
            }
            System.out.println("-------------------------");

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
