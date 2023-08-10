
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

public class Category {
    private String cate;
    private int noOfRecords;
    
    public Category () {
        
    }
    
    public Category (String cate) {
        this.cate = cate;
        this.noOfRecords = 0;
    }
    
    public String getCate () {
        return this.cate;
    }
    
    public void setCate (String cate) {
        this.cate = cate;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public void setNoOfRecords(int noOfRecords) {
        this.noOfRecords = noOfRecords;
    }
    
    
    public List<Book> list(int offset, int noOfRecords) {
        int start = offset + 1;
        int end = offset + noOfRecords;
        String cateQuery = null;
        if (cate == null || cate.equals("")) {
            cateQuery = "";
        } else {
            cateQuery = " where Category = N\'" + cate + "\'";
        }
        String query = "Select * from (Select (row_number() over (order by BookID)) as Row, " + 
                "BookID, BName, Author, Price, Category from Book" + 
                cateQuery + ") as Tab " + 
                "where Row between " + start + " and " + end;
        ArrayList<Book> list = new ArrayList<>();
        Book book = null;
        try {
            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                book = new Book();
                book.setBookID(rs.getString("BookID"));
                book.setbName(rs.getNString("BName"));
                book.setAuthor(rs.getNString("Author"));
                book.setPrice(rs.getInt("Price"));
                list.add(book);
            }
            rs.close();
            query = "Select count(*) from Book" + cateQuery;
            rs = state.executeQuery(query);
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
