package com.example.budget.dao;

import com.example.budget.model.DataSourceFactory;
import com.example.budget.model.Transaction;
import com.example.budget.model.TransactionDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class DaoTransaction implements TransactionDao {

    private DaoTransaction() {
    }

    private static class SingletonHelper {
        private static final DaoTransaction INSTANCE = new DaoTransaction();
    }

    public static DaoTransaction getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public Optional<Transaction> find(String id) throws SQLException {
        String sql = "SELECT transaction_id,cost,date,expense_id,exp_category_id,comment " +
                "FROM transactions WHERE transaction_id = ?";
        int transactionId = 0;
        int cost = 0;
        LocalDate date = LocalDate.now();
        int expenseId = 0;
        int expenseCategoryId = 0;
        String comment = "";
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            transactionId = resultSet.getInt("transaction_id");
            cost = resultSet.getInt("cost");
            date = (resultSet.getDate("date")).toLocalDate();
            expenseId = resultSet.getInt("expense_id");
            expenseCategoryId = resultSet.getInt("exp_category_id");
            comment = resultSet.getString("comment");
        }

        return Optional.of(new Transaction(
                transactionId, cost, date, expenseId, expenseCategoryId, comment));
    }

    @Override
    public List<Transaction> findAll() throws SQLException {

        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT transaction_id,cost,date,expense_id,exp_category_id,comment " +
                "FROM transactions";
        Connection conn = DataSourceFactory.getConnection();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            int transactionId = resultSet.getInt("transaction_id");
            int cost = resultSet.getInt("cost");
            LocalDate date = (resultSet.getDate("date")).toLocalDate();
            int expenseId = resultSet.getInt("expense_id");
            int expenseCategoryId = resultSet.getInt("exp_category_id");
            String comment = resultSet.getString("comment");

            Transaction transaction = new Transaction(
                    transactionId, cost, date, expenseId, expenseCategoryId, comment);
            transactions.add(transaction);
        }

        return transactions;
    }

    @Override
    public boolean save(Transaction tr) throws SQLException {
        String sql = "INSERT into transaction (cost, date, expense_id, " +
                "exp_category_id, comment) VALUES (?, ?, ?, ?, ?)";
        boolean rowInserted = false;
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, tr.getCost());
        statement.setDate(2, java.sql.Date.valueOf(tr.getTransactionDate()));
        statement.setLong(3, tr.getExpenseId());
        statement.setLong(4, tr.getExpenseCategoryId());
        statement.setString(5,tr.getComment());

        rowInserted = statement.executeUpdate() > 0;
        return rowInserted;
    }

    @Override
    public boolean update(Transaction tr) throws SQLException {
        String sql = "UPDATE transactions SET cost = ?, date = ?, expense_id = ?," +
                "exp_category_id = ?, comment = ? WHERE transaction_id = ?";
        boolean rowUpdated = false;
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, tr.getCost());
        statement.setDate(2, java.sql.Date.valueOf(tr.getTransactionDate()));
        statement.setLong(3,tr.getExpenseId());
        statement.setLong(4, tr.getExpenseCategoryId());
        statement.setString(5, tr.getComment());
        statement.setLong(6,tr.getId());

        rowUpdated = statement.executeUpdate() > 0;
        return rowUpdated;
    }

    @Override
    public boolean delete(Transaction tr) throws SQLException {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?";
        boolean rowDeleted = false;
        Connection conn = DataSourceFactory.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setLong(1, tr.getId());

        rowDeleted = statement.executeUpdate() > 0;
        return rowDeleted;
    }
}
