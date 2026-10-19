package org.example;

import org.example.entity.Author;
import org.example.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class LibraryApp {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sf = meta.getSessionFactoryBuilder().build();

        Session s = sf.openSession();
        Transaction t = s.beginTransaction();

        Author auth = new Author();
        auth.setName("Ravi Kishan");
        auth.setCountry("India");

        Author auth1 = new Author();
        auth1.setName("Charles Decky");
        auth1.setCountry("Russia");

        Author auth2 = new Author();
        auth2.setName("sam Rachael");
        auth2.setCountry("New York");

        Book obj = new Book();
        obj.setTitle("Strange Hobby");
        obj.setPrice(1249.00);
        obj.setAvailable(true);

        Book obj1 = new Book();
        obj1.setTitle("The Second Chance");
        obj1.setPrice(1599.00);
        obj1.setAvailable(true);

        Book obj2 = new Book();
        obj2.setTitle("Remember Me!");
        obj2.setPrice(999.00);
        obj2.setAvailable(true);

        Book obj3 = new Book();
        obj3.setTitle("The Immortal");
        obj3.setPrice(499.00);
        obj3.setAvailable(true);

        obj.setAuthor(auth2);  //link
        obj1.setAuthor(auth);  //link
        obj2.setAuthor(auth2);  //link
        obj3.setAuthor(auth1);  //link

        s.persist(auth);
        s.persist(auth1);
        s.persist(auth2);

        s.persist(obj);
        s.persist(obj1);
        s.persist(obj2);
        s.persist(obj3);

        t.commit();
        s.close();

        //HQL
        Session s1 = sf.openSession();
        Query<Book> query = s1.createQuery("FROM Book", Book.class);
        List<Book> res = query.list();

        for (Book b : res) {
            System.out.println(b.getId() + " , " + b.getTitle() + " , " + b.getPrice() + " , " + b.isAvailable());
        }
        s1.close();

        //GET BY ID
        Session s2 = sf.openSession();
        Book found = s2.get(Book.class, 2);
        if (found != null)
            System.out.println(found.getAuthor().getName() + " , " + found.getTitle() + " , " + found.getPrice() + " , " + found.isAvailable());
        else System.out.println("Book Not Found!");
        s2.close();

        //UPDATE
        Session s3 = sf.openSession();
        Transaction t1 = s3.beginTransaction();
        if (found != null) {
            found.setPrice(2499);
            s3.merge(found);
            System.out.println(found.getAuthor().getName() + " , " + found.getTitle() + " , " + found.getPrice() + " , " + found.isAvailable());
        } else {
            System.out.println("Book Not Found! Update Failed!");
        }
        t1.commit();
        s3.close();

        //DELETE
        Session s4 = sf.openSession();
        Transaction t4 = s4.beginTransaction();
        Book toDelete = s4.get(Book.class, 4);
        if (toDelete != null) {
            s4.remove(toDelete);
        }
        t4.commit();
        s4.close();

        //HQL
        Session s5 = sf.openSession();
        Query<Book> query1 = s5.createQuery("FROM Book WHERE author.id = 3", Book.class);
        List<Book> res1 = query1.list();
        for (Book c : res1) {
            System.out.println(c.getTitle() + " , " + c.getPrice() + " , " + c.isAvailable());
        }
        s5.close();

        //HQL
        Session s6 = sf.openSession();
        Query<Book> query2 = s6.createQuery("FROM Book ORDER BY price", Book.class);
        List<Book> res2 = query2.list();
        for (Book d : res2) {
            System.out.println(d.getTitle() + " , " + d.getPrice() + " , " + d.isAvailable());
        }
        s6.close();

        // ISSUE: available = false
        Session s7 = sf.openSession();
        Transaction t7 = s7.beginTransaction();
        Book b = s7.get(Book.class, 1);
        if (b != null && b.isAvailable()) {
            b.setAvailable(false);
            s7.merge(b);
            System.out.println("Book issued");
        } else {
            System.out.println("Book not available");
        }
        t7.commit();
        s7.close();

        sf.close();

    }
}
