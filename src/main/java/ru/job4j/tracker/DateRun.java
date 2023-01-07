package ru.job4j.tracker;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Date and time handle demo class
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 07.01.2023
 */
public class DateRun {
    public static void main(String[] args) {
        var registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try (var sf = new MetadataSources(registry)
                .buildMetadata().buildSessionFactory()) {
            var session = sf.openSession();
            session.beginTransaction();
            var stored = session.createQuery(
                    "from Item", Item.class
            ).list();
            for (Item it : stored) {
                var time = it.getCreated().atZone(
                        ZoneId.of("UTC+3")
                ).format(DateTimeFormatter.ofPattern("HH:mm yyyy-MM-dd"));
                System.out.println(time);
            }
            var item = new Item();
            item.setName("check timezone");
            item.setCreated(LocalDateTime.now());
            session.persist(item);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}