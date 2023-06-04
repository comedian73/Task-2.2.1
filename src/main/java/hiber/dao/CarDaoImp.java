package hiber.dao;

import hiber.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCar() {
        TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }
    @Override
    public void dropCarTable() {
        Session session = sessionFactory.openSession();
      Transaction transaction = session.beginTransaction();

      Query query = session.createSQLQuery("DROP TABLE IF EXISTS cars CASCADE ").addEntity(Car.class);
      query.executeUpdate();

      transaction.commit();
      session.close();
   }
}
