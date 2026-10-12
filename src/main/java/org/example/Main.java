package org.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.example.entity.Student;
public class Main {
    public static void main(String[] args) {
        // loading Hibernate configuration
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Student.class);

        // creating SessionFactory and Session
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        // creating dummy student object for testing
        Student student = new Student();
        student.setId(2);
        student.setName("Rahul");
        student.setEmail("rahul@example.com");

        // saving in Database
        session.persist(student);

        transaction.commit();

        // Close connections
        session.close();
        sessionFactory.close();

        System.out.println("Data successfully saved and table created!");
    }
}
