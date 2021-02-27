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
    private Set<Note> notes = new HashSet<>();

    /**
     * Add note.
     *
     * @param note the note
     */
    public void addNote(Note note) {
        notes.add(note);
        note.setUser(this);
    }

    /**
     * Remove note.
     *
     * @param note the note
     */
    public void removeNote(Note note) {
        notes.remove(note);
        note.setUser(null);
    }
}
