package com.example.budget.controllers;

import com.example.budget.dao.DaoTransaction;
import com.example.budget.model.Transaction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoTransaction transactionDao = DaoTransaction.getInstance();
    private static final Logger LOGGER = Logger.getLogger(TransactionController.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertTransaction(req, resp);
                    break;
                case "/delete":
                    deleteTransaction(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateStuff(req, resp);
                    break;
                default:
                    listTransactions(req, resp);
                    break;
            }
        }
        catch (SQLException e) {
            // For simplicity just log the Exceptions
            LOGGER.log(Level.SEVERE, "SQL Error", e);
        }

    }

    private void updateStuff(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        int cost = Integer.parseInt(req.getParameter("cost"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        int expenseId = Integer.parseInt(req.getParameter("expenseId"));
        int expenseCatId = Integer.parseInt(req.getParameter("expenseCategoryId"));
        String comment = req.getParameter("comment");

        Transaction transaction = new Transaction(id, cost, date, expenseId, expenseCatId, comment);
        transactionDao.update(transaction);
        resp.sendRedirect("transactions");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{
        String id = req.getParameter("id");
        Optional<Transaction> existingTransaction = transactionDao.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/TransactionForm.jsp");
        existingTransaction.ifPresent(t -> req.setAttribute("transaction", t));
        dispatcher.forward(req, resp);
    }

    private void deleteTransaction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("id"));

        Transaction t = new Transaction(id);
        transactionDao.delete(t);
        resp.sendRedirect("transactions");
    }

    private void insertTransaction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(req.getParameter("id"));
        int cost = Integer.parseInt(req.getParameter("cost"));
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        int expenseId = Integer.parseInt(req.getParameter("expenseId"));
        int expenseCatId = Integer.parseInt(req.getParameter("expenseCategoryId"));
        String comment = req.getParameter("comment");

        Transaction transaction = new Transaction(id, cost, date, expenseId, expenseCatId, comment);
        transactionDao.save(transaction);
        resp.sendRedirect("transactions");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{
        RequestDispatcher disp = req.getRequestDispatcher("jsp/TransactionForm.jsp");
        disp.forward(req, resp);
    }

    private void listTransactions(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException{
        RequestDispatcher disp = req.getRequestDispatcher("jsp/transactionsList");

        List<Transaction> transactions = transactionDao.findAll();
        req.setAttribute("listTransactions", transactions);

        disp.forward(req, resp);
    }


}
