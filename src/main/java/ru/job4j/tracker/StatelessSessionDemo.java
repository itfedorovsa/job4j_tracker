package ru.job4j.tracker;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StatelessSessionDemo {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public void saveAndInstantUpdate() {
        StatelessSession ss = sf.openStatelessSession();
        try {
            ss.beginTransaction();
            Item item = new Item("New Item");
            ss.insert(item);
            System.out.println("Item id: " + item.getId() + ", Item name: " + item.getName());
            item.setName("Renamed Item");
            ss.update(item);
            ss.getTransaction().commit();
        } catch (Exception e) {
            ss.getTransaction().rollback();
        } finally {
            ss.close();
        }
    }

    public List<Item> findAll() {
        StatelessSession ss = sf.openStatelessSession();
        List<Item> items = new ArrayList<>();
        try {
            ss.beginTransaction();
            Query<Item> query = ss.createQuery("FROM Item", Item.class);
            items = query.list();
            ss.getTransaction().commit();
        } catch (Exception e) {
            ss.getTransaction().rollback();
        } finally {
            ss.close();
        }
        return items;
    }

    public Item findById(Integer id) {
        StatelessSession ss = sf.openStatelessSession();
        Item item = null;
        try {
            ss.beginTransaction();
            Query<Item> query = ss.createQuery("FROM Item WHERE id = :iId", Item.class);
            query.setParameter("iId", id);
            item = query.uniqueResult();
            ss.getTransaction().commit();
        } catch (Exception e) {
            ss.getTransaction().rollback();
        } finally {
            ss.close();
        }
        return item;
    }

    public static void main(String[] args) {
        StatelessSessionDemo ssd = new StatelessSessionDemo();
        Item item1 = ssd.findById(4);
        System.out.println("Item1 id: " + item1.getId() + ", Item1 name: " + item1.getName());
        Item item2 = ssd.findById(4);
        System.out.println("Item2 id: " + item2.getId() + ", Item2 name: " + item2.getName());
    }

}
