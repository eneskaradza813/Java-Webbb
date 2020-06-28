/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author mesa
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public enum Gender
    {
        male,female
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        String first_name = "";
        String last_name = "";
        Gender gender = null;
        LocalDate date_of_birth = null;
        String plan = "";
        boolean agree = false;
        
        if (ServletFileUpload.isMultipartContent(request)) {

                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List items = upload.parseRequest(request);
                Iterator itr = items.iterator();

                while (itr.hasNext()) {

                    FileItem item = (FileItem) itr.next();

                    if (!item.isFormField()) {

                        if(item.getFieldName().equals("first_name")){
                            first_name = item.getString();
                        }
                        else if(item.getFieldName().equals("last_name")){
                            last_name = item.getString();
                        }
                        else if(item.getFieldName().equals("gender")){
                            gender = Gender.valueOf(item.getString()); 
                        }
                        else if(item.getFieldName().equals("date")){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            date_of_birth = LocalDate.parse(item.getString(), formatter);
                        }
                        else if(item.getFieldName().equals("plan")){
                            plan = item.getString();
                        }
                        else if(item.getFieldName().equals("agree")){
                            if(item.getString().equals("0")){
                                agree = false;
                            }
                            else{
                                agree = true;
                            }
                        }

                    }
                    else{
                        item.write(new File(item.getName()));
                    }
                }
            }
        
            try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>You entered: </h1>");

            out.println("<p> Your first name: " + first_name + "</p>");
            out.println("<p> Your last name: " + last_name + "</p>");
            out.println("<p> Your gender: " + gender.toString() + "</p>");
            out.println("<p> Your plan: " + plan + "</p>");
            out.println("<p> Your date: " + date_of_birth.toString() + "</p>");
            if(agree){
                out.println("<p> You agreed.</p>");
            } else{
                out.println("<p> You have not agreed.</p>");
            }
            out.println("</body>");
            out.println("</html>");
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
