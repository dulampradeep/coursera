package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class frame4_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <style>\n");
      out.write("            *:focus {\n");
      out.write("               outline: none;\n");
      out.write("                }\n");
      out.write("            body{\n");
      out.write("               background-color:#59C4A9;\n");
      out.write("            }\n");
      out.write("            #logo{\n");
      out.write("                width:45px;\n");
      out.write("                height:45px;\n");
      out.write("                border-radius: 30%;\n");
      out.write("                position:absolute;\n");
      out.write("                top:15%;\n");
      out.write("                left:1%;\n");
      out.write("            }\n");
      out.write("            #login{\n");
      out.write("               \n");
      out.write("                padding:13px;\n");
      out.write("               width:50px;\n");
      out.write("                background-color:rgba(255,255,255,0.1);\n");
      out.write("                position:absolute;\n");
      out.write("                top:10px;\n");
      out.write("                left:90%;\n");
      out.write("                text-align:center;\n");
      out.write("                color:white;\n");
      out.write("                 text-decoration:none;\n");
      out.write("                  border-radius: 5%;\n");
      out.write("            }\n");
      out.write("            #logoname{\n");
      out.write("                 position:absolute;\n");
      out.write("                 top:7%;\n");
      out.write("                 left:5%;\n");
      out.write("                 font-family:sans-serif;\n");
      out.write("                 font-weight:bold;\n");
      out.write("                 font-style:oblique;\n");
      out.write("            }\n");
      out.write("            #nav{\n");
      out.write("                padding-left:25px;\n");
      out.write("            }\n");
      out.write("            #nav a{\n");
      out.write("                float:left;\n");
      out.write("                padding:13px;\n");
      out.write("                padding-right:50px;\n");
      out.write("                background-color:rgba(255,255,255,0.1);\n");
      out.write("                position:relative;\n");
      out.write("                top:3px;\n");
      out.write("                left:12%;\n");
      out.write("                text-decoration:none;\n");
      out.write("                color:white;\n");
      out.write("                border-radius: 3%;\n");
      out.write("            }\n");
      out.write("             #nav a:nth-child(1) {\n");
      out.write("                padding-left: 30px !important;\n");
      out.write("            }\n");
      out.write("            #nav a:nth-child(4) {\n");
      out.write("                padding-right: 30px !important;\n");
      out.write("            }\n");
      out.write("            #nav>a:hover{\n");
      out.write("                background-color:rgba(0,0,0,0.1);\n");
      out.write("            }\n");
      out.write("            #login:hover{\n");
      out.write("                background-color:rgba(0,0,0,0.1);\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <img id=\"logo\" src=\"stuff/logo2.png\" alt=\"logo\" />\n");
      out.write("        <p id=\"logoname\">Tornado</p>\n");
      out.write("        <div id=\"menu\">\n");
      out.write("            <nav id=\"nav\">\n");
      out.write("                <a href=\"Notice_Board.jsp\" target=\"frame2\">Home</a> \n");
      out.write("                <a href=\"home.jsp\" target=\"frame2\">CSS</a> \n");
      out.write("                <a href=\"register1.jsp\" target=\"frame2\">JavaScript</a> \n");
      out.write("                <a href=\"/jquery/\"  target=\"frame2\">jQuery</a>\n");
      out.write("            </nav>\n");
      out.write("            \n");
      out.write("        </div>\n");
      out.write("      \n");
      out.write("        <a  href=\"logout_session.jsp\" id=\"login\" >Logout</a>\n");
      out.write("         \n");
      out.write("    </body>\n");
      out.write("    <script>\n");
      out.write("        \n");
      out.write("    </script>\n");
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
