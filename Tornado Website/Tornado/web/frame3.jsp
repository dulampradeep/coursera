    <%-- 
    Document   : frame3
    Created on : Jan 15, 2020, 8:24:45 AM
    Author     : PRADEEP
--%>

<%@page import="java.sql.*"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <script src="jquery.min.js"></script>
        <style>
            *:focus {
               outline: none;
                }
            body::-webkit-scrollbar {
             display: none;
             
            }
            body{
                padding-top: 8px;
            }
            tr{
                margin:5px;
             
                color:gray;
            }
            td{
                margin:5px;
                padding: 10px;
               
            }
            table{
                margin-top: 50px;
                margin-left:30px;
                border:1px solid lightgray;
                border-collapse: collapse;
                width:85%;
                height:100px;
               
            }
          
             #head{
                font-size:15px;
                font-family:Helvetica,courier,arial;
                text-align: center;
                background-color:#D8D8D8;
                color:yellow;
                position:relative;
                top:10px;
                margin:0;
                padding:0;
            }
            .head1{
               background-color:#D8D8D8;
                color:gray;
                
                height:40px;
                border-radius:7px;
            }
            .period{
                border-radius: 15%;
               display: none;
            }
             .period img{
                border-radius: 15%;
                width:90px;
                height:90px;
                position: relative;
                top:-60px;
                left:calc(50% - 45px);
            }
            .edit{
                color:gray;
                background-color:#F9EDEE;
                height:30px;
                width:50px;
                border-radius:10%;
                padding:10px;
                top:130px;
                left:72%;
                margin:0;
                border:0;
                box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.2), 0 4px 15px 0 rgba(0, 0, 0, 0.19);
            }
            #search{
                position:relative;
                top:20%;
                left:30%;
                border:0;
                background-color:rgba(90,80,50,0.5);
                width:62%;
                padding-left:15px;
                height:33px;
                border-radius:5px;
                text-decoration: none;
                color:whitesmoke;
                display:inline;
                margin:10px;
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
                       con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","tornado","tornado");
		     
		       st=con.createStatement();
                       String b=request.getParameter("id1");
                       String a=request.getParameter("id2");
                      
                       String sql="select s.u_id,s.first_name,s.last_name,s.phone,s.section,d.status,s.id from student s,"+a+" d where  d.section='"+a+"' and s.id=d.id order by s.id";
                     rs= st.executeQuery(sql);
                   %><div class="head1 top" style="margin-bottom:0px;background-color: gray; color:green;"> <h3 id="head" style="margin-bottom:0px;background-color: gray; color:green;">Students</h3></div><hr>
                      <input type="text" id="search" placeholder="Enter to search" autofocus />
                   <%
            while(rs.next()){
                %>  
                   
                    <div class="head1 " id="<%=rs.getString(7)%>"> <h3 id="head"><%=rs.getString(7)%></h3></div>
                    <div class="period <%=rs.getString(7)%>">
                        
                        <button class="edit" value="<%=rs.getString(7)%>" style="position:relative;">Edit</button>
                        <table cellpadding="2px"  cellspacing="1px" >
                            <tr ><td colspan="2" style="padding:0px;margin:0;position:relative;"> <img src="stuff/image1.jpg" align="center" alt="logo"/></td><tr>
                            <tr><td> User Id :</td><td><%=rs.getString(1)%></td></tr>
                            <tr><td>First Name :</td><td><%=rs.getString(2)%></td></tr>
                            <tr> <td>Last Name  :</td><td><%=rs.getString(3)%></td></tr>
                            <tr><td>Phone :</td><td><%=rs.getString(4)%></td></tr>
                            <tr><td>Section :</td><td><%=rs.getString(5)%></td></tr>
                            <tr><td>Status :</td><td><%=rs.getString(6)%></td></tr>
                        </table>
                    </div><br>
                   
                    <%
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
          
            $(".head1").click(function(){
            
               
                var g=$(this).attr("id");
                var a="."+g;
                $(a).slideToggle();;
               
            });
            
        </script>
    </body>
</html>
