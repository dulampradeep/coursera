package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class index1_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("<html>\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta charset=\"utf-8\">\n");
      out.write("\t\t<title>Registration form</title>\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"style2c.css\">\n");
      out.write("\t\t<script src=\"jquery.min.js\"></script>\n");
      out.write("\t\t<script src=\"smtp.js\"></script>\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\t<img src=\"stuff/logo2.png\" class=\"logo\">\n");
      out.write("\t\t<div class=\"box\">\n");
      out.write("\n");
      out.write("\t\t\t<h1>Registration</h1>\n");
      out.write("\t\t\t<form method=\"post\">\n");
      out.write("\t\t\t\t<label for=\"t1\">User Name</label><br><br>\n");
      out.write("\t\t\t\t<input type=\"text\" class=\"sub2\" name=\"Full Name\" id=\"t1\" placeholder=\"Enter fullname\" autofocus><br>\n");
      out.write("\t\t\t\t<label for=\"e1\">Email</label><br><br>\n");
      out.write("\t\t\t\t<input type=\"text\" name=\"Email\" class=\"sub2\" id=\"e1\" placeholder=\"Enter Email\"><br>\n");
      out.write("\t\t\t\t<input  id=\"verify\" type=\"button\" name=\"verify\" value=\"verify\">\n");
      out.write("\t\t\t\t<label for=\"p1\">password</label><br><br>\n");
      out.write("\t\t\t\t<input type=\"password\" name=\"password\" class=\"sub2\" id=\"p1\" placeholder=\"Enter password\"><br>\n");
      out.write("\t\t\t\t<span id=\"showpass\"><img src=\"stuff/hide.png\" width=\"20px\" height=\"20px\" alt=\"no image\" id=\"myicon\" /></span>\n");
      out.write("\t\t\t\t<label for=\"p2\">Re-enter</label><br><br>\n");
      out.write("\t\t\t\t<input type=\"password\" name=\"re-enter\" class=\"sub2\" id=\"p2\" placeholder=\"Re enter password\"><br>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"access\">\n");
      out.write("\t\t\t\t<label for=\"acc\">Access</label>&nbsp&nbsp&nbsp&nbsp\n");
      out.write("\t\t\t\t<select id=\"acc\">\n");
      out.write("\t\t\t\t\t<option>Admin</option>\n");
      out.write("\t\t\t\t\t<option>Authority</option>\n");
      out.write("\t\t\t\t\t<option selected>Citizen</option>\n");
      out.write("\t\t\t\t</select>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<label class=\"next sub3\" for=\"n1\">Phone number</label><br>\n");
      out.write("\t\t\t\t<input type=\"number\" name=\"ph number\"  class=\"sub2 sub3\" id=\"n1\" placeholder=\"Enter phone number\">\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t<div class=\"gender\">\n");
      out.write("\t\t\t\t<label id=\"genhead\">Gender</label><br><br>\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"gender\"   id=\"gender\"><label  id=\"genmale\">Male</label>\n");
      out.write("\t\t\t\t<input type=\"radio\" name=\"gender\"   id=\"gender\" ><label  id=\"genfemale\">Female</label>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"adddr\">\n");
      out.write("\t\t\t\t<label for=\"addr\">Address</label><br><br>\n");
      out.write("\t\t\t\t<textarea id=\"addr\" placeholder=\"Adderess\" rows=\"7\" cols=\"38\"></textarea>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<input class=\"sub\" type=\"reset\" name=\"reset\" value=\"Reset\">\n");
      out.write("\t\t\t\t<input class=\"sub\" type=\"submit\" name=\"submit\" value=\"Register\">\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<script>\n");
      out.write("\t\t\t//$(\".box\").click(function(){\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t$(\"#p2\").blur(function(){\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t\tif(($(\"p1\").val()).equals( $(\"p2\").val() )){\n");
      out.write("\t\t\t\t\t\t$(\"#p1\").css(\"border\",\"1px solid darkgreen\");\n");
      out.write("\t\t\t\t\t\t$(\"#p2\").css(\"border\",\"1px solid darkgreen\");\n");
      out.write("\t\t\t\t\t}else{\n");
      out.write("\t\t\t\t\t\t$(\"#p2\").css(\"border\",\"1px solid red\");\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t$(\"#e1\").blur(function(){\n");
      out.write("\t\t\t\tvar x=validate($(\"#e1\").val());alert(x);\n");
      out.write("\t\t\t\t\tif(x){\n");
      out.write("\t\t\t\t\t\t$(\"#e1\").css(\"border\",\"1px solid darkgreen\");\n");
      out.write("\t\t\t\t\t}else{\n");
      out.write("\t\t\t\t\t\t$(\"#e1\").css(\"border\",\"1px solid red\");\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t\tfunction validate(email){\n");
      out.write("\t\t\t\t    var regex =  /^([\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4})?$/;\n");
      out.write("\t\t\t\t\treturn regex.test(email);\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t$(\"#p1\").keydown(function(){\n");
      out.write("\t\t\t\t\t$(\"#showpass\").css(\"display\",\"inline\");\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t\t$(\"#showpass\").click(function(){\n");
      out.write("\t\t\t\tif($(\"#myicon\").attr('src') == \"stuff/show.png\"){\n");
      out.write("\t\t\t\t\t$(\"#myicon\").attr(\"src\",\"stuff/hide.png\");\n");
      out.write("\t\t\t\t\t$(\"#p1\").attr(\"type\",\"password\");\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t}else{\n");
      out.write("\t\t\t\t\t$(\"#myicon\").attr(\"src\",\"stuff/show.png\");\n");
      out.write("\t\t\t\t\t$(\"#p1\").attr(\"type\",\"text\");\t\t\t\t\t\n");
      out.write("\t\t\t\t}\t\t\t\t\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t\t$(\".logo\").animate({\n");
      out.write("\t\t\t\tleft:\"300px\"\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t},1000);\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t$(\".box\").animate({\n");
      out.write("\t\t\t\t'margin-left':\"10px\",\n");
      out.write("\t\t\t\t'height':\"600px\",\n");
      out.write("\t\t\t\t width:\"900px\" \n");
      out.write("\t\t\t\t \n");
      out.write("\t\t\t\t },\n");
      out.write("\t\t\t\t 1000,function(){\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t$(this).css(\"border-style\",\"outset\");\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t\t });\n");
      out.write("\t\t\t\t  $(\".sub\").animate({\n");
      out.write("\t\t\t\t\tleft:\"480px\"\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t},1000);\n");
      out.write("\t\t\t\t$(\".sub\").animate({\n");
      out.write("\t\t\t\t\tbottom:\"30px\"\n");
      out.write("\t\t\t\t},300);\n");
      out.write("\t\t\t\t$(\".sub2\").animate({\n");
      out.write("\t\t\t\t\twidth:\"350px\"\n");
      out.write("\t\t\t\t},1300,function(){\n");
      out.write("\t\t\t\t$(\".access\").css(\"display\",\"inline\");\n");
      out.write("\t\t\t\t}); \n");
      out.write("\t\t\t\t$(\".gender\").css(\"display\",\"inline\");\n");
      out.write("\t\t\t\t$(\".sub3\").css(\"display\",\"inline\");\n");
      out.write("\t\t\t\t$(\".adddr\").css(\"display\",\"inline\");\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t//\t});\n");
      out.write("\t\t</script>\n");
      out.write("\t</body>\n");
      out.write("</html>");
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
