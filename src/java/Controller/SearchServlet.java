
package Controller;

import Model.Book;
import Model.Category;
import Model.Search;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchServlet", urlPatterns = {"/search.do"})
public class SearchServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String key = null;
            if (request.getParameter("key") != null) {
                key = request.getParameter("key");
            }
            request.setAttribute("key", key);
            if (key.equals("")) {
                Category category = new Category("");
                List<Book> list = category.list(0, 20);
                int noOfRecords = category.getNoOfRecords();
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 20);
                request.setAttribute("noOfRecords", noOfRecords);
                request.setAttribute("noOfPages", noOfPages);
                request.setAttribute("currentPage", 1);
                request.setAttribute("listBook", list);
                
                RequestDispatcher view = request.getRequestDispatcher("products.jsp");
                view.forward(request, response);
            } else {
                Search result = new Search();
                List<Book> list = result.search(key);
                int noOfResult = result.getNoOfBook();
                String message = "Kết quả tìm kiếm cho: \'" + key + "\'";
                request.setAttribute("listBook", list);
                request.setAttribute("noOfResult", noOfResult);
                request.setAttribute("message", message);
                
                RequestDispatcher view = request.getRequestDispatcher("search.jsp");
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
