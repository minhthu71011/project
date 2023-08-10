
package Controller;

import Model.Book;
import Model.Category;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CateServlet", urlPatterns = {"/products.do"})
public class CateServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int page = 1;
        int recordPerPage = 20;
        String cateName = null;
        String cate = null;
            cate = request.getParameter("cate");
            if (request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));
            if (cate == null)
                cate = "";
            else if (cate.equals("sgk")) cateName = "Sách giáo khoa";
            else if (cate.equals("ttr")) cateName = "Truyện tranh";
            else if (cate.equals("tth")) cateName = "Tiểu thuyết";
            else if (cate.equals("tvn")) cateName = "Thơ ca Việt Nam";
            else if (cate.equals("vvn")) cateName = "Văn học Việt Nam";
            else if (cate.equals("vnn")) cateName = "Văn học nước ngoài";
            else cateName = "";
        //request.setAttribute("userpath", userPath);
        Category category = new Category (cateName);
        List<Book> list = category.list((page-1)*recordPerPage, recordPerPage);
        int noOfRecords = category.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordPerPage);
        request.setAttribute("noOfRecords", noOfRecords);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("listBook", list);
        if (cateName == null || cateName.equals("")) {
            RequestDispatcher view = request.getRequestDispatcher("products.jsp");
            view.forward(request, response);
        } else {
            request.setAttribute("category", cateName);
            request.setAttribute("cate", cate);
            RequestDispatcher view = request.getRequestDispatcher("cate.jsp");
            view.forward(request, response);
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
