package com.example.budget.model;

public class Expense extends BaseModel{

    private String expenseName;
    private long ExpenseCategoryId;

    public Expense(){
    }

    public Expense(int id, String expenseName, long expenseCategoryId) {
        super(id);
        this.expenseName = expenseName;
        ExpenseCategoryId = expenseCategoryId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public long getExpenseCategoryId() {
        return ExpenseCategoryId;
    }

    public void setExpenseCategoryId(long expenseCategoryId) {
        ExpenseCategoryId = expenseCategoryId;
    }
}
