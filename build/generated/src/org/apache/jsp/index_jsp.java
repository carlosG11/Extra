package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"zxx\">\r\n");
      out.write("<head>\r\n");
      out.write("\t<title>INSTYLR | Fashion HTML Template</title>\r\n");
      out.write("\t<meta charset=\"UTF-8\">\r\n");
      out.write("\t<meta name=\"description\" content=\"Instyle Fashion HTML Template\">\r\n");
      out.write("\t<meta name=\"keywords\" content=\"instyle, fashion, html\">\r\n");
      out.write("\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("\t<!-- Favicon -->\r\n");
      out.write("\t<link href=\"img/favicon.ico\" rel=\"shortcut icon\"/>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Stylesheets -->\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/font-awesome.min.css\"/>\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/owl.carousel.min.css\"/>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Main Stylesheets -->\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/style.css\"/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<!--[if lt IE 9]>\r\n");
      out.write("\t\t<script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>\r\n");
      out.write("\t\t<script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\r\n");
      out.write("\t<![endif]-->\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<!-- Page Preloder -->\r\n");
      out.write("\t<div id=\"preloder\">\r\n");
      out.write("\t\t<div class=\"loader\"></div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Header section -->\r\n");
      out.write("\t<header class=\"header-section\">\r\n");
      out.write("\t\t<nav class=\"navbar navbar-expand-md navbar-dark bg-dark site-navbar\">\r\n");
      out.write("\t\t\t<a class=\"navbar-brand site-logo\" href=\"index.html#\">\r\n");
      out.write("\t\t\t\t<h2><span>AUX</span>Style</h2>\r\n");
      out.write("\t\t\t\t<p>Fashion Forward</p>\r\n");
      out.write("\t\t\t</a>\r\n");
      out.write("\t\t\t<button class=\"navbar-toggler d-lg-none\" type=\"button\" data-toggle=\"collapse\" data-target=\"#collapsibleNavId\" aria-controls=\"collapsibleNavId\"\r\n");
      out.write("\t\t\t\taria-expanded=\"false\" aria-label=\"Toggle navigation\">\r\n");
      out.write("\t\t\t\t<span class=\"navbar-toggler-icon\"></span>\r\n");
      out.write("\t\t\t</button>\r\n");
      out.write("\t\t\t<div class=\"collapse navbar-collapse\" id=\"collapsibleNavId\">\r\n");
      out.write("\t\t\t\t<!-- Main menu -->\r\n");
      out.write("\t\t\t\t<ul class=\"navbar-nav ml-auto mt-2 mt-lg-0\">\r\n");
      out.write("\t\t\t\t\t<li class=\"nav-item\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"nav-link\" href=\"index.html\">Home</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li class=\"nav-item\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"nav-link\" href=\"about.html\">the Brand</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li class=\"nav-item\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"nav-link\" href=\"portfolio.html\">Portfolio</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li class=\"nav-item\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"nav-link\" href=\"#\">Shop</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li class=\"nav-item\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"nav-link\" href=\"blog.html\">blog</a>\r\n");
      out.write("\t\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t\t<li class=\"nav-item\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"nav-link\" href=\"contact.html\">Contact</a>\r\n");
      out.write("\t\t\t\t\t</li>                                                                \r\n");
      out.write("\t\t\t\t\t<li class=\"nav-item\">\r\n");
      out.write("\t\t\t\t\t\t<a class=\"nav-link\" href=\"login.jsp\">Login</a>\r\n");
      out.write("\t\t\t\t\t</li>                                                                \r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t<div class=\"social-links my-2 my-lg-0\">\r\n");
      out.write("\t\t\t\t\t<a href=\"#\"><i class=\"fa fa-pinterest\"></i></a>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\"><i class=\"fa fa-facebook\"></i></a>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\"><i class=\"fa fa-twitter\"></i></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</nav>\r\n");
      out.write("\t</header>\r\n");
      out.write("\t<!-- Header section end-->\r\n");
      out.write("\r\n");
      out.write("\t<!-- Hero section -->\r\n");
      out.write("\t<section class=\"hero-section\">\r\n");
      out.write("\t\t<div class=\"hero-slider owl-carousel\">\r\n");
      out.write("\t\t\t<div class=\"hs-item set-bg\" data-setbg=\"img/slider/1.jpg\" data-hash=\"slide-1\">\r\n");
      out.write("\t\t\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t\t\t<h2>Style is forever</h2>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\" class=\"site-btn\">Read More <i class=\"fa fa-angle-double-right\"></i></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"next-hs set-bg\" data-setbg=\"img/slider/2.jpg\">\r\n");
      out.write("\t\t\t\t\t<a href=\"#slide-2\" class=\"nest-hs-btn\">Next</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<div class=\"hs-item set-bg\" data-setbg=\"img/slider/2.jpg\" data-hash=\"slide-2\">\r\n");
      out.write("\t\t\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t\t\t<h2>Style is forever</h2>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\" class=\"site-btn\">Read More <i class=\"fa fa-angle-double-right\"></i></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"next-hs set-bg\" data-setbg=\"img/slider/1.jpg\">\r\n");
      out.write("\t\t\t\t\t<a href=\"#slide-1\" class=\"nest-hs-btn\">Next</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"hero-social-warp\">\r\n");
      out.write("\t\t\t<p>Follow us on Social MEdia</p>\r\n");
      out.write("\t\t\t<div class=\"hero-social-links\">\r\n");
      out.write("\t\t\t\t<a href=\"#\"><i class=\"fa fa-behance\"></i></a>\r\n");
      out.write("\t\t\t\t<a href=\"#\"><i class=\"fa fa-dribbble\"></i></a>\r\n");
      out.write("\t\t\t\t<a href=\"#\"><i class=\"fa fa-twitter\"></i></a>\r\n");
      out.write("\t\t\t\t<a href=\"#\"><i class=\"fa fa-facebook\"></i></a>\r\n");
      out.write("\t\t\t\t<a href=\"#\"><i class=\"fa fa-pinterest\"></i></a>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</section>\r\n");
      out.write("\t<!-- Hero section end-->\r\n");
      out.write("\r\n");
      out.write("\t<!-- Intro section -->\r\n");
      out.write("\t<section class=\"intro-section spad\">\r\n");
      out.write("\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-5\">\r\n");
      out.write("\t\t\t\t\t<img src=\"img/intro-img.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-7 intro-text\">\r\n");
      out.write("\t\t\t\t\t<span>Aenean quis velit pulvinar,</span>\r\n");
      out.write("\t\t\t\t\t<h2>\"I firmly believe that with the right footwear one can rule the world.\"</h2>\r\n");
      out.write("\t\t\t\t\t<p>Aenean quis velit pulvinar, pellentesque neque vel, laoreet orci. Suspendisse potenti. Donec congue vel justo eget malesuada. In arcu justo, sagittis consequat pulvinar quis, pretium quis massa. Vestibulum nec nibh eu nisi rutrum iaculis. Pellentesque placerat sit amet leo in vestibu-lum. Suspendisse quam neque, rutrum vel scelerisque eu</p>\r\n");
      out.write("\t\t\t\t\t<a href=\"#\" class=\"site-btn sb-dark\">Read More <i class=\"fa fa-angle-double-right\"></i></a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</section>\r\n");
      out.write("\t<!-- Intro section end-->\r\n");
      out.write("\r\n");
      out.write("\t<!-- Portfolio section -->\r\n");
      out.write("\t<section class=\"portfolio-section\">\r\n");
      out.write("\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-4 col-md-7\">\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/1.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-3 col-md-5\">\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/2.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-5 col-md-12\">\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/3.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-6 col-md-12\">\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/4.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/5.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/7.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/6.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/8.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/9.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/10.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/11.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-6 col-md-12\">\r\n");
      out.write("\t\t\t\t\t<a href=\"portfolio.html\" class=\"portfolio-item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/portfolio/12.jpg\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<h4>See More</h4>\r\n");
      out.write("\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</section>\r\n");
      out.write("\t<!-- Portfolio section end-->\r\n");
      out.write("\r\n");
      out.write("\t<!-- Blog section -->\r\n");
      out.write("\t<section class=\"blog-section spad\">\r\n");
      out.write("\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t<div class=\"blog-slider owl-carousel\">\r\n");
      out.write("\t\t\t\t<div class=\"blog-item\">\r\n");
      out.write("\t\t\t\t\t<div class=\"blog-thumb set-bg\" data-setbg=\"img/blog/1.jpg\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"blog-date\">\r\n");
      out.write("\t\t\t\t\t\t\t<h2>20</h2>\r\n");
      out.write("\t\t\t\t\t\t\t<p>Jan</p>\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"blog-head\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"blog-tags\">fashion, event, lifestyle</div>\r\n");
      out.write("\t\t\t\t\t\t<h2><a href=\"single-blog.html\">Our fashion App</a></h2>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<p>Aenean quis velit pulvinar, pellentesque neque vel, laoreet orci. Suspendisse po-tenti. Donec congue vel justo eget malesuada. In arcu justo, sagittis consequat pulvinar quis, pretium quis massa. Vestibulum nec nibh eu nisi rutrum iaculis. Pellentesque placerat sit amet leo in vestibulum. Suspendisse quam neque, rutrum vel scelerisque eu.</p>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"blog-item\">\r\n");
      out.write("\t\t\t\t\t<div class=\"blog-thumb set-bg\" data-setbg=\"img/blog/2.jpg\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"blog-date\">\r\n");
      out.write("\t\t\t\t\t\t\t<h2>20</h2>\r\n");
      out.write("\t\t\t\t\t\t\t<p>Jan</p>\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"blog-head\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"blog-tags\">fashion, event, lifestyle</div>\r\n");
      out.write("\t\t\t\t\t\t<h2><a href=\"single-blog.html\">Our fashion App</a></h2>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<p>Aenean quis velit pulvinar, pellentesque neque vel, laoreet orci. Suspendisse po-tenti. Donec congue vel justo eget malesuada. In arcu justo, sagittis consequat pulvinar quis, pretium quis massa. Vestibulum nec nibh eu nisi rutrum iaculis. Pellentesque placerat sit amet leo in vestibulum. Suspendisse quam neque, rutrum vel scelerisque eu.</p>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"blog-item\">\r\n");
      out.write("\t\t\t\t\t<div class=\"blog-thumb set-bg\" data-setbg=\"img/blog/3.jpg\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"blog-date\">\r\n");
      out.write("\t\t\t\t\t\t\t<h2>20</h2>\r\n");
      out.write("\t\t\t\t\t\t\t<p>Jan</p>\t\t\t\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"blog-head\">\r\n");
      out.write("\t\t\t\t\t\t<div class=\"blog-tags\">fashion, event, lifestyle</div>\r\n");
      out.write("\t\t\t\t\t\t<h2><a href=\"single-blog.html\">Our fashion App</a></h2>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<p>Aenean quis velit pulvinar, pellentesque neque vel, laoreet orci. Suspendisse po-tenti. Donec congue vel justo eget malesuada. In arcu justo, sagittis consequat pulvinar quis, pretium quis massa. Vestibulum nec nibh eu nisi rutrum iaculis. Pellentesque placerat sit amet leo in vestibulum. Suspendisse quam neque, rutrum vel scelerisque eu.</p>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</section>\r\n");
      out.write("\t<!-- Blog section end-->\r\n");
      out.write("\r\n");
      out.write("\t<!-- Back to top -->\r\n");
      out.write("\t<div class=\"container\">\r\n");
      out.write("\t\t<div class=\"backtotop\">\r\n");
      out.write("\t\t\t<div class=\"up-btn\" id=\"backTotop\">UP</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Footer section -->\r\n");
      out.write("\t<footer class=\"footer-section\">\r\n");
      out.write("\t\t<div class=\"container\">\r\n");
      out.write("\t\t\t<div class=\"row\">\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("\t\t\t\t\t<div class=\"footer-widget fw-about\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"img/footer-logo.png\" alt=\"\">\r\n");
      out.write("\t\t\t\t\t\t<p>Velit pulvinar, pellentesque neque vel, laoreet orci. Suspendisse potenti. Donec congue vel justo eget malesu ada. In arcu justo, sagittis consequat pulvinar.</p>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"fw-social\">\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"#\"><i class=\"fa fa-pinterest\"></i></a>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"#\"><i class=\"fa fa-facebook\"></i></a>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"#\"><i class=\"fa fa-twitter\"></i></a>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"#\"><i class=\"fa fa-dribbble\"></i></a>\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"#\"><i class=\"fa fa-behance\"></i></a>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("\t\t\t\t\t<div class=\"footer-widget resent-post\">\r\n");
      out.write("\t\t\t\t\t\t<h2 class=\"fw-title\">Recent Posts</h2>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"rp-item\">\r\n");
      out.write("\t\t\t\t\t\t\t<h4>Paris Fashion Week</h4>\r\n");
      out.write("\t\t\t\t\t\t\t<span>20 January 2019</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"rp-item\">\r\n");
      out.write("\t\t\t\t\t\t\t<h4>About Our Fashion App</h4>\r\n");
      out.write("\t\t\t\t\t\t\t<span>20 January 2019</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"rp-item\">\r\n");
      out.write("\t\t\t\t\t\t\t<h4>Simple Blog Post</h4>\r\n");
      out.write("\t\t\t\t\t\t\t<span>20 January 2019</span>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("\t\t\t\t\t<div class=\"footer-widget\">\r\n");
      out.write("\t\t\t\t\t\t<h2 class=\"fw-title\">Usefull Links</h2>\r\n");
      out.write("\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"#\">Testimonials</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"#\">About Us</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"#\">Jobs</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"#\">Reviews</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"#\">Marketing</a></li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><a href=\"#\">Subscribe</a></li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"col-lg-3 col-sm-6\">\r\n");
      out.write("\t\t\t\t\t<div class=\"footer-widget contact-widget\">\r\n");
      out.write("\t\t\t\t\t\t<h2 class=\"fw-title\">Contact</h2>\r\n");
      out.write("\t\t\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t\t\t<li><span>Address:</span>Main Str, no 23, New York</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><span>Phone:</span>+546 990221 123</li>\r\n");
      out.write("\t\t\t\t\t\t\t<li><span>Mail:</span>model@contact.com</li>\r\n");
      out.write("\t\t\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->\r\n");
      out.write("Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class=\"fa fa-heart-o\" aria-hidden=\"true\"></i> by <a href=\"https://colorlib.com\" target=\"_blank\">Colorlib</a>\r\n");
      out.write("<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->\r\n");
      out.write("\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</footer>\r\n");
      out.write("\t<!-- Footer section end -->\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<!--====== Javascripts & Jquery ======-->\r\n");
      out.write("\t<script src=\"js/jquery-3.2.1.min.js\"></script>\r\n");
      out.write("\t<script src=\"js/bootstrap.min.js\"></script>\r\n");
      out.write("\t<script src=\"js/owl.carousel.min.js\"></script>\r\n");
      out.write("\t<script src=\"js/main.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t</body>\r\n");
      out.write("</html>\r\n");
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