package kz.rontyk.ledger.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Table(name = "_store")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends AbstractEntity<Long> {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "rating")
    @Max(value = 10, message = "rating cannot be more than 10")
    @Min(value = 0, message = "rating cannot be lower than 0")
    private int rating;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private Wallet wallet;


}
