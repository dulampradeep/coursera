/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tornado;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PRADEEP
 */public class PostAttendence extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req
     * @param res
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException  {
    
        try (PrintWriter out = res.getWriter()) {
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
             String c[]=req.getParameterValues("check1");
           Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","tornado","tornado");
            String a=req.getParameter("id");     
            st=con.createStatement();
           
            if(c != null && c.length > 0){
                String sql="update "+a+" set status='YES' where id='"+c[0]+"'";
                
                for(int i=1;i<c.length;i++){
                    sql+=" or id='"+c[i]+"'";
                    
                }
                
                int k= st.executeUpdate(sql);
                if(k>=1){
                    
                    res.sendRedirect("frame2.jsp?id="+a);
                }
            }
            else{
                    String link="frame2.jsp?id="+a;
                out.println("<p style='color:red;text-align:center;'>Opps! You didn't give attendence to any one</p>");
                RequestDispatcher rd=req.getRequestDispatcher(link);
                rd.include(req, res);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(PostAttendence.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
