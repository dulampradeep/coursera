<%-- 
    Document   : start
    Created on : Jan 23, 2020, 2:04:26 AM
    Author     : PRADEEP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="w3.js" type="text/javascript" ></script>
        <style>
            *{
             padding:10px;
             margin:0;
             border:0;
            }  
            img{
                width:600px;
                height:500px;
                align:center;
                transition: 5s;
            }
        </style>
    </head>
    <body>
       
    <center>
        <img class="nature" src="stuff/img1.jpg" >
        <img class="nature" src="stuff/img2.jpg" >
        <img class="nature" src="stuff/img3.jpg" >
        <button onclick="myShow.previous()">Previous</button>
        <button onclick="myShow.next()">Next</button>
    </center>
        <script>
        myShow=w3.slideshow(".nature",5000);
        
            
        </script>
    </body>
</html>
