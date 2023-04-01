package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class NamedQueryUsage {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public List<Item> findAll() {
        Session session = sf.openSession();
        List<Item> items = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Item> query = session.getNamedQuery("getItems");
            items = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    public Item findById(Integer id) {
        Session session = sf.openSession();
        Item item = null;
        try {
            session.beginTransaction();
            Query<Item> query = session.getNamedQuery("getItemById");
            query.setParameter("iId", id);
            item = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    public static void main(String[] args) {
        NamedQueryUsage namedQueryUsage = new NamedQueryUsage();
        List<Item> items = namedQueryUsage.findAll();
        System.out.println("All items: ");
        for (Item item : items) {
            System.out.println("Item id: " + item.getId() + ", Item name: " + item.getName());
        }

        Item itemById = namedQueryUsage.findById(2);
        System.out.println("Item by id 2: ");
        System.out.println("Item id: " + itemById.getId() + ", Item name: " + itemById.getName());
    }

}
