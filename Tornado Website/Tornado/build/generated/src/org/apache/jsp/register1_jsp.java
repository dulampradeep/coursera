package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register1_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<!doctype html>\n");
      out.write("<html>\n");
      out.write("\t<head>\n");
      out.write("\t\t<meta charset=\"utf-8\">\n");
      out.write("\t\t<title>Registration form</title>\n");
      out.write("\t\t<link rel=\"stylesheet\" type=\"text/css\" href=\"style2.css\">\n");
      out.write("\t</head>\n");
      out.write("\t<body>\n");
      out.write("\t<img src=\"logo2.png\" class=\"logo\">\n");
      out.write("\t\t<div class=\"box\">\n");
      out.write("\n");
      out.write("\t\t\t<h1>Registration</h1>\n");
      out.write("\t\t\t<form>\n");
      out.write("\t\t\t\t<p>Full Name</p><br>\n");
      out.write("\t\t\t\t<input type=\"text\" name=\"Full Name\" placeholder=\"Enter fullname\">\n");
      out.write("\t\t\t\t<p>Email</p><br>\n");
      out.write("\t\t\t\t<input type=\"text\" name=\"Email\" placeholder=\"Enter Email\">\n");
      out.write("\t\t\t\t<p>password</p><br>\n");
      out.write("\t\t\t\t<input type=\"password\" name=\"password\" placeholder=\"Enter password\">\n");
      out.write("\t\t\t\t<p>Re-enter</p><br>\n");
      out.write("\t\t\t\t<input type=\"password\" name=\"re-enter\" placeholder=\"Re enter password\">\n");
      out.write("\t\t\t\t<p>Phone number</p><br>\n");
      out.write("\t\t\t\t<input type=\"number\" name=\"ph number\" placeholder=\"Enter phone number\">\n");
      out.write("\t\t\t\t<input type=\"reset\" name=\"reset\" value=\"Reset\">\n");
      out.write("\t\t\t\t<input type=\"submit\" name=\"submit\" value=\"Register\">\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
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
