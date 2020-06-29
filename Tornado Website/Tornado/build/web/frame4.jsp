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
            #login{
               
                padding:13px;
               width:50px;
                background-color:rgba(255,255,255,0.1);
                position:absolute;
                top:10px;
                left:90%;
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
            #nav a{
                float:left;
                padding:13px;
                padding-right:50px;
                background-color:rgba(255,255,255,0.1);
                position:relative;
                top:3px;
                left:12%;
                text-decoration:none;
                color:white;
                border-radius: 3%;
            }
             #nav a:nth-child(1) {
                padding-left: 30px !important;
            }
            #nav a:nth-child(4) {
                padding-right: 30px !important;
            }
            #nav>a:hover{
                background-color:rgba(0,0,0,0.1);
            }
            #login:hover{
                background-color:rgba(0,0,0,0.1);
            }
        </style>
    </head>
    <body>
        <img id="logo" src="stuff/logo2.png" alt="logo" />
        <p id="logoname">Tornado</p>
        <div id="menu">
            <nav id="nav">
                <a href="Notice_Board.jsp" target="frame2">Home</a> 
                <a href="home.jsp" target="frame2">CSS</a> 
                <a href="register1.jsp" target="frame2">JavaScript</a> 
                <a href="/jquery/"  target="frame2">jQuery</a>
            </nav>
            
        </div>
      
        <a  href="logout_session.jsp" id="login" >Logout</a>
         
    </body>
    <script>
        
    </script>
</html>
