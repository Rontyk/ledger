package kz.rontyk.ledger.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "_card")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Card extends AbstractEntity<Long> {

    @Column(name = "last4", nullable = false)
    private String last4;

    @Column(name = "token", unique = true, nullable = false)
    private String token;

    @Column(name = "exp_month", nullable = false)
    private short expMonth;

    @Column(name = "exp_year", nullable = false)
    private short expYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
