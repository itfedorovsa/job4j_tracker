package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class CacheDemo {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public void loadItems() {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            Item item1 = session.load(Item.class, 2);
            System.out.println("Item name: " + item1.getName());
            Item item2 = session.load(Item.class, 2);
            System.out.println("Item name: " + item2.getName());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public static void main(String[] args) {
        CacheDemo cacheDemo = new CacheDemo();
        cacheDemo.loadItems();
    }

}
