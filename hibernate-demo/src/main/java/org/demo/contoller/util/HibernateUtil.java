package org.demo.contoller.util;

import org.demo.model.UserInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        sessionFactory= new Configuration()
                .configure("/hibernate.cfg.xml")
                .addAnnotatedClass(UserInfo.class)
                .buildSessionFactory();
    }
    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }
    public static void closeSession(){
        sessionFactory.close();
    }
}
