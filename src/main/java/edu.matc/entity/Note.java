package edu.matc.entity;

import com.sun.xml.bind.v2.TODO;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A class to represent a user's note
 * @author jordynbx
 */
@Entity(name = "Note")
@Table(name = "notes")
@NoArgsConstructor
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @ManyToOne
    private Item item;

    @NonNull
    @ManyToOne
    private User user;

    @NonNull
    @Column(name = "note")
    private String noteContent;

    public Note(Item item, User user, String noteContent) {
        this.item = item;
        this.user = user;
        this.noteContent = noteContent;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", item=" + item +
                ", noteContent='" + noteContent + '\'' +
                '}';
    }
}
