package kz.rontyk.ledger.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import kz.rontyk.ledger.entities.enums.Currency;
import kz.rontyk.ledger.entities.enums.SystemWalletCode;
import kz.rontyk.ledger.entities.enums.TransferStatus;
import kz.rontyk.ledger.entities.enums.TransferType;
import lombok.*;

import java.util.Objects;

@Table(name = "_transfer")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Transfer extends AbstractEntity<Long>{

    private Transfer(long money, Wallet fromWallet, Wallet toWallet, Order order, TransferType transferType){
        if (Objects.equals(fromWallet.getId(), toWallet.getId())) throw new IllegalArgumentException("You cannot transfer money from you to you");
        if (money <= 0) throw new IllegalArgumentException("You cannot transfer money lower than 1");
        if (fromWallet.getCurrency() != toWallet.getCurrency()) throw new IllegalArgumentException("You cannot transfer money to different currency wallets");
        this.money = money;
        this.fromWallet = fromWallet;
        this.toWallet = toWallet;
        this.order = order;
        this.transferStatus = TransferStatus.PENDING;
        this.transferType = transferType;
        this.currency = fromWallet.getCurrency();
    }

    public static Transfer p2p(Wallet fromWallet, Wallet toWallet, long money){
        return new Transfer(money, fromWallet, toWallet, null, TransferType.P2P);
    }

    public static Transfer topUp(Wallet fromWallet, Wallet toWallet, long money){
        if (fromWallet.getSystemWalletCode() != SystemWalletCode.EXTERNAL) throw new IllegalArgumentException("You cannot create TOPUP transfer from not external wallet");
        return new Transfer(money, fromWallet, toWallet, null, TransferType.TOPUP);
    }

    public static Transfer withdrawal(Wallet fromWallet, Wallet toWallet, long money){
        if (toWallet.getSystemWalletCode() != SystemWalletCode.EXTERNAL) throw new IllegalArgumentException("You cannot create WITHDRAWAL transfer to not external wallet");
        return new Transfer(money, fromWallet, toWallet, null, TransferType.WITHDRAWAL);
    }

    public static Transfer payment(Wallet fromWallet, Wallet toWallet, long money, Order order){
        if (order == null) throw new IllegalArgumentException("order cannot be null");
        return new Transfer(money, fromWallet, toWallet, order, TransferType.PAYMENT);
    }

    public static Transfer refund(Wallet fromWallet, Wallet toWallet, long money, Order order){
        if (order == null) throw new IllegalArgumentException("order cannot be null");
        return new Transfer(money, fromWallet, toWallet, order, TransferType.REFUND);
    }


    @Column(name = "money", nullable = false)
    @Positive
    private long money;

    @Column(name = "transfer_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Setter
    private TransferStatus transferStatus;

    @Column(name = "transfer_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_wallet", nullable = false)
    private Wallet fromWallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_wallet", nullable = false)
    private Wallet toWallet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

}
