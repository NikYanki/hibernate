import models.Word;
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
                .addAnnotatedClass(Word.class)
                .getMetadataBuilder()
                .build();
        SessionFactory sessionFactory = metadata
                .getSessionFactoryBuilder()
                .build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(new Word("Privet"));
        session.save(new Word("Kak dela"));
        session.save(new Word("Poka"));


        session.getTransaction().commit();



        List<Word> words = session.createQuery("select w  from Word w",Word.class).getResultList();
        System.out.println(words);
        session.close();
        sessionFactory.close();
    }
}
