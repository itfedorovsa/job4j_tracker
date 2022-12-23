package ru.job4j.toone;

import javax.persistence.*;
import lombok.*;
import lombok.EqualsAndHashCode.Include;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user_notifications")
public class UserMessenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private int id;
    private String messenger;
    private String identify;
}