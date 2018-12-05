package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("   \n");
      out.write("    <head>\n");
      out.write("\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("\n");
      out.write("        <title>Clínica Médica - Entrar</title>\n");
      out.write("\n");
      out.write("        <link rel=\"shortcut icon\" href=\"img/logo1.png\" >\n");
      out.write("        <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\" media=\"screen\" type=\"text/css\" />\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"https://fonts.googleapis.com/css?family=Open+Sans|Raleway|Candal\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/font-awesome.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/bootstrap.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/stylelogin.css\">\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            HttpSession sessao = request.getSession(true);
            if (!sessao.isNew()) {
                String Mens_Login = (String) sessao.getAttribute("mensagem");
                if (Mens_Login != null) {
        
      out.write("\n");
      out.write("             <h4 style=\"color: red;text-align: center\" >");
      out.print(Mens_Login);
      out.write("</h4>\n");
      out.write("        ");

                }
            }
            
        
      out.write("\n");
      out.write("         <div class=\"row col-md-12\"></div>\n");
      out.write("        <div class=\"row \">\n");
      out.write("            <div class=\"login-card\">\n");
      out.write("                <h1>Entrar</h1><br>\n");
      out.write("                <form class=\"form-group\" action=\"autenticador\">\n");
      out.write("                    <input type=\"text\" name=\"email\" placeholder=\"Email\">\n");
      out.write("                    <input type=\"password\" name=\"senha\" placeholder=\"Senha\">\n");
      out.write("                    <input type=\"submit\" name=\"login\" class=\"login login-submit\" value=\"Entrar\">\n");
      out.write("                </form>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>\n");
      out.write("\n");
      out.write("    \n");
      out.write("\n");
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
