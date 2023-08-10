
package Controller;

import Model.Account;
import Model.Register;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register.do"})
public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = null;
            String mail = null;
            String phone = null;
            String password = null;
            String confirm = null;
            Account acc = new Account();
            if (request.getParameter("mail") != null) {
                name = request.getParameter("name");
                mail = request.getParameter("mail");
                phone = request.getParameter("phone");
                password = request.getParameter("password");
                confirm = request.getParameter("confirm");
                
                if ((name == null) || (name.equals(""))) {
                    String message = "Vui lòng điền đầy đủ thông tin!";
                    request.setAttribute("inforMessage", message);
                    request.setAttribute("name", "");
                    request.setAttribute("mail", mail);
                    request.setAttribute("phone", phone);
                    RequestDispatcher view = request.getRequestDispatcher("register.jsp");
                    view.forward(request, response);
                } else if ((mail == null) || (mail.equals(""))) {
                    String message = "Vui lòng điền đầy đủ thông tin!";
                    request.setAttribute("inforMessage", message);
                    request.setAttribute("name", name);
                    request.setAttribute("mail", mail);
                    request.setAttribute("phone", phone);
                    RequestDispatcher view = request.getRequestDispatcher("register.jsp");
                    view.forward(request, response);
                } else if (acc.checkExist(mail)) {
                    String message = "Email đã được sử dụng. Vui lòng nhập một email khác!";
                    request.setAttribute("inforMessage", message);
                    request.setAttribute("name", name);
                    request.setAttribute("mail", "");
                    request.setAttribute("phone", phone);
                    RequestDispatcher view = request.getRequestDispatcher("register.jsp");
                    view.forward(request, response);
                } else if (password.equals("")) {
                    String message = "Vui lòng nhập mật khẩu và xác nhận!";
                    request.setAttribute("passMessage", message);
                    request.setAttribute("name", name);
                    request.setAttribute("mail", mail);
                    request.setAttribute("phone", phone);
                    RequestDispatcher view = request.getRequestDispatcher("register.jsp");
                    view.forward(request, response);
                } else if (!password.equals(confirm)) {
                    String message = "Mật khẩu xác nhận không chính xác. Vui lòng nhập lại!";
                    request.setAttribute("passMessage", message);
                    request.setAttribute("name", name);
                    request.setAttribute("mail", mail);
                    request.setAttribute("phone", phone);
                    RequestDispatcher view = request.getRequestDispatcher("register.jsp");
                    view.forward(request, response);
                } else {
                    String message = "Đăng ký tài khoản thành công!";
                    Register register = new Register(name, mail, phone, password);
                    Account account = register.getAcc();
                    String accID = account.getAccID();
                    request.setAttribute("message", message);
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("accID", accID);
                    
                    RequestDispatcher view = request.getRequestDispatcher("account.jsp");
                    view.forward(request, response);
                }
            } else {
                String message = "Vui lòng điền đầy đủ thông tin!";
                request.setAttribute("inforMessage", message);
                request.setAttribute("name", name);
                request.setAttribute("mail", null);
                request.setAttribute("phone", phone);
                RequestDispatcher view = request.getRequestDispatcher("register.jsp");
                view.forward(request, response);
            }
        }
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
