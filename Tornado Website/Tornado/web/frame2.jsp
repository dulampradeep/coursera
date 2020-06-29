<%-- 
    Document   : frame2
    Created on : Jan 15, 2020, 8:24:32 AM
    Author     : PRADEEP
--%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="jquery.min.js"></script>
        <style>
            body{
                margin-top: 10px;
                background-color: white;
                border-radius:5px;
                margin:10px;
            }
             #period:hover{
                background-color:#D8D8D8;
                color:yellow;
            }
            #period{
                color:gray;
                font-size:15px;
                background-color:#F9EDEE;
                padding:3px;
               
                margin-top:20px;
                border:0;
                border-radius:10px;
                
                height:40px;
                width:99%;
                box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.2), 0 2px 10px 0 rgba(0, 0, 0, 0.19);
            }
            p{
                padding-left: 30px;
                padding-right: 120px;
                margin:10px;
                top:13px;
            }
            a{
                text-decoration:none;
                color:gray;
               
            }
            
            #head{
                font-size:15px;
                font-family:Helvetica,courier,arial;
                text-align: center;
                 color:green;
                 position:relative;
                 top:10px;
            }
            #head1{
                background-color: gray;
             
                height:40px;
                border-radius:7px;
            }
            th{
                background-color:#F9EDEE;
                width:130px;
                height:40px;
                text-align:center;
            }
            
            table{
                border-collapse: collapse;
                width:100%;
            }
            .sub{
                color:gray;
                background-color:#F9EDEE;
                height:38px;
                width:80px;
                border-radius:10%;
                padding:10px;
                position:relative;
                top:20px;
                left:78%;
                margin:0;
                border:0;
                box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.2), 0 4px 15px 0 rgba(0, 0, 0, 0.19);
            }
            .sub:hover{
                background-color:lightgrey;
            }
            *:focus {
               outline: none;
                }
            #search {
                position:relative;
                top:20%;
                left:72%;
                border:0;
                background-color:rgba(90,80,50,0.5);
                width:25%;
                padding-left:15px;
                height:33px;
                border-radius:5px;
                text-decoration: none;
                color:whitesmoke;
                display:inline;
                margin:10px;
            }
            #waytoframe3{
                display:inline;
                position:absolute;
                margin:10px;
                float:left;
                width:100px;
                font-size:17px;
                height:20px;
                background-color:rgba(90,80,50,0.5);
                color:gray;
                padding:8px;
                top:77px;
                left:1.5%;
                margin:5px;
                padding-left:20px;
                border-radius:5px;
            }
            #waytoframe3:hover{
                background-color:#D8D8D8;
                color:yellow;
            }
            #search1{
                position:absolute !important;
                top:12% !important;
                left:25% !important;
                width:330px;
                height:25px;
                padding-left:20px !important;
                padding-top:7px;
                text-align: center;
                font-weight: bold;
            }
        </style>
    </head>
    <body id="body"><%
   
                       String a=request.getParameter("id");
                       %>
                       <div id="head1"> <h3 id="head"><%=a%></h3></div><hr>
                       <a href="frame3.jsp?id2=<%=a%>" target="frame3"> <p id="waytoframe3">Students </p></a>
                       <p id="search1"/>time</p>
                       <input type="text" id="search" placeholder="Enter to search" autofocus />
             
                <table>
                    <tr>
                        <th>Regd No</th>
                        <th>Name</th>
                        <th> Attendence</th>
                        
                    </tr>
                </table>
                
                <form name="form" action="PostAttendence?id=<%=a%> " method="post">
                <div id="replace">  
 
                </div>
                     <input class ="sub" type="reset" value="clear" style="left:75%;"/>
                    <input class ="sub" type="submit" value="submit"/>
                
                </form>
        <script>
            
                $(document).ready(function (){
                 var xmlhttp = new XMLHttpRequest();
            
              xmlhttp.onreadystatechange = function() {
                    
                 if (this.readyState == 4 && this.status == 200) {
                  $("#replace").html(this.responseText);
                }  
                 
                };
                xmlhttp.open("GET", "Ajaxsearch1?id1="+$("#head").html()+"&id2= ",true);
                 xmlhttp.send();
                   
    });
            
            $("#search").keyup(function ajaxs(){
                var sql="select * from student inner join "+$("#head").html()+" on student.id="+$("#head").html()+".id where student.first_name like '"+$(this).val()+"%'";
               
              
                 var xmlhttp = new XMLHttpRequest();
            
              xmlhttp.onreadystatechange = function() {
                    
                 if (this.readyState == 4 && this.status == 200) {
                  senttttt(this);
                }  
                 
                };
                xmlhttp.open("GET", "Ajaxsearch1?id1="+$("#head").html()+"&id2="+$(this).val(),true);
                 xmlhttp.send();
                   
            });
            function senttttt(k){
                $("#replace").html(k.responseText);
            }
            setInterval(function(){
                $("#search1").html("Time : "+new Date().toLocaleTimeString()+"  Date : "+new Date().toLocaleDateString());
            },10);
            function find(x){
                switch(x){
                   case 7:return "Sunday";break; 
                   case 1:return "Monday";break; 
                   case 2:return "Tuesday";break; 
                   case 3:return "Wednesday";break; 
                   case 4:return "Thursday";break;
                   case 5:return "Friday";break; 
                   case 6:return "Saturday";break; 
                   
                }
            }
        </script>
    </body>
</html>
