<%-- 
    Document   : frame4
    Created on : Jan 15, 2020, 8:27:34 AM
    Author     : PRADEEP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            *:focus {
               outline: none;
                }
            body{
               background-color:#59C4A9;
            }
            #logo{
                width:45px;
                height:45px;
                border-radius: 30%;
                position:absolute;
                top:15%;
                left:1%;
            }
            .login{
               
                padding:12px;
               width:50px;
                background-color:rgba(255,255,255,0.1);
                position:relative;
                top:20px;
                left:87%;
                margin:5px;
                text-align:center;
                color:white;
                 text-decoration:none;
                  border-radius: 5%;
            }
            #logoname{
                 position:absolute;
                 top:7%;
                 left:5%;
                 font-family:sans-serif;
                 font-weight:bold;
                 font-style:oblique;
            }
            #nav{
                padding-left:25px;
            }
           
            
            .login:hover{
                background-color:rgba(0,0,0,0.1);
            }
        </style>
    </head>
    <body>
        <img id="logo" src="stuff/logo2.png" alt="logo" />
        <p id="logoname">Tornado</p>
        <div id="menu">
      
            
        </div>
         <a  href="register1.jsp" class="login" target="frame2">Register</a>
        <a  href="login.jsp" class="login" target="frame2">Login</a>
         
    </body>
  
</html>
