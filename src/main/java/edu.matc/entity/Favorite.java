package edu.matc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * The type Favorite.
 */
@Entity(name = "Favorite")
@Table(name = "user_favorites")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    //@Column(name = "user_id")
    @NonNull
    private int userId;

    //@Column(name = "item_id")
    @NonNull
    private int itemId;

    @ManyToMany
    private User user;
}
