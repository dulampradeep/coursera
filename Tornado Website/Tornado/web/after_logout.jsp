<%-- 
    Document   : after logout
    Created on : Jan 25, 2020, 2:34:21 AM
    Author     : PRADEEP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <script type="text/javascript">
        function noBack()
         {
             window.history.forward()
         }
        noBack();
        window.onload = noBack;
        window.onpageshow = function(evt) { if (evt.persisted) noBack() }
        window.onunload = function() { void (0) }
    </script>
    </head>
    <body>
    <center><h3 style="position:relative;top:100px;">Opps! your are logged out please sign in again</h3>
        <br>
        <a  href="home_main.jsp" class="login" >Login</a>
    
    </center>
    </body>
</html>
