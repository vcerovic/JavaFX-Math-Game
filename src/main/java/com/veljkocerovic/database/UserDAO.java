package com.veljkocerovic.database;

import com.veljkocerovic.models.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.veljkocerovic.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAO {

    public static List<User> getAllUsersByHighscore() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx;
        List<User> users = null;

        try {
            tx = session.beginTransaction();

            users = session.createQuery("FROM User as user ORDER BY user.highscore DESC", User.class).list();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.close();
        }

        return users;
    }
}
