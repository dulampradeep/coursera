<%-- 
    Document   : insert
    Created on : May 13, 2019, 12:30:18 PM
    Author     : dprab
--%>

<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        try{
            String t1,t2,t3,s1,r1;
            t1=request.getParameter("t1");
            t2=request.getParameter("t2");
            t3=request.getParameter("t3");
            s1=request.getParameter("s1");
            r1=request.getParameter("r1");
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
        PreparedStatement ps=con.prepareStatement("insert into std values(?,?,?,?,?)");
        ps.setString(1,t1);
        ps.setString(2,t2);
        ps.setString(3,t3);
        ps.setString(4,s1);
        ps.setString(5,r1); 
      if(ps.executeUpdate()==1)
          out.println("updated");
        else
          out.println("not updated");
        
        
        con.close();
        
        }
        catch(Exception e){
              e.printStackTrace();
              out.println(e);
              }
        
        %>
    </body>
</html>
