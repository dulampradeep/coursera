<%-- 
    Document   : frame1
    Created on : Jan 15, 2020, 8:23:56 AM
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
            }
            .period:hover{
                background-color:#D8D8D8;
                color:red;
            }
            .period{
                color:gray;
                font-size:15px;
                background-color:#F9EDEE;
                padding:4px;
                margin:5px;
                margin-top:14px;
                border:0;
                border-radius:10px;
                text-align:center;
                height:40px;
                width:90%;
                box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.2), 0 4px 14px 0 rgba(0, 0, 0, 0.19);
            }
            h4{
                padding:0;
                margin:10px;
            }
            a:hover{
              
                color:yellow;
            }
            *:focus {
               outline: none;
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
            #waytoframe3{
               
                position:relative;
                margin:10px;
               
                width:30px;
                font-size:15px;
                height:18px;
                background-color:rgba(90,80,50,0.5);
                color:gray;
                padding:8px;
                top:13%;
                left:70%;
                margin:5px;
                padding-left:8px;
                border-radius:5px;
            }
            #waytoframe3:hover{
                background-color:#D8D8D8;
                color:yellow;
            }
        </style>
    </head>
    <body>
        <%
            Connection con=null;
			Statement st=null;
			ResultSet rs=null;
            try{
                       PrintWriter pw=response.getWriter();
		       Class.forName("oracle.jdbc.driver.OracleDriver");
                       System.out.println("Loaded");
		       con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","tornado","tornado");
		       System.out.println("connected");
		       st=con.createStatement();
                       System.out.println("statement created");
                       
         
            rs= st.executeQuery("select * from timetableMon where username='"+ (String)session.getAttribute("uname")+"'");
            %>
            
            <div id="head1"> <h3 id="head">Today Classes</h3></div><hr>
            <div id="waytoframe3">Close </div>
            <%
            while(rs.next()){
                for(int i=2;i<=9;i++){
                    if(rs.getString(i)==null)
                        continue;
                    String v=rs.getString(i);
            %>
                    <div class="period">
                       
                        <a href="frame2.jsp?id=<%= rs.getString(i)%>" target="frame5" id="<%= rs.getString(i)%>"><h4 style=" position:relative;"> Period <%=i-1%> :<%=rs.getString(i)%></h4></a>
                    </div>
                    
                    
                    
                    
                    <%
                }
            }
            }
            catch(Exception e){
                e.printStackTrace();
            }
finally{
  try { con.close(); } catch (SQLException sqle) {}
}
        %>
        <script>
            
            /* $("a").click(function ajaxs(k){
             var xmlhttp = new XMLHttpRequest();
             alert("hai2");
              xmlhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                  myFunction(this);
                }
              };    alert("hai3");
              xmlhttp.open("GET", "Ajaxsearch1?id="+$(this).attr("id"), true);
              xmlhttp.send();
                  alert("hai4");
            });
                function myFunction(con,id){
                    
                  alert(con.responseText);
                }
            $("#head1").click(function(){
                $(".period").slideToggle();
              });*/
    
              $("#waytoframe3").click(function(){
                  
              });
        </script>
    </body>
</html>
