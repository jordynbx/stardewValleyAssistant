package edu.matc.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent a user search
 */
@Entity(name = "UserSearch")
@Table(name = "user_searches")
@NoArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class UserSearch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

//    @ManyToMany
//    @JoinTable(name = "user",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "id")}
//    )
//    private Set<User> users = new HashSet<User>();
    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "user_searches_user_id_fk"))
    private User user;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "item_id",
            foreignKey = @ForeignKey(name = "user_searches_item_id_fk"))
    private Item item;
}
