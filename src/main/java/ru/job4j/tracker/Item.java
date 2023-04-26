package ru.job4j.tracker;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.job4j.toone.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "getItems",
                query = "FROM Item"

        ),
        @NamedQuery(
                name = "getItemById",
                query = "FROM Item WHERE id = :iId"

        )}
)
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Type(type = "ru.job4j.tracker.customtypes.LocalDateTimeToString")
    private LocalDateTime created = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "participates",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> participates;

    public Item(String name) {
        this.name = name;
    }

    public Item(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(Integer id, String name, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

}