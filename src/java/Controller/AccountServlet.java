
package Controller;

import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name="AccountServlet", urlPatterns={"/account.do"})
public class AccountServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String accID = "102001";
        HttpSession session = request.getSession();
        if (session.getAttribute("accID") != null) {
            accID = (String) session.getAttribute("accID");
        } else {
            String urlPattern = request.getServletPath();
            request.setAttribute("currentUrl", urlPattern);
            RequestDispatcher view = request.getRequestDispatcher("login.jsp");
            view.forward(request, response);
        }
        Account acc = new Account();
        
        if (request.getParameter("mail") != null) {
            String mail = request.getParameter("mail");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            acc.update(mail, name, phone);
            
            session.setAttribute("name", name);
            
            String message = "Thay đổi thông tin tài khoản thành công!";
            request.setAttribute("message", message);
        }
        acc = new Account(accID);
        request.setAttribute("acc", acc);
        RequestDispatcher view = request.getRequestDispatcher("account.jsp");
        view.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
