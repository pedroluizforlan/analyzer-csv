package com.plfp.model;

import java.time.LocalDate;
import java.util.Objects;

public class BankTransaction {
    private Long id;
    private Double amount;
    private String description;
    private String bank;
    private LocalDate date;

    public BankTransaction(Long id, Double value, String description, String bank, LocalDate date) {
        this.id = id;
        this.amount = value;
        this.description = description;
        this.bank = bank;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof BankTransaction that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getAmount(), that.getAmount()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getBank(), that.getBank()) && Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAmount(), getDescription(), getBank(), getDate());
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", bank='" + bank + '\'' +
                ", date=" + date +
                '}';
    }
}