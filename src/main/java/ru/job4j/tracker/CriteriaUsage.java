package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CriteriaUsage {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    public List<Item> findAll() {
        Session session = sf.openSession();
        List<Item> items = new ArrayList<>();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Item> criteriaQuery = builder.createQuery(Item.class);
            criteriaQuery.select(criteriaQuery.from(Item.class));
            Query<Item> query = session.createQuery(criteriaQuery);
            items = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    public List<Item> findByName(String key) {
        Session session = sf.openSession();
        List<Item> items = new ArrayList<>();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Item> criteriaQuery = builder.createQuery(Item.class);
            Root<Item> root = criteriaQuery.from(Item.class);
            criteriaQuery.select(root).where(builder.like(root.get("name"), key));
            Query<Item> query = session.createQuery(criteriaQuery);
            items = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    public List<Item> findByNamesDesc(String key1, String key2) {
        Session session = sf.openSession();
        List<Item> items = new ArrayList<>();
        try {
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Item> criteriaQuery = builder.createQuery(Item.class);
            Root<Item> root = criteriaQuery.from(Item.class);
            Predicate likeByKey1 = builder.like(root.get("name"), key1);
            Predicate likeByKey2 = builder.like(root.get("name"), key2);
            criteriaQuery.select(root)
                    .where(builder.or(likeByKey1, likeByKey2))
                    .orderBy(builder.desc(root.get("id")));
            Query<Item> query = session.createQuery(criteriaQuery);
            items = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return items;
    }

    public static void main(String[] args) {
        CriteriaUsage criteriaTracker = new CriteriaUsage();
        System.out.println("All items: ");
        List<Item> items = criteriaTracker.findAll();
        for (Item item : items) {
            System.out.println("Item id: " + item.getId() + ", Item name: " + item.getName());
        }

        List<Item> itemsByName = criteriaTracker.findByName("%1%");
        System.out.println("Items by name %1%: ");
        for (Item item : itemsByName) {
            System.out.println("Item id: " + item.getId() + ", Item name: " + item.getName());
        }

        List<Item> itemsByNames = criteriaTracker.findByNamesDesc("%3%", "%1%");
        System.out.println("Items by name %3% and %1%: ");
        for (Item item : itemsByNames) {
            System.out.println("Item id: " + item.getId() + ", Item name: " + item.getName());
        }
    }

}
