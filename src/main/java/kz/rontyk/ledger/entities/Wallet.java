package kz.rontyk.ledger.entities;

import jakarta.persistence.*;
import kz.rontyk.ledger.entities.enums.Currency;
import kz.rontyk.ledger.entities.enums.SystemWalletCode;
import kz.rontyk.ledger.entities.enums.WalletType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "_wallet")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wallet extends AbstractEntity<Long> {

    private Wallet(long money, WalletType walletType, SystemWalletCode systemWalletCode, boolean allowNegative, Currency currency){
        this.money = money;
        this.walletType = walletType;
        this.systemWalletCode = systemWalletCode;
        this.allowNegative = allowNegative;
        this.currency = currency;
    }

    public static Wallet forUser(){
        return new Wallet(0, WalletType.USER, null, false, Currency.KZT);
    }

    public static Wallet forStore(){
        return new Wallet(0, WalletType.STORE, null, false, Currency.KZT);
    }

    @Column(name = "money", nullable = false)
    private long money;

    @Column(name = "wallet_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private WalletType walletType;


    @Enumerated(EnumType.STRING)
    @Column(name = "system_wallet_code")
    private SystemWalletCode systemWalletCode;


    @Column(name = "allow_negative", nullable = false)
    private boolean allowNegative;


    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;


    public void credit(long amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма зачисления должна быть больше нуля: " + amount);
        }
        this.money += amount;
    }

    public void debit(long amount){
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма денег должна быть больше нуля: " + amount);
        }
        if (!this.allowNegative){
            if (this.money - amount <0) throw new IllegalArgumentException("В кошелке недостаточна средств");
        }
        this.money -= amount;
    }
}
