<%-- 
    Document   : regist
    Created on : May 13, 2019, 11:58:28 AM
    Author     : dprab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
           function hai(){
               open("edit.jsp");
           } 
           
        </script>
    </head>
    <body>
    <center>
        <fieldset>
             <legend>Register form</legend>
            <form action="insert.jsp">
                <table border="1" cellpadding="3px" cellspacing="4px">
                   
                    <tr><center><h1>Registration form<h1></center></tr>
                    <tr>
                        <td>
                            Enter name :
                        </td>
                        <td>
                             <input type="text" name="t1" placeholder="Enter name " >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Enter email :
                        </td>
                        <td>
                             <input type="text" name="t2" placeholder="Enter name " >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Enter phno :
                        </td>
                        <td>
                             <input type="text" name="t3" placeholder="Enter phno " >
                        </td>
                    </tr>
                    <tr>
                        <td>
                            choose course:
                        </td>
                        <td>
                            <select name="s1"><option value="Choose one">Choose one</option><option value="c">c</option><option value="c++">c++</option><option value="java">java</option><option value="python">python</option><option value="oracle">oracle</option><option value="adv java">adv java</option></select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            type :
                        </td>
                        <td>
                            <input type="radio" name="r1" value="online">online<br><input type="radio" name="r1" value="ofline">ofline
                        </td>
                    </tr>
                    <tr>
                        <td><center>      <input type="button" name="b2" value="edit" onclick=hai()></center></td>
                        <td> <center>      <input type="submit" name="b1" value="submit"></center></td>
                        
                    </tr>
                </table>
            </form>
        </fieldset>
    </center>
    </body>
</html>
