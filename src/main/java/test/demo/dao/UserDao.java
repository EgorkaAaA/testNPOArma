package test.demo.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import test.demo.Entities.Users;
import test.demo.Hebirnate.HibernateSessionFactoryUtil;

@Component
public class UserDao {
    public Users findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Users.class, id);
    }

    public Users save(Users user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        if(session.createQuery("from Users where login = :userLogin",Users.class).setParameter("userLogin",user.getLogin()).list().size() == 0) {
            session.save(user);
        } else {
            return null;
        }
        tx1.commit();
        session.close();
        return findById(user.getUser_id());
    }

    public Users update(Users user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
        return findById(user.getUser_id());
    }

    public Users authUser(Users user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Users userForAuth = session.createQuery("from Users where login = :userLogin",Users.class).setParameter("userLogin",user.getLogin()).list().get(0);
        tx1.commit();
        session.close();
        return userForAuth.getPassword().equals(user.getPassword()) ? userForAuth : null;
    }
}
