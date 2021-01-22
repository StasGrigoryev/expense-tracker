package com.example.budget.model;


public class ExpenseCategory extends BaseModel{

    private String categoryName;

    public ExpenseCategory(){
    }

    public ExpenseCategory(int id, String categoryName) {
        super(id);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
