package com.example.demo.model;

import java.util.Date;

import javax.persistence.*;

@Entity(name = "transactions")
@Table(name = "transactions")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "userid")
    private int userId;
    @Column(name = "type")
    private int type;
    @Column(name = "trxvalue")
    private long trxValue;
    @Column(name = "trxdate")
    private Date trxDate;

    public Transaction(int userId, int type, long trxValue, Date trxDate) {
        this.userId = userId;
        this.type = type;
        this.trxValue = trxValue;
        this.trxDate = trxDate;
    }

    public Transaction() {

    }

    public int getUserId() {
        return userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getTrxValue() {
        return trxValue;
    }

    public void setTrxValue(long trxValue) {
        this.trxValue = trxValue;
    }

    public Date getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(Date trxDate) {
        this.trxDate = trxDate;
    }
}
