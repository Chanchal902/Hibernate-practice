package org.example;

import org.example.entity.Students;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Work {
    public static void main(String[] args) {
        // creating an object of the Students class and setting the values
        Students obj = new Students();
        obj.setId(101);
        obj.setName("Chanchal Rani");
        obj.setMarks(81);

        Students obj1 = new Students();
        obj1.setId(102);
        obj1.setName("Harshh Sinha");
        obj1.setMarks(96);

        Students obj2 = new Students();
        obj2.setId(103);
        obj2.setName("Iram Darindi");
        obj2.setMarks(78);

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = meta.getSessionFactoryBuilder().build();
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();
        session.persist(obj);
        t.commit();
        sf.close();
        session.close();
    }
}
