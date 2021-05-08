package edu.matc.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * A class to represent a unique token
 * @author jordynbx
 */
@Entity(name = "Token")
@Table(name = "tokens")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "tokens_user_id_fk")
    )
    private User user;

    @NonNull
    @Column(name="token")
    private String token;

    @NonNull
    @Column(name="expiration")
    private Timestamp expiration;
}
