package org.example;

import org.example.entity.Candidate;
import org.example.entity.Laptop;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class OneToOneApp {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = meta.getSessionFactoryBuilder().build();

        Session session = sf.openSession();
        Transaction t = session.beginTransaction();

        Laptop l = new Laptop();
        l.setBrand("Dell");

        Candidate c = new Candidate();
        c.setName("Chanchal Rani");
        c.setLaptop(l);   // link: candidate -> laptop

        session.persist(c);
        t.commit();
        session.close();

        Session s = sf.openSession();
        Candidate found = s.get(Candidate.class, 1);
        System.out.println(found.getName() + " has " + found.getLaptop().getBrand());

        s.close();
        sf.close();
    }
}
