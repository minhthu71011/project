
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

public class MoreBook {
    private String category;
    
    public MoreBook(){}
    
    public List<Book> listBook (String bookID, int off) {
        String query = "Select top " + off + " * from Book where Category = " +
                "(Select Category from Book where BookID = \'" + bookID + "\')" +
                "and BookID <> \'" + bookID + "\'";
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
                book.setContent(rs.getNString("Content"));
                book.setPrice(rs.getInt("Price"));
                list.add(book);
            } 
            rs.close();
            
            rs = state.executeQuery("Select Category from Book where BookID = \'bookID\'");
            if(rs.next())
                this.category = rs.getNString(1);
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    public String getCategory() {
        return category;
    }
}
