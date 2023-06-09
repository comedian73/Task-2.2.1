package hiber.dao;

import hiber.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void dropUsersTable() {
      Session session = sessionFactory.openSession();
      Transaction transaction = session.beginTransaction();

      Query query = session.createSQLQuery("DROP TABLE IF EXISTS users CASCADE").addEntity(User.class);
      query.executeUpdate();

      transaction.commit();
      session.close();
   }

}
