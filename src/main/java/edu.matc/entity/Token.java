package edu.matc.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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
    @OneToOne(mappedBy = "user")
    private User user;

    @NonNull
    @Column(name="token")
    private String token;

    @NonNull
    @Column(name="expiration")
    private String expiration;
}
