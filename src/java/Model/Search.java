
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

public class Search {
    private int noOfBook;
    
    public Search() {
        this.noOfBook = 0;
    }
    
    public List<Book> search (String key) {
        List<Book> list = new ArrayList<Book>();
        Book book = null;
        //Tim chinh xac theo ten, tac gia, the loai
        String query = "Select * from Book where BName like N\'" + key + 
                "\' or Author like N\'" + key + "\' or Category like N\'" + key + "\'";
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
                this.noOfBook++;
            }
            rs.close();
            
            //Tim gan dung theo ten sach
            query = "Declare @query Nvarchar(max)\n" + 
                "Select @query = dbo.non_unicode_convert(N\'" + key + "\')\n" + 
                "Select * from Book where dbo.non_unicode_convert(BName) like \'%\' + @query + \'%\'";
            rs = state.executeQuery(query);
            while (rs.next()) {
                book = new Book();
                book.setBookID(rs.getString("BookID"));
                book.setbName(rs.getNString("BName"));
                book.setAuthor(rs.getNString("Author"));
                book.setContent(rs.getNString("Content"));
                book.setPrice(rs.getInt("Price"));
                list.add(book);
                this.noOfBook++;
            }
            rs.close();
            
            //Tim gan dung theo tac gia
            query = "Declare @query Nvarchar(max)\n" + 
                "Select @query = dbo.non_unicode_convert(N\'" + key + "\')\n" + 
                "Select * from Book where dbo.non_unicode_convert(Author) like \'%\' + @query + \'%\'";
            rs = state.executeQuery(query);
            while (rs.next()) {
                book = new Book();
                book.setBookID(rs.getString("BookID"));
                book.setbName(rs.getNString("BName"));
                book.setAuthor(rs.getNString("Author"));
                book.setContent(rs.getNString("Content"));
                book.setPrice(rs.getInt("Price"));
                list.add(book);
                this.noOfBook++;
            }
            rs.close();
            
            //Tim gan dung theo the loai
            query = "Declare @query Nvarchar(max)\n" + 
                "Select @query = dbo.non_unicode_convert(N\'" + key + "\')\n" + 
                "Select * from Book where dbo.non_unicode_convert(Category) like \'%\' + @query + \'%\'";
            rs = state.executeQuery(query);
            while (rs.next()) {
                book = new Book();
                book.setBookID(rs.getString("BookID"));
                book.setbName(rs.getNString("BName"));
                book.setAuthor(rs.getNString("Author"));
                book.setContent(rs.getNString("Content"));
                book.setPrice(rs.getInt("Price"));
                list.add(book);
                this.noOfBook++;
            }
            rs.close();
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int getNoOfBook() {
        return noOfBook;
    }

    public void setNoOfBook(int noOfBook) {
        this.noOfBook = noOfBook;
    }
    
    
}
