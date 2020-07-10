<%@page contentType="text/html" language="java" import="java.util.*,aux2.vo.VOUsuario,aux2.vo.VOProducto" session="true" pageEncoding="UTF-8"%>
<%
    String sMensaje= (String)request.getAttribute("mensaje");
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    VOProducto resultado;
    resultado = (VOProducto)request.getAttribute("resultado");    
    if (null==sMensaje)
        sMensaje ="";    
    //valida que el usuario se haya identificado
    if(null==voUsuario){        
        %><jsp:forward page="login.jsp">
        <jsp:param name="mensaje" value="Es obligatorio identificarse."/>
        </jsp:forward><%           
    }
    //valida que el usuario sea administrador
    if(!"cliente".equals(voUsuario.getPerfil())){
        %><jsp:forward page="login.jsp">
        <jsp:param name="mensaje" value="Se requiere acceso de Cliente."/>
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
        <jsp:include page="clienteMenu.jsp" /> 
	<!-- Header section end-->

	<!-- Contact page -->
	<!-- Contact page end-->

	<!-- Footer section -->
	<section class="page-warp contact-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
                                            <span><%= sMensaje %></span>
                                            <h1>Consultar Usuario</h1>
					</div>
						<div class="row">
                                                        <div class="col-md-6">
                                                            <img src="img/Productos/<%=resultado.getId()%>.jpg">
							</div>
                                                        <div class="col-md-6">
                                                            &nbsp;
							</div>  
							<div class="col-md-6">
                                                            <label for="tipo">Tipo</label>
                                                            <input type="text" name="tipo" id="tipo" required value="<%=resultado.getTipo()%>" disabled>
							</div>
                                                        <div class="col-md-6">
                                                            &nbsp;
							</div>  
							<div class="col-md-6">
                                                            <label for="nombre">Nombre</label>
                                                            <input type="text" name="nombre" id="nombre" required value="<%=resultado.getNombre()%>" disabled>
							</div>
                                                        <div class="col-md-6">
                                                            &nbsp;
							</div>  
							<div class="col-md-6">
                                                            <label for="duracion">Duracion</label>
                                                            <input type="text" name="duracion" id="duracion" required value="<%=resultado.getDuracion()%>" disabled>
							</div>
                                                        <div class="col-md-6">
                                                            &nbsp;
							</div>  
							<div class="col-md-6">
                                                            <label for="precio">Precio</label>
                                                            <input type="text" name="precio" id="precio" value="<%=resultado.getPrecio()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            &nbsp;
							</div>                                                                                                                                                                    
							<div class="col-md-3">
                                                            <button class="btn btn-info btn-block my-4"  id="botonAgregar" name="botonAgregar" type="submit" >Agregar al carrito</button>                                                            
							</div>
						</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Footer section end -->        
        
	
	<!--====== Javascripts & Jquery ======-->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	</body>
</html>