package edu.matc.entity;

import com.sun.xml.bind.v2.TODO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * A user's note
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

    //TODO: figure out if this should be an int or an Item, and if an Item, how to configure that
    @Column(name = "item_id")
    private int item;

    @ManyToOne
    private User user;

    @Column(name = "note")
    private String noteContent;

    public Note(int item, User user, String noteContent) {
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
