package org.example;

import org.example.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Empp {
    public static void main(String[] args) {

        //SessionFactory created only one time
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = meta.getSessionFactoryBuilder().build();

        //save
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();
        Employee obj = new Employee();
        obj.setId(101);
        obj.setName("Harsh Sinha");
        obj.setSalary(56000);
        Employee obj1 = new Employee();
        obj1.setId(102);
        obj1.setName("Chanchal Rani");
        obj1.setSalary(51000);
        session.persist(obj);
        session.persist(obj1);
        t.commit();
        session.close();

        //GET BY Id
        Session s2 = sf.openSession();
        Employee found = s2.get(Employee.class, 102); //found -> object
        System.out.println(found.getName() + " " + found.getSalary());
        s2.close();

        //UPDATE
        Session s3 = sf.openSession();
        Transaction t3 = s3.beginTransaction();
        found.setSalary(63000);
        s3.merge(found);
        System.out.println(found.getName() + " " + found.getSalary());
        t3.commit();
        s3.close();

        //DELETE
        Session s4 = sf.openSession();
        Transaction t4 = s4.beginTransaction();
        Employee toDelete = s4.get(Employee.class, 101);
        if (toDelete != null) {
            s4.remove(toDelete);
        }
        t4.commit();
        s4.close();

        sf.close();
    }
}
