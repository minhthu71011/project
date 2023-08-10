
package Controller;

import Model.Book;
import Model.MoreBook;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "BookServlet", urlPatterns = {"/book.do"})
public class BookServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String bookID = "VN0001";
        if (request.getQueryString() != null) {
            bookID = request.getQueryString();
        }
        
        Book book = new Book(bookID);
        
        request.setAttribute("bookID", bookID);
        request.setAttribute("bName", book.getbName());
        request.setAttribute("author", book.getAuthor());
        request.setAttribute("price", book.getPrice());
        //request.setAttribute("amount", book.getAmount());
        request.setAttribute("content", book.getContent());
        
        int off = 6;
        MoreBook more = new MoreBook();
        List<Book> list = more.listBook(bookID, off);
        request.setAttribute("bookList", list);
        request.setAttribute("off", off);
        
        RequestDispatcher view = request.getRequestDispatcher("viewProducts.jsp");
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
