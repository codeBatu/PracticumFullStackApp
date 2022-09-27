package com.practicum.fttech.accountservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    private  Long id;

@Column(name = "transaction_description")
    private  String TransactionDescription;
    @Column(name = "transaction_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date TransactionDate;
    @Column(name = "transaction_type")
    @Enumerated(EnumType.ORDINAL)
    private  TransactionType TransactionType;



    @ManyToOne
    private Account accounts;

}


