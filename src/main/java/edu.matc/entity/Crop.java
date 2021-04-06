package edu.matc.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * A class to represent an item
 *
 * @author jordynbx
 */

@Entity(name = "Crop")
@Table(name = "crops")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id",
            foreignKey = @ForeignKey(name = "crops_item_id_fk")
    )
    @NonNull
    private Item item;

    @Column(name = "season")
    private String season;

    @Column(name = "seed_price")
    private int seedPrice;

    @Column(name = "sell_price")
    private int sellPrice;

    @Column(name = "recipes")
    private String recipes;

    @Column(name = "bundles")
    private String bundles;
}
