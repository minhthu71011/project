
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Login {
    private String id;
    private String name;
    
    public Login () {
        
    } 
    
    public boolean login (String mail, String password) {
        String query = "Select AccID, Name from Account where Mail = \'" + mail + "\' and Password = \'" + password + "\'";
        boolean check = false;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                this.id = rs.getString("AccID");
                this.name = rs.getString("Name");
                check = true;
            }
            else this.id = null;
            
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
