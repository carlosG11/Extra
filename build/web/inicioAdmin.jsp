<%@page contentType="text/html" language="java" import="java.util.*,aux2.vo.VOUsuario" session="true" pageEncoding="UTF-8"%>
<%
    String sMensaje= (String)request.getAttribute("mensaje");
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    if (null==sMensaje)
        sMensaje ="";    
    //valida que el usuario se haya identificado
    if(null==voUsuario){        
        %><jsp:forward page="login.jsp">
        <jsp:param name="mensaje" value="Es obligatorio identificarse."/>
        </jsp:forward><%           
    }
    //valida que el usuario sea administrador
    if(!"admin".equals(voUsuario.getPerfil())){
        %><jsp:forward page="login.jsp">
        <jsp:param name="mensaje" value="Se requiere acceso de Administrador."/>
        </jsp:forward><%           
    }    
%>

<!DOCTYPE html>
<html lang="zxx">
<head>
	<title>AUXSTYLE</title>
	<meta charset="UTF-8">
	<meta name="description" content="Instyle Fashion HTML Template">
	<meta name="keywords" content="instyle, fashion, html">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- Favicon -->
	<link href="img/favicon.ico" rel="shortcut icon"/>

	<!-- Stylesheets -->
	<link rel="stylesheet" href="css/bootstrap.min.css"/>
	<link rel="stylesheet" href="css/font-awesome.min.css"/>
	<link rel="stylesheet" href="css/owl.carousel.min.css"/>
	<!-- Main Stylesheets -->
	<link rel="stylesheet" href="css/style.css"/>


 	<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Header section -->
        <jsp:include page="adminMenu.jsp" /> 
	<!-- Header section end-->

	<!-- Contact page -->
	<section class="page-warp contact-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<span><%= sMensaje %></span>
						<h1>Inicio Administrador</h1>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Contact page end-->

	<!-- Footer section -->
	<footer class="footer-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-sm-6">
					<div class="footer-widget fw-about">
						<img src="img/footer-logo.png" alt="">
					</div>
				</div>
				<div class="col-lg-3 col-sm-6">
					<div class="footer-widget contact-widget">
						<ul>
							<li><span>Address:</span>Main Str, no 23, New York</li>
							<li><span>Phone:</span>+546 990221 123</li>
							<li><span>Mail:</span>model@contact.com</li>
						</ul>
					</div>
				</div>                            
			</div>
		</div>
	</footer>
	<!-- Footer section end -->        
        
	
	<!--====== Javascripts & Jquery ======-->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	</body>
</html>