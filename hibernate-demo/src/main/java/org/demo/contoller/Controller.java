package org.demo.contoller;

import org.demo.contoller.util.HibernateUtil;
import org.demo.model.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

import static org.demo.contoller.util.HibernateUtil.closeSession;
import static org.demo.contoller.util.HibernateUtil.getSession;

public class Controller {

    public void createUserInfoAndSave(String name, String mail) {
        Session session = getSession();
        UserInfo userInfo = new UserInfo(name, mail);
        try {
            session.beginTransaction();
            session.persist(userInfo);
            session.getTransaction().commit();
            boolean wasSuccessful = session.getTransaction().wasSuccessful();
            System.out.println("User "+name+", Transaction : "+wasSuccessful);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    // 2. NEW Method to READ data
    public void getAllUsers() {
        Session session = getSession();
        try {
            session.beginTransaction();
            // HQL Query: Use the Class name "UserInfo", not the table name
            Query<UserInfo> query = session.createQuery("FROM UserInfo", UserInfo.class);
            List<UserInfo> users = query.list();
            System.out.println("\n--- Database Contents ---");
            for (UserInfo user : users) {
                System.out.println(user); // This calls your toString() method
            }
            System.out.println("-------------------------");

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
