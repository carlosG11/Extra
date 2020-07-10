<%@page contentType="text/html" language="java" import="java.util.*,aux2.vo.VOUsuario" session="true" pageEncoding="UTF-8"%>
<%
    String sMensaje= (String)request.getAttribute("mensaje");
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    VOUsuario voUsuarioItem;
    ArrayList <VOUsuario>resultados;
    resultados = (ArrayList)request.getAttribute("resultados");
    if (null==resultados)
        resultados = new <VOUsuario>ArrayList();    
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
            <div class="container bootstrap snippet">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <span><%= sMensaje %></span>
                            <h1>Buscar Usuarios</h1>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <h2>
                                    <%=resultados.size()%> Resultados de Usuarios: <span class="text-navy">"Usuario"</span>
                                </h2>
                                <small>Request time  (0.23 seconds)</small>

                                <div class="search-form">
                                    <form name="formulario" method="post" action="SVTAdminUsuarioBuscar" >
                                        <div class="input-group">
                                            <input type="text" placeholder="nombre" name="nombre" class="form-control input-lg">
                                            <div class="input-group-append">
                                                <button class="btn btn-secondary"><i class="fa fa-search"></i></button>
                                            </div>
                                        </div>
                                    </form>

                                    <form name="formularioConsultar" id="formularioConsultar" method="post" action="SVTAdminUsuarioConsultar" >
                                        <input type="hidden" name="id" value="">
                                    </form>                        
                                </div>

                                <%
                                    for (int i=0; i<resultados.size();i++){
                                        voUsuarioItem = (VOUsuario)resultados.get(i);

                                %>
                                    <div class="hr-line-dashed"></div>
                                    <div class="search-result">                                                                    
                                        <h3><a href="#" onclick="consultarUsuario(<%=voUsuarioItem.getId()%>);" ><%=voUsuarioItem.getId()%> - <%=voUsuarioItem.getUsuario()%></a></h3>
                                        <a href="#" onclick="consultarUsuario(<%=voUsuarioItem.getId()%>);" class="search-link">perfil: <%=voUsuarioItem.getPerfil()%></a>
                                        <p>
                                            <%=voUsuarioItem.getNombre()%> <%=voUsuarioItem.getPaterno()%> <%=voUsuarioItem.getMaterno()%>
                                        </p>
                                    </div>                    
                                <%                            
                                    }                                        
                                %>

                            </div>
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
        
        <script language="javascript">
            function consultarUsuario(id){
                document.formularioConsultar.id.value=id;
                document.formularioConsultar.submit();
            }
        </script>
        
	</body>
</html>