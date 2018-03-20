/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack1;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author colchhina
 */
public class checklogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checklogin</title>");            
            out.println("</head>");
            out.println("<body>");
            try {
                String name = request.getParameter("username");
                boolean flag = false;
                String colorname="white";
                Cookie cookies[] = request.getCookies();
                if(cookies!=null)
                {
                    for(Cookie c : cookies)
                    {
                        if(c.getName().equals(name))
                        {
                            colorname = c.getValue();
                            flag = true;
                        }
                    }
                }
                if(flag==false)
                {
                    Cookie cook = new Cookie(name, "white");
                    cook.setMaxAge(30);
                    response.addCookie(cook);
                    out.println("Welcome New User");
                }
                else
                {
                    out.println("Welcome Existing User");
                }
                out.println("<body bgcolor="+colorname+">");
                out.println("<form action=setcolor>");
                out.println("Enter color name : <input type=text name=cname>");
                out.println("<input type=hidden name=uname value="+name+">");
                out.println("<input type=submit value=setcolor>");
                out.println("</form>");
                out.println("</body>");
                out.println("</html>");
            } catch (Exception e) {
                out.println(e.toString());
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
