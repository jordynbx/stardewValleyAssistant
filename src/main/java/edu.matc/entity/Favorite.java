package edu.matc.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class to represent a favorite item
 * @author jordynbx
 */
@Entity(name = "Favorite")
@Table(name = "user_favorites")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    @ManyToOne
    private Item item;

    @Override
    public String toString() {
        return "Favorite{" +
                "id=" + id +
                ", item=" + item +
                '}';
    }
}
