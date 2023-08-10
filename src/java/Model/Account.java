
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Account {
    private String accID;
    private String mail;
    private String name;
    private boolean gender; // Male is 0, Female is 1
    private String phone;
    private String password;

    public String getAccID() {
        return accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Account () {
        
    }
    
    public Account (String ID) {
        String query = "Select AccID, Mail, Name, Gender, Phone, Password from Account where AccID = \'" + ID + "\'";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                this.accID = ID;
                this.mail = rs.getString("Mail");
                this.name = rs.getNString("Name");
                this.gender = rs.getBoolean("Gender");
                this.phone = rs.getString("Phone");
                this.password = rs.getString("Password");
            }
            rs.close();
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public boolean checkExist(String mail) {
        String query = "Select AccID from Account where Mail = \'" + mail + "\'";
        boolean check = false;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
    
    public String update (String mail, String name, String phone) {
        String query = "Update Account set Name = N\'" + name + "\', Phone = \'" + 
                phone + "\' where Mail = \'" + mail + "\'";
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            if (rs.next()) {
                return mail;
            }
        } catch (SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
        return mail;
    }
}
