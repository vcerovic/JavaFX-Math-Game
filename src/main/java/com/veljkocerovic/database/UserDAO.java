package com.veljkocerovic.database;

import com.veljkocerovic.exceptions.UsernameAlreadyExistsException;
import com.veljkocerovic.models.User;

import java.util.List;
import java.util.Optional;

import com.veljkocerovic.utils.AlertUtils;
import com.veljkocerovic.utils.HibernateUtils;
import javafx.scene.control.Alert;
import org.hibernate.query.Query;
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

    public static Optional<User> getUserByUsername(String username) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx;
        List<User> users = null;

        try {
            tx = session.beginTransaction();

            Query<User> query = session.createQuery("from User u where u.username = :username ", User.class);
            query.setParameter("username", username);
            users = query.list();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.close();
        }
        

        return Optional.ofNullable((users != null ? users.size() : 0) > 0 ? users.get(0) : null);
    }


    public static boolean saveUser(User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx;
        boolean success = false;

        try {
            tx = session.beginTransaction();

            if (getUserByUsername(user.getUsername()).isEmpty()) {
                session.persist(user);
                success = true;
            } else {
                String msg = "User with username: " + user.getUsername() + " already exists";
                AlertUtils.showAlertMessage(msg, Alert.AlertType.ERROR);
                throw new UsernameAlreadyExistsException(msg);
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.close();
        }

        return success;
    }

    public static void updateUser(int id, User user) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx;

        try {
            tx = session.beginTransaction();

            User foundUser = session.get(User.class, id);

            if (foundUser != null) {
                session.merge(user);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.close();
        }
    }

    public static void deleteUser(int id){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx;

        try {
            tx = session.beginTransaction();

            User foundUser = session.get(User.class, id);

            if (foundUser != null) {
                session.remove(foundUser);
            }

            AlertUtils.showAlertMessage("User successfully deleted.", Alert.AlertType.INFORMATION);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtils.close();
        }
    }

    public static void changeUsername(int id, User newUser) {
        if(getUserByUsername(newUser.getUsername()).isPresent()){
            AlertUtils.showAlertMessage("That username is already taken", Alert.AlertType.ERROR);
            throw new UsernameAlreadyExistsException("That username is already taken");
        }

        updateUser(id, newUser);
        AlertUtils.showAlertMessage("User successfully updated.", Alert.AlertType.INFORMATION);
    }
}
