<%@page contentType="text/html" language="java" import="java.util.*,aux2.vo.VOUsuario" session="true" pageEncoding="UTF-8"%>
<%
    String sMensaje= (String)request.getAttribute("mensaje");
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    VOUsuario resultado = voUsuario;
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
                                            <h1>Consultar Mi Perfil</h1>
					</div>
					<form class="comment-form" name="consultarPerfil" method="post" action="SVTAdminPerfilModificar" onsubmit="return verificarEnvio();">
						<div class="row">
							<div class="col-md-6">
                                                            <label for="id">Identificador</label>
                                                            <input type="text" name="id" pattern="\d+" id="id" placeholder="id" required value="<%=resultado.getId()%>" disabled>
							</div>
							<div class="col-md-6">
                                                               &nbsp;
							</div>
							<div class="col-md-6">
                                                            <label for="usuario">Usuario</label>
                                                            <input type="text" name="usuario" pattern="[a-zA-Z][a-zA-Z\d]+" id="usuario" placeholder="usuario" required value="<%=resultado.getUsuario()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            <label for="password">Password</label>
                                                            <input type="password" name="password"  id="password" placeholder="password" required minlength=6 maxlength=20 value="<%=resultado.getPassword()%>" disabled>
							</div>                                                        
                                                        
							<div class="col-md-6">
                                                            <label for="nombre">Nombre</label>
                                                            <input type="text" name="nombre" pattern="[a-zA-Z \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+" id="nombre" placeholder="nombre" required minlength=1 maxlength=20 value="<%=resultado.getNombre()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            <label for="paterno">Paterno</label>
                                                            <input type="text" name="paterno" pattern="[a-zA-Z \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+" id="paterno" placeholder="ap. paterno" required minlength=1 maxlength=20 value="<%=resultado.getPaterno()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            <label for="materno">Materno</label>
                                                            <input type="text" name="materno" pattern="[a-zA-Z \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+" id="materno" placeholder="ap. materno" minlength=1 maxlength=20 value="<%=resultado.getMaterno()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            <br>
                                                            <p>
                                                            <div class="input-group mb-3">
                                                              <div class="input-group-prepend">
                                                                <label class="input-group-text" for="perfil">Perfil</label>
                                                              </div>
                                                              <select class="custom-select" id="perfil" name="perfil" disabled>
                                                                <option value="admin" <%="admin".equals(resultado.getPerfil())?"selected":""%>>Administrador</option>
                                                                <option value="gerente" <%="gerente".equals(resultado.getPerfil())?"selected":""%>>Gerente</option>
                                                                <option value="cliente" <%="cliente".equals(resultado.getPerfil())?"selected":""%>>Cliente</option>
                                                              </select>
                                                            </div>
							</div>                                                        
							<div class="col-md-12">
                                                            &nbsp;
							</div>                                                        
							<div class="col-md-12">
                                                            <h3>Domicilio</h3>
							</div>
							<div class="col-md-6">
                                                            <label for="calle">Calle</label>
                                                            <input type="text" name="calle"  pattern="[a-zA-Z0-9 \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+"  id="calle" placeholder="calle" required minlength=1 maxlength=36 value="<%=resultado.getCalle()%>" disabled>
							</div>
							<div class="col-md-3">
                                                            <label for="exterior">Exterior</label>
                                                            <input type="text" name="exterior" pattern="sn|SN|[ \d-]*" id="exterior" placeholder="no. exterior" required minlength=1 maxlength=6 value="<%=resultado.getExterior()%>" disabled>
							</div>
							<div class="col-md-3">
                                                            <label for="interior">Interior</label>
                                                            <input type="text" name="interior" pattern="sn|SN|[ \d-]*" id="interior" placeholder="no. interior" maxlength=6 value="<%=resultado.getInterior()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            <label for="colonia">Colonia</label>
                                                            <input type="text" name="colonia"  pattern="[a-zA-Z0-9 \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+"   id="colonia" placeholder="colonia" required maxlength=36 value="<%=resultado.getColonia()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            <label for="municipio">Municipio</label>
                                                            <input type="text" name="municipio"  pattern="[a-zA-Z0-9 \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+"  id="municipio" placeholder="municipio" required maxlength=20 value="<%=resultado.getMunicipio()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            <label for="telefono">Telefono</label>
                                                            <input type="text" name="telefono"  pattern="\d{2}[-| ]\d{4}[-| ]\d{4}"  id="telefono" placeholder="telefono (MEX:12-3456-7890)" required maxlength=20 value="<%=resultado.getTelefono()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            <label for="correo">Correo</label>
                                                            <input type="email" name="correo"  id="correo" placeholder="correo" required maxlength=32 value="<%=resultado.getCorreo()%>" disabled>
							</div>
							<div class="col-md-6">
                                                            <div class="input-group mb-3">
                                                              <div class="input-group-prepend">
                                                                <label class="input-group-text" for="sucursal">Sucursal</label>
                                                              </div>
                                                              <select class="custom-select" id="sucursal" name="sucursal" disabled>
                                                                <option value="polanco" <%="polanco".equals(resultado.getSucursal())?"selected":""%>>Polanco</option>
                                                                <option value="santafe" <%="santafe".equals(resultado.getSucursal())?"selected":""%>>Santa Fe</option>
                                                                <option value="lindavista" <%="lindavista".equals(resultado.getSucursal())?"selected":""%>>Lindavista</option>
                                                                <option value="interlomas" <%="interlomas".equals(resultado.getSucursal())?"selected":""%>>Interlomas</option>
                                                              </select>
                                                            </div>
							</div>
							<div class="col-md-6">
                                                            &nbsp;
							</div>
							<div class="col-md-12" >
                                                            &nbsp;
                                                        </div>
							<div class="col-md-12" >
                                                            <div class="custom-control custom-switch">
                                                              <input type="checkbox" class="custom-control-input" id="switchModificar" onclick="validarModificar(this);">
                                                              <label class="custom-control-label" for="switchModificar">Modificar</label>
                                                            </div>                                                            
                                                        </div>
							<div class="col-md-12" >
                                                            &nbsp;
                                                        </div>
							<div class="col-md-3">
                                                            <button class="btn btn-info btn-block my-4"  id="botonguardar" name="botonguardar" type="submit" disabled >Guardar Cambios</button>                                                            
							</div>
						</div>
					</form>
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
            
            function validarModificar(switchModificar){
                var b = !switchModificar.checked;
                document.consultarPerfil.password.disabled=b;
                document.consultarPerfil.nombre.disabled=b;
                document.consultarPerfil.paterno.disabled=b;
                document.consultarPerfil.materno.disabled=b;
                document.consultarPerfil.calle.disabled=b;
                document.consultarPerfil.exterior.disabled=b;
                document.consultarPerfil.interior.disabled=b;
                document.consultarPerfil.colonia.disabled=b;
                document.consultarPerfil.municipio.disabled=b;
                document.consultarPerfil.telefono.disabled=b;
                document.consultarPerfil.correo.disabled=b;
                document.consultarPerfil.sucursal.disabled=b;
                document.consultarPerfil.botonguardar.disabled=b;
                document.consultarPerfil.botonprueba.disabled=b;
            }            
            
            function verificarEnvio(){
                var elements = document.consultarUsuario.elements;
                for (var i = 0, element; element = elements[i++];)
                    element.disabled=false;
                return true;
            }
            
        </script>
        
	</body>
</html>