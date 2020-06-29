package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;
import java.sql.*;

public final class frame2_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <script src=\"jquery.min.js\"></script>\n");
      out.write("        <style>\n");
      out.write("            body{\n");
      out.write("                margin-top: 10px;\n");
      out.write("                background-color: white;\n");
      out.write("                border-radius:5px;\n");
      out.write("                margin:10px;\n");
      out.write("            }\n");
      out.write("             #period:hover{\n");
      out.write("                background-color:#D8D8D8;\n");
      out.write("                color:yellow;\n");
      out.write("            }\n");
      out.write("            #period{\n");
      out.write("                color:gray;\n");
      out.write("                font-size:15px;\n");
      out.write("                background-color:#F9EDEE;\n");
      out.write("                padding:3px;\n");
      out.write("               \n");
      out.write("                margin-top:20px;\n");
      out.write("                border:0;\n");
      out.write("                border-radius:10px;\n");
      out.write("                \n");
      out.write("                height:40px;\n");
      out.write("                width:99%;\n");
      out.write("                box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.2), 0 2px 10px 0 rgba(0, 0, 0, 0.19);\n");
      out.write("            }\n");
      out.write("            p{\n");
      out.write("                padding-left: 30px;\n");
      out.write("                padding-right: 120px;\n");
      out.write("                margin:10px;\n");
      out.write("                top:13px;\n");
      out.write("            }\n");
      out.write("            a{\n");
      out.write("                text-decoration:none;\n");
      out.write("                color:gray;\n");
      out.write("               \n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            #head{\n");
      out.write("                font-size:15px;\n");
      out.write("                font-family:Helvetica,courier,arial;\n");
      out.write("                text-align: center;\n");
      out.write("                 color:green;\n");
      out.write("                 position:relative;\n");
      out.write("                 top:10px;\n");
      out.write("            }\n");
      out.write("            #head1{\n");
      out.write("                background-color: gray;\n");
      out.write("             \n");
      out.write("                height:40px;\n");
      out.write("                border-radius:7px;\n");
      out.write("            }\n");
      out.write("            th{\n");
      out.write("                background-color:#F9EDEE;\n");
      out.write("                width:130px;\n");
      out.write("                height:40px;\n");
      out.write("                text-align:center;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            table{\n");
      out.write("                border-collapse: collapse;\n");
      out.write("                width:100%;\n");
      out.write("            }\n");
      out.write("            .sub{\n");
      out.write("                color:gray;\n");
      out.write("                background-color:#F9EDEE;\n");
      out.write("                height:38px;\n");
      out.write("                width:80px;\n");
      out.write("                border-radius:10%;\n");
      out.write("                padding:10px;\n");
      out.write("                position:relative;\n");
      out.write("                top:20px;\n");
      out.write("                left:78%;\n");
      out.write("                margin:0;\n");
      out.write("                border:0;\n");
      out.write("                box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.2), 0 4px 15px 0 rgba(0, 0, 0, 0.19);\n");
      out.write("            }\n");
      out.write("            .sub:hover{\n");
      out.write("                background-color:lightgrey;\n");
      out.write("            }\n");
      out.write("            *:focus {\n");
      out.write("               outline: none;\n");
      out.write("                }\n");
      out.write("            #search {\n");
      out.write("                position:relative;\n");
      out.write("                top:20%;\n");
      out.write("                left:72%;\n");
      out.write("                border:0;\n");
      out.write("                background-color:rgba(90,80,50,0.5);\n");
      out.write("                width:25%;\n");
      out.write("                padding-left:15px;\n");
      out.write("                height:33px;\n");
      out.write("                border-radius:5px;\n");
      out.write("                text-decoration: none;\n");
      out.write("                color:whitesmoke;\n");
      out.write("                display:inline;\n");
      out.write("                margin:10px;\n");
      out.write("            }\n");
      out.write("            #waytoframe3{\n");
      out.write("                display:inline;\n");
      out.write("                position:absolute;\n");
      out.write("                margin:10px;\n");
      out.write("                float:left;\n");
      out.write("                width:100px;\n");
      out.write("                font-size:17px;\n");
      out.write("                height:20px;\n");
      out.write("                background-color:rgba(90,80,50,0.5);\n");
      out.write("                color:gray;\n");
      out.write("                padding:8px;\n");
      out.write("                top:77px;\n");
      out.write("                left:1.5%;\n");
      out.write("                margin:5px;\n");
      out.write("                padding-left:20px;\n");
      out.write("                border-radius:5px;\n");
      out.write("            }\n");
      out.write("            #waytoframe3:hover{\n");
      out.write("                background-color:#D8D8D8;\n");
      out.write("                color:yellow;\n");
      out.write("            }\n");
      out.write("            #search1{\n");
      out.write("                position:absolute !important;\n");
      out.write("                top:12% !important;\n");
      out.write("                left:25% !important;\n");
      out.write("                width:330px;\n");
      out.write("                height:25px;\n");
      out.write("                padding-left:20px !important;\n");
      out.write("                padding-top:7px;\n");
      out.write("                text-align: center;\n");
      out.write("                font-weight: bold;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body id=\"body\">");

   
                       String a=request.getParameter("id");
                       
      out.write("\n");
      out.write("                       <div id=\"head1\"> <h3 id=\"head\">");
      out.print(a);
      out.write("</h3></div><hr>\n");
      out.write("                       <a href=\"frame3.jsp?id2=");
      out.print(a);
      out.write("\" target=\"frame3\"> <p id=\"waytoframe3\">Students </p></a>\n");
      out.write("                       <p id=\"search1\"/>time</p>\n");
      out.write("                       <input type=\"text\" id=\"search\" placeholder=\"Enter to search\" autofocus />\n");
      out.write("             \n");
      out.write("                <table>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Regd No</th>\n");
      out.write("                        <th>Name</th>\n");
      out.write("                        <th> Attendence</th>\n");
      out.write("                        \n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("                \n");
      out.write("                <form name=\"form\" action=\"PostAttendence?id=");
      out.print(a);
      out.write(" \" method=\"post\">\n");
      out.write("                <div id=\"replace\">  \n");
      out.write(" \n");
      out.write("                </div>\n");
      out.write("                     <input class =\"sub\" type=\"reset\" value=\"clear\" style=\"left:75%;\"/>\n");
      out.write("                    <input class =\"sub\" type=\"submit\" value=\"submit\"/>\n");
      out.write("                \n");
      out.write("                </form>\n");
      out.write("        <script>\n");
      out.write("            \n");
      out.write("                $(document).ready(function (){\n");
      out.write("                 var xmlhttp = new XMLHttpRequest();\n");
      out.write("            \n");
      out.write("              xmlhttp.onreadystatechange = function() {\n");
      out.write("                    \n");
      out.write("                 if (this.readyState == 4 && this.status == 200) {\n");
      out.write("                  $(\"#replace\").html(this.responseText);\n");
      out.write("                }  \n");
      out.write("                 \n");
      out.write("                };\n");
      out.write("                xmlhttp.open(\"GET\", \"Ajaxsearch1?id1=\"+$(\"#head\").html()+\"&id2= \",true);\n");
      out.write("                 xmlhttp.send();\n");
      out.write("                   \n");
      out.write("    });\n");
      out.write("            \n");
      out.write("            $(\"#search\").keyup(function ajaxs(){\n");
      out.write("                var sql=\"select * from student inner join \"+$(\"#head\").html()+\" on student.id=\"+$(\"#head\").html()+\".id where student.first_name like '\"+$(this).val()+\"%'\";\n");
      out.write("               \n");
      out.write("              \n");
      out.write("                 var xmlhttp = new XMLHttpRequest();\n");
      out.write("            \n");
      out.write("              xmlhttp.onreadystatechange = function() {\n");
      out.write("                    \n");
      out.write("                 if (this.readyState == 4 && this.status == 200) {\n");
      out.write("                  senttttt(this);\n");
      out.write("                }  \n");
      out.write("                 \n");
      out.write("                };\n");
      out.write("                xmlhttp.open(\"GET\", \"Ajaxsearch1?id1=\"+$(\"#head\").html()+\"&id2=\"+$(this).val(),true);\n");
      out.write("                 xmlhttp.send();\n");
      out.write("                   \n");
      out.write("            });\n");
      out.write("            function senttttt(k){\n");
      out.write("                $(\"#replace\").html(k.responseText);\n");
      out.write("            }\n");
      out.write("            setInterval(function(){\n");
      out.write("                $(\"#search1\").html(\"Time : \"+new Date().toLocaleTimeString()+\"  Date : \"+new Date().toLocaleDateString());\n");
      out.write("            },10);\n");
      out.write("            function find(x){\n");
      out.write("                switch(x){\n");
      out.write("                   case 7:return \"Sunday\";break; \n");
      out.write("                   case 1:return \"Monday\";break; \n");
      out.write("                   case 2:return \"Tuesday\";break; \n");
      out.write("                   case 3:return \"Wednesday\";break; \n");
      out.write("                   case 4:return \"Thursday\";break;\n");
      out.write("                   case 5:return \"Friday\";break; \n");
      out.write("                   case 6:return \"Saturday\";break; \n");
      out.write("                   \n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
