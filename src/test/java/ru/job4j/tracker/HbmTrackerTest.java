package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HbmTrackerTest {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private final Session session = sf.openSession();

    @After
    public void wipeTable() {
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM Item")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenAddTwoItemsAndFindAllThenSizeIs2() {
        try (var tracker = new HbmTracker()) {
            tracker.add(new Item("item1"));
            tracker.add(new Item("item2"));
            Assert.assertThat(tracker.findAll().size(), is(2));
        }
    }

    @Test
    public void whenAddOneAndDeleteOneThenSizeIs0() {
        try (var tracker = new HbmTracker()) {
            tracker.add(new Item("item"));
            Item rsl = tracker.findByName("item").get(0);
            tracker.delete(rsl.getId());
            Assert.assertThat(tracker.findAll().size(), is(0));
        }
    }

    @Test
    public void whenAddTwoItemsAndDeleteOneThenSizeIs1() {
        try (var tracker = new HbmTracker()) {
            tracker.add(new Item("item1"));
            tracker.add(new Item("item2"));
            Item rsl = tracker.findByName("item1").get(0);
            tracker.delete(rsl.getId());
            Assert.assertThat(tracker.findAll().size(), is(1));
        }
    }

    @Test
    public void whenAddAndReplace() {
        try (var tracker = new HbmTracker()) {
            Item item1 = tracker.add(new Item("item1"));
            Item item2 = new Item("item2");
            tracker.replace(item1.getId(), item2);
            Assert.assertThat(tracker.findById(item1.getId()).getName(), is("item2"));
        }
    }

    @Test
    public void whenAddAndFindByName() {
        try (var tracker = new HbmTracker()) {
            tracker.add(new Item("item"));
            String itemNameFromDB = tracker.findByName("item").get(0).getName();
            Assert.assertThat(itemNameFromDB, is("item"));
        }
    }
}