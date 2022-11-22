package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HQLUsage {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (SessionFactory sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            Session session = sf.openSession();
            findAll(session);
            unique(session);
            findById(session, 6);
            update(session, 6);
            findById(session, 6);
            delete(session, 5);
            findAll(session);
            insert(session);
            findAll(session);
            session.close();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void findAll(Session session) {
        Query query = session.createQuery("FROM Item");
        for (Object st : query.list()) {
            System.out.println(st);
        }
    }

    public static void unique(Session session) {
        Query<Item> query = session.createQuery(
                "FROM Item i WHERE i.id = 6", Item.class);
        System.out.println(query.uniqueResult());
    }

    public static void findById(Session session, int id) {
        Query<Item> query = session.createQuery(
                "FROM Item as i WHERE i.id = :fId", Item.class);
        query.setParameter("fId", id);
        System.out.println(query.uniqueResult());
    }

    public static void update(Session session, int id) {
        try {
            session.beginTransaction();
            session.createQuery(
                            "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", "new name")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public static void delete(Session session, int id) {
        try {
            session.beginTransaction();
            session.createQuery(
                            "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    public static void insert(Session session) {
        try {
            session.beginTransaction();
            session.createQuery(
                            "INSERT INTO Item (name) VALUES (:fName)")
                    .setParameter("fName", "name1")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

}