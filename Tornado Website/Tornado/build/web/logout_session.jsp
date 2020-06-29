<%-- 
    Document   : logout_session
    Created on : Jan 25, 2020, 2:11:39 AM
    Author     : PRADEEP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
      <%
          session.invalidate(); %>
          <script>window.open("after_logout.jsp",'_self');
          window.open("after_logout.jsp",'frame4');</script>
     
    </body>
  
</html>
