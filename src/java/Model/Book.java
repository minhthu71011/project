
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Book {

    private String bookID;
    private String bName;
    private String author;
    private int price;
    private int amount;
    private String content;

    public Book() {

    }

    public Book(String id) {
        String query = "Select * from Book where BookID = \'" + id + "\'";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                this.bookID = id;
                this.bName = rs.getNString("BName");
                this.author = rs.getNString("Author");
                this.price = rs.getInt("Price");
                this.amount = rs.getInt("Amount");
                this.content = rs.getNString("Content");
            }
            rs.close();
        } catch (SQLException | NamingException ex) {
            System.err.println(ex);
        }
    }
    
    public String addBook (String bName, String author, String cate, int amount, int price, String content) {
        String category = null;
        String bookID = null;
        if (cate.equals("sgk")) category = "Sách giáo khoa";
        else if (cate.equals("ttr")) category = "Truyện tranh";
        else if (cate.equals("tvn")) category = "Thơ ca Việt Nam";
        else if (cate.equals("vvn")) category = "Văn học Việt Nam";
        else if (cate.equals("tth")) category = "Tiểu thuyết";
        else if (cate.equals("vnn")) category = "Văn học nước ngoài";
        else category = "";
        String query = "Select top 1 BookID from Book where Category = N\'" + 
                category + "\' order by BookID desc";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            
            if (rs.next()) {
                String last = rs.getString(1);
                int c, m = 0;
                int k = last.length();
                for (int i=2; i<k; i++) {
                    c = last.charAt(i) - '0';
                    m = m*10 + c;
                }
                int n = m + 1;
                bookID = last.substring(0, 2) + n;
            }
            rs.close();
            
            query = "Insert into Book values (\'" + bookID + "\', N\'" + 
                    bName + "\', N\'" + author + "\', N\'" + category + "\', N\'" + 
                    content + "\', " + price + ", " + amount + ")";
            rs = state.executeQuery(query);
            
            if (rs.next()) 
                return bookID;
            } catch(SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return bookID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
