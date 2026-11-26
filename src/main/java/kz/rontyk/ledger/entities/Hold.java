package kz.rontyk.ledger.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import kz.rontyk.ledger.entities.enums.HoldStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Table(name = "_hold")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hold extends AbstractEntity<Long>{

    public Hold(long amount, Instant expiresAt, Wallet wallet, Order order){
        this.amount = amount;
        this.holdStatus = HoldStatus.ACTIVE;
        this.expiresAt = expiresAt;
        this.wallet = wallet;
        this.order = order;
    }

    @Column(name = "amount", nullable = false)
    @Positive
    private long amount;

    @Column(name = "hold_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private HoldStatus holdStatus;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
