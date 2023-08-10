
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Register {
    private Account acc;
    
    public Register() {
        
    }
    
    public Register(String name, String mail, String phone, String password) {
        String query = "Select top 1 AccID from Account order by AccID desc";
        String last;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/BookStore");
            Connection conn = ds.getConnection();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(query);
            String accID = null;
            if (rs.next()) {
                last = rs.getString(1);
                int c, m = 0;
                int k = last.length();
                for (int i=0; i<k; i++) {
                    c = last.charAt(i) - '0';
                    m = m*10 + c;
                }
                int n = m + 1;
                accID = "" + n;
            }
            rs.close();
            if (phone.equals("")) 
                query = "Insert into Account (AccID, Mail, Name, Password)" +
                    " values (\'" + accID + "\', \'" + mail + "\', N\'" + name +
                    "\', \'" + password + "\')";
            else query = "Insert into Account (AccID, Mail, Name, Phone, Password)" +
                    " values (\'" + accID + "\', \'" + mail + "\', N\'" + name +
                    "\', \'" + phone + "\', \'" + password + "\')";
            rs = state.executeQuery(query);
            if (rs.next()) {
                this.acc = new Account();
                acc.setAccID(accID);
                acc.setMail(mail);
                acc.setName(name);
                acc.setPhone(phone);
                acc.setPassword(password);
            }
        } catch(SQLException | NamingException e) {
            System.out.println(e.getMessage());
        }
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }
    
}
