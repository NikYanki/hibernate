import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        StandardServiceRegistry standardServiceRegistry =new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(standardServiceRegistry)
                .addAnnotatedClass(User.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//        session.save(new User("Mykola"));
//        session.save(new User("Serj"));
//
//
//        session.getTransaction().commit();


//        List<User> users = session.createNativeQuery("select *  from User",User.class).getResultList();
//        System.out.println(users);

//        List<User> users = session.createQuery("select u  from User u",User.class).getResultList();
//        System.out.println(users);
        User user = session.find(User.class, 1);
        User user1 = session.find(User.class, 2);
        session.beginTransaction();
        session.delete(user);
        session.delete(user1);
        session.getTransaction().commit();


        session.close();
        sessionFactory.close();
    }
}
