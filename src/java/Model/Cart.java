
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Cart {
    private int total;
    private int amount;
    private String accID;
    
    public Cart (String accID) {
        this.total = 0;
        this.amount = 0;
        this.accID = accID;
    }
    
    public List<Book> bookCart () {
        String query = "Select Book.BookID, BName, Author, Price, Purchase.Amount " 
                + "from Purchase, Book where Book.BookID = Purchase.BookID and AccID = \'" 
                + this.accID + "\' and Bought = \'false\' order by Datetime";
        List<Book> list = new ArrayList<Book>();
        Book book = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            while(rs.next()) {
                book = new Book();
                book.setBookID(rs.getString("BookID"));
                book.setbName(rs.getNString("BName"));
                book.setAuthor(rs.getNString("Author"));
                book.setPrice(rs.getInt("Price"));
                book.setAmount(rs.getInt("Amount"));
                list.add(book);
                this.amount += book.getAmount();
                this.total += book.getPrice()*book.getAmount();
            }
            
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public String addToCart (String bookID, int amount) {
        String query = "Insert into Purchase values (\'" + accID + "\', \'" + 
                bookID + "\', getDate(), " + amount + ", 0)";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) 
                return this.accID;
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return this.accID;
    }
    
    public String removeFromCart (String bookID) {
        String query = "Delete from Purchase where AccID = \'" + accID + "\' and BookID = \'" + bookID + "\'";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) 
                return this.accID;
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return this.accID;
    }
    
    public String toPay () {
        String query = "Update Purchase set Bought = 1 where AccID = \'" + 
                accID + "\' and Bought = 0";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) 
                return this.accID;
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return this.accID;
    }
    
    public int getTotal() {
        return this.total;
    }
    
    public String getAccID() {
        return this.accID;
    }
    
    public int getAmount() {
        return this.amount;
    }
}
