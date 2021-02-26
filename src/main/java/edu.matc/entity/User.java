package edu.matc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent a user
 */
@Entity(name = "User")
@Table(name = "user")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "username")
    @NonNull
    private String username;

    @Column(name = "password")
    @NonNull
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Favorite> favorites = new HashSet<>();

    /**
     * Add favorite.
     *
     * @param favorite the favorite
     */
    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
        favorite.setUser(this);
    }

    /**
     * Remove order.
     *
     * @param favorite the favorite
     */
    public void removeFavorite(Favorite favorite) {
        favorites.remove(favorite);
        favorite.setUser(null);
    }
}
