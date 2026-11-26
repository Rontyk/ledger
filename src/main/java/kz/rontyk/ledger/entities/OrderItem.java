package kz.rontyk.ledger.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Table(name = "_order_item")
@Entity
@Getter
@Setter
public class OrderItem extends AbstractEntity<Long> {

    @Column(name = "item_amount", nullable = false)
    @Positive
    private int itemAmount;

    @Column(name = "price", nullable = false)
    @Min(value = 0)
    private long price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
