
package Controller;

import Model.Book;
import Model.Cart;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CartServlet", urlPatterns = {"/cart.do"})
public class CartServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        Cart cart = new Cart(accID);
        if (request.getQueryString() != null && !request.getQueryString().equals("")) {
            String bookID = request.getParameter("bookID");
            int amount = 1;
            if (request.getParameter("amount") != null) {
                amount = Integer.parseInt(request.getParameter("amount"));
                cart.addToCart(bookID, amount);
            }   
            else {
                cart.removeFromCart(bookID);
            }   
        }
        List<Book> list = cart.bookCart();
        int total = cart.getTotal();
        int noOfRecords = cart.getAmount();
        request.setAttribute("bookCart", list);
        request.setAttribute("total", total);
        request.setAttribute("amount", noOfRecords);
        
        RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
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
