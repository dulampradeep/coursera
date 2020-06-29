package tornado;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login_forward extends HttpServlet{

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    
    
    
    
        response.setContentType("text/html");
         String a=request.getParameter("username");
           String b=request.getParameter("password");
           HttpSession ses=request.getSession();
           ses.setAttribute("uname",a);
           PrintWriter out = response.getWriter();
           out.println("<script>window.open('frame4.jsp','frame1');</script>");
          
            request.getRequestDispatcher("Notice_Board.jsp").include(request, response);
        
    }

}
