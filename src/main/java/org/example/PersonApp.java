package org.example;

import org.example.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class PersonApp {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = meta.getSessionFactoryBuilder().build();

        //save
        Session session = sf.openSession();
        Transaction t = session.beginTransaction();
        Person obj = new Person();
        obj.setName("Chanchal Rani");
        obj.setAge(23);
        obj.setId(101);

        Person obj1 = new Person();
        obj1.setName("Iram Fatima");
        obj1.setAge(69);
        obj1.setId(102);

        Person obj2 = new Person();
        obj2.setName("Kushaid Shamim");
        obj2.setAge(10);
        obj2.setId(103);

        Person obj3 = new Person();
        obj3.setName("Neha Mishra");
        obj3.setAge(33);
        obj3.setId(104);
        session.persist(obj);
        session.persist(obj1);
        session.persist(obj2);
        session.persist(obj3);
        t.commit();
        session.close();

        Session s1 = sf.openSession();
        Query<Person> query = s1.createQuery("FROM Person", Person.class);
        List<Person> results = query.list();

        for (Person p : results) {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getAge());
        }

        System.out.println("-------AS Clause--------");
        Query<Person> q = s1.createQuery("FROM Person AS P", Person.class);
        List<Person> res = q.list();
        for (Person e : res) {
            System.out.println(e.getId() + " " + e.getName() + " " + e.getAge());
        }

        System.out.println("-------WHERE Clause-------");
        List<Person> res1 = s1.createQuery("FROM Person WHERE age = 69", Person.class).list();
        for (Person c : res1) {
            System.out.println(c.getId() + " " + c.getName() + " " + c.getAge());
        }

        System.out.println("-------ORDER BY Clause-------");
        List<Person> res2 = s1.createQuery("FROM Person ORDER BY age DESC", Person.class).list();
        for (Person d : res2) {
            System.out.println(d.getId() + " " + d.getName() + " " + d.getAge());
        }
        s1.close();
        sf.close();
    }
}
