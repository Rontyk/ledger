package kz.rontyk.ledger.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import kz.rontyk.ledger.entities.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Table(name = "_order")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends AbstractEntity<Long>{

    public Order(long totalAmount, User user, Store store){
        this.totalAmount = totalAmount;
        this.user = user;
        this.store = store;
        this.orderStatus = OrderStatus.CREATED;
    }

    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "total_amount", nullable = false)
    @Positive
    private long totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
