<%-- 
    Document   : Notice Board
    Created on : Jan 31, 2020, 9:30:40 PM
    Author     : PRADEEP
--%>

<%@page import="jsoup.NoticeBoard"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notice Board</title>
        <style>
          th{
                background-color:#F9EDEE;
                width:130px;
                height:40px;
                text-align:left;
                font-size: 20px;
            }
            
            table{
                border-collapse: collapse;
                width:100%;
            }
            tr{
                color:gray;
                font-size:15px;
                background-color:#F9EDEE;
                padding:5px;
               
                margin-top:50px;
                border:0;
                border-radius:10px;
                border-bottom:5px solid rgbargba(0, 0, 0, 0.2);
                height:70px;
                width:99%;
                box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.2), 0 2px 10px 0 rgba(0, 0, 0, 0.19);
            }
        </style>
    </head>
    <body>
        <table cellpadding='10' cellspacing="5">
           <% 
               String st=NoticeBoard.notice();
               PrintWriter pw=response.getWriter();
               out.println(st);
               
           %>
        </table>
    </body>
</html>
