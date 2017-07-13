package ru.itpark.onlineBanking.models;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountTransaction {

    private Date date;
    private String description;
    private String status;
    private double amount;
    private BigDecimal availableBalance;
    private String dateStr;

    public AccountTransaction() {
    }

    private SimpleDateFormat formatForDateStr = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Double getAvailableBalance() {
        return availableBalance.doubleValue();
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getDateStr() {
        return formatForDateStr.format(date);
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    @Override
    public String toString() {
        return "AccountTransaction{" +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                ", availableBalance=" + availableBalance +
                '}';
    }
}
