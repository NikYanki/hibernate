import models.Car;
import models.Owner;
import models.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Test {
    public static void main(String[] args) {

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Car.class)
                .addAnnotatedClass(Owner.class)
                .getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory
                .openSession();

        session.beginTransaction();
        session.save(new Owner("Valera",new Car("audi", Type.SUV,210,20000,2020)));
        session.save(new Owner("Valera",new Car("audi", Type.SUV,210,20000,2020)));
        session.save(new Owner("Valera",new Car("audi", Type.SUV,210,20000,2020)));
        session.save(new Owner("Valera",new Car("audi", Type.SUV,210,20000,2020)));
        session.save(new Owner("Valera",new Car("audi", Type.SUV,210,20000,2020)));

        session.getTransaction().commit();

        session.createQuery("select o from Owner o",Owner.class).getResultList().forEach(owner -> System.out.println(owner));

        session.close();
        sessionFactory.close();
    }
}
