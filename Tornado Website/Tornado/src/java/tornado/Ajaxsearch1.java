/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tornado;

import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author PRADEEP
 */
public class Ajaxsearch1 extends HttpServlet{

    /**
     *
     * @param req
     * @param res
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @Override
      protected void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
            try{
              Connection con=null;
              Statement st=null;
              ResultSet rs=null;
              res.setContentType("text/html");
              PrintWriter out=res.getWriter();
              Class.forName("oracle.jdbc.driver.OracleDriver");
              con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","tornado","tornado");
              st=con.createStatement();
              String a=req.getParameter("id1");
              String b=req.getParameter("id2");
              String c=b.toLowerCase();
              String sql="select * from student inner join "+a+" on student.id="+a+".id where student.first_name like '"+c+"%' order by student.id";
            
              rs=st.executeQuery(sql);
              String x="";
              if (!rs.next()) {
                   x+="<p style='color:red; text-align:center;font-size:25px;margin-top:200px;margin-left:100px;'>Opps ! No data found</p>";
                     out.write(x);
                     return;
              }
             do{
                  x+="<a href='frame3.jsp# " + rs.getString(1) + " ' target='frame3'> <div id='period'>" +

"                       <table >" +
"                           <tr>" +

"                               <td> <p style=' position:relative;display:inline; left:55px;'> "+rs.getString(1)+"</p></td>" +
"                               <td> <p style=' position:relative;display:inline; left:10px;'>"+rs.getString(3)+rs.getString(2)+"</p></td>" +
"                               <td><p style=' position:relative;display:inline;padding-left:0; left:25%;'><input type='checkbox' name='check1' value='"+rs.getString(1)+"'/></p><br></td>" +

"                        </tr>" +
"                       </table>" +

"                          </div></a>";
              } while(rs.next());
 
              con.close();
              
            
            out.write(x);
            }
            catch(IOException | ClassNotFoundException | SQLException e){
                System.out.println(e);
            }
      }
}
