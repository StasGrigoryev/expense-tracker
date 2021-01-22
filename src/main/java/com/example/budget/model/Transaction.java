package com.example.budget.model;

import java.time.LocalDate;

public class Transaction extends BaseModel{

    private int cost;
    private LocalDate transactionDate;
    private int expenseId;
    private int expenseCategoryId;
    private String comment;

    public Transaction() {
    }

    public Transaction(int id) {
        super.id = id;
    }

    public Transaction(int id, int cost, LocalDate transactionDate, int expenseId,
                       int expenseCategoryId, String comment) {
        super(id);
        this.cost = cost;
        this.transactionDate = transactionDate;
        this.expenseId = expenseId;
        this.expenseCategoryId = expenseCategoryId;
        this.comment = comment;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getExpenseCategoryId() {
        return expenseCategoryId;
    }

    public void setExpenseCategoryId(int expenseCategoryId) {
        this.expenseCategoryId = expenseCategoryId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
