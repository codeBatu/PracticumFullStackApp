package com.practicum.fttech.accountservice.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_user_account",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_account")
    @Column(name = "idAccount")
    private Long idAcc;
    @Column(name = "total_gold",columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal TotalGold;
    @Column(name = "total_dollar",columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal TotalDollar;
    @Column(name = "total_euro",columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal TotalEuro;
    @Column(name = "total_turkish_lira",columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal TotalTurkishLira;


    public Account() {
        this.is_active = true;
        this.TotalGold = new BigDecimal(0.00);
        this.TotalDollar = new BigDecimal(0.00);
        this.TotalEuro = new BigDecimal(0.00);
        this.TotalTurkishLira = new BigDecimal(0.00);

    }

    @Column(name = "is_active",columnDefinition = "boolean default false")
    private boolean is_active;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Transaction> transactions;


    private Long userId;
}