package edu.matc.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type Favorite.
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

    //@Column(name = "user_id")
    @NonNull
    @ManyToOne
    private User user;

    //@Column(name = "item_id")
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
