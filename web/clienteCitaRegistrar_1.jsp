<%@page contentType="text/html" language="java" import="java.util.*,aux2.vo.VOUsuario" session="true" pageEncoding="UTF-8"%>
<%
    String sMensaje= (String)request.getAttribute("mensaje");
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    ArrayList <VOUsuario>estilistasPolanco;
    ArrayList <VOUsuario>estilistasLindavista;
    ArrayList <VOUsuario>estilistasSantafe;
    ArrayList <VOUsuario>estilistasInterlomas;
    estilistasPolanco = (ArrayList)request.getAttribute("estilistasPolanco");    
    estilistasLindavista = (ArrayList)request.getAttribute("estilistasLindavista");    
    estilistasSantafe = (ArrayList)request.getAttribute("estilistasSantafe");    
    estilistasInterlomas = (ArrayList)request.getAttribute("estilistasInterlomas");    
    
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
	<section class="page-warp contact-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
                                            <span><%= sMensaje %></span>
                                            <h1>Registrar Cita</h1>
					</div>
					<form class="comment-form" method="post" action="SVTAdminUsuarioRegistrar" >
						<div class="row">
							<div class="col-md-6">
                                                            <div class="input-group mb-3">
                                                              <div class="input-group-prepend">
                                                                <label class="input-group-text" for="sucursal">Sucursal</label>
                                                              </div>
                                                              <select class="form-control sucursal selectFilter" id="sucursal" name="sucursal" data-target="estilista">
                                                                <option value="-1">-- Seleccione Sucursal --</option>
                                                                <option data-ref="polanco">Polanco</option>
                                                                <option data-ref="santafe">Santa Fe</option>
                                                                <option data-ref="lindavista">Lindavista</option>
                                                                <option data-ref="interlomas">Interlomas</option>
                                                              </select>
                                                            </div>
							</div>
							<div class="col-md-6">
                                                            <div class="input-group mb-3">
                                                              <div class="input-group-prepend">
                                                                <label class="input-group-text" for="estilista">Estilista</label>
                                                              </div>
                                                              <select class="form-control estilista selectFilter" id="estilista" name="estilista" >
                                                                <option value="-1">-- Seleccione Estilista --</option>
                                                                <%
                                                                    for(int i=0;i<estilistas.size();i++){
                                                                        voUsuarioEstilista = (VOUsuario)estilistas.get(i);
                                                                %>
                                                                    <option data-ref="<%=voUsuarioEstilista.getId()%>" data-belong="<%=voUsuarioEstilista.getSucursal()%>"><%=voUsuarioEstilista.getUsuario()%> - <%=voUsuarioEstilista.getNombre()%> <%=voUsuarioEstilista.getPaterno()%> <%=voUsuarioEstilista.getMaterno()%></option>
                                                                <%                                                                        
                                                                    }                                                                
                                                                %>
                                                              </select>
                                                            </div>
							</div>
							<div class="col-md-12" >
                                                            &nbsp;
                                                        </div>
							<div class="col-md-3">
                                                            <button class="btn btn-info btn-block my-4"  id="botonguardar" name="botonguardar" type="submit" >Registrar</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- Contact page end-->	
        

	<!-- Footer section -->
        <jsp:include page="footer.jsp" /> 
	<!-- Footer section end -->        
        
	</body>
</html>