package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
   public User getUserByCar(Car car) {

      String hqlForCar = "FROM Car WHERE model = :model AND series = :series";
      String hqlForUser = "FROM User WHERE User = :user";

      Query queryFromCar = sessionFactory.openSession().createQuery(hqlForCar);
      queryFromCar.setParameter("model", car.getModel());
      queryFromCar.setParameter("series", car.getSeries());
      car = (Car) queryFromCar.getResultList().get(0);

      Query queryFromUser = sessionFactory.openSession().createQuery(hqlForUser);
      queryFromUser.setParameter("user", car.getSeries());

      return (User) queryFromUser.getResultList().get(0);
   }
}


