package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public boolean replace(Integer id, Item item) {
        Session session = sf.openSession();
        boolean isReplaced = false;
        try {
            session.beginTransaction();
            session.createQuery("UPDATE Item SET name = :iName WHERE id = :iId")
                    .setParameter("iName", item.getName())
                    .setParameter("iId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            isReplaced = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return isReplaced;
    }

    @Override
    public boolean delete(Integer id) {
        Session session = sf.openSession();
        boolean isReplaced = false;
        try {
            session.beginTransaction();
            session.createQuery("DELETE Item WHERE id = :iId")
                    .setParameter("iId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            isReplaced = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return isReplaced;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        List<Item> items = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery("FROM Item", Item.class);
            items = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        List<Item> items = new ArrayList<>();
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery("FROM Item WHERE name = :iName", Item.class);
            query.setParameter("iName", key);
            items = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    @Override
    public Item findById(Integer id) {
        Session session = sf.openSession();
        Item item = null;
        try {
            session.beginTransaction();
            Query<Item> query = session.createQuery("FROM Item WHERE id = :uId", Item.class);
            query.setParameter("uId", id);
            item = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}