package org.example;

import org.example.entity.CollegeStudent;
import org.example.entity.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class OneToManyBidirectionalApp {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = meta.getSessionFactoryBuilder().build();

        Session session = sf.openSession();
        Transaction t = session.beginTransaction();

        Department obj = new Department();
        obj.setName("CSE");

        CollegeStudent obj1 = new CollegeStudent();
        obj1.setName("Chanchal");
        CollegeStudent obj2 = new CollegeStudent();
        obj2.setName("Harsh");

        obj1.setDepartment(obj); //link bi-directional
        obj2.setDepartment(obj);
        obj.getStud().add(obj1);
        obj.getStud().add(obj2);

        session.persist(obj); //only Department bcz cascade will save CollegeStudent

        t.commit();
        session.close();

        sf.close();

    }
}
