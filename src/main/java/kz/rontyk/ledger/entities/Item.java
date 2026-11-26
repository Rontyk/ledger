package kz.rontyk.ledger.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Table(name = "_item")
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends AbstractEntity<Long>{

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "article", nullable = false)
    private String article;

    @Column(name = "rating", nullable = false)
    @Max(value = 10, message = "rating cannot be more than 10")
    @Min(value = 0, message = "rating cannot be lower than 0")
    private int rating;

    @Column(name = "amount", nullable = false)
    @Min(value = 0)
    private int amount;

    @Column(name = "price", nullable = false)
    @Min(value = 0)
    private long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
