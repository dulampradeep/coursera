package tornado;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;

public class login_validate implements Filter{
    @Override
    public void init(FilterConfig arg0) throws ServletException {} 

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     * @throws java.lang.IllegalAccessException
     */
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException   {
           
        try {
            response.setContentType("text/html");
            Connection con=null;
            Statement st=null;
            ResultSet rs=null;
           
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","tornado","tornado");
            
            st=con.createStatement();
            rs=st.executeQuery("select name,password from teacher");
            String a=request.getParameter("username");
            String b=request.getParameter("password");
            while(rs.next()){
                
                if((rs.getString(1).equals(a)) && (rs.getString(2).equals(b)))
                    chain.doFilter(request, response);
            }
          
        } catch (Exception e) {
            System.out.println(e);
        }
    
    
    }

    @Override
    public void destroy() {
        //To change body of generated methods, choose Tools | Templates.
    }
    
}