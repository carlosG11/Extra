<%@page contentType="text/html" language="java" import="java.util.*,aux2.vo.VOUsuario" session="true" pageEncoding="UTF-8"%>
<%
    String sMensaje= (String)request.getAttribute("mensaje");
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    VOUsuario voUsuarioEstilista;
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
        <link rel="stylesheet" href="css/gijgo.min.css" type="text/css" />


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
					<form class="comment-form" method="post" action="SVTClienteCitaConsultarFecha" >
						<div class="row">
							<div class="col-md-12">
                                                            <div class="input-group mb-12">
                                                              <div class="input-group-prepend">
                                                                <label class="input-group-text" for="estilista">Seleccionar Estilista</label>
                                                              </div>
                                                                <select class="browser-default custom-select " id="estilista" name="estilista" required>
                                                                    <optgroup label="Sucursal Polanco">
                                                                    <%
                                                                        for(int i=0;i<estilistasPolanco.size();i++){
                                                                            voUsuarioEstilista = estilistasPolanco.get(i);
                                                                    %>
                                                                        <option value="<%=voUsuarioEstilista.getId()%>"><%=voUsuarioEstilista.getNombre()%> <%=voUsuarioEstilista.getPaterno()%> <%=voUsuarioEstilista.getMaterno()%></option>
                                                                    <%                                                                            
                                                                        }
                                                                    %>                                                                        
                                                                    </optgroup>
                                                                    <optgroup label="Sucursal Lindavista">
                                                                    <%
                                                                        for(int i=0;i<estilistasLindavista.size();i++){
                                                                            voUsuarioEstilista = estilistasLindavista.get(i);
                                                                    %>
                                                                        <option value="<%=voUsuarioEstilista.getId()%>"><%=voUsuarioEstilista.getNombre()%> <%=voUsuarioEstilista.getPaterno()%> <%=voUsuarioEstilista.getMaterno()%></option>
                                                                    <%                                                                            
                                                                        }
                                                                    %>                                                                        
                                                                    </optgroup>
                                                                    <optgroup label="Sucursal Santa Fe">
                                                                    <%
                                                                        for(int i=0;i<estilistasSantafe.size();i++){
                                                                            voUsuarioEstilista = estilistasSantafe.get(i);
                                                                    %>
                                                                        <option value="<%=voUsuarioEstilista.getId()%>"><%=voUsuarioEstilista.getNombre()%> <%=voUsuarioEstilista.getPaterno()%> <%=voUsuarioEstilista.getMaterno()%></option>
                                                                    <%                                                                            
                                                                        }
                                                                    %>                                                                        
                                                                    </optgroup>
                                                                    <optgroup label="Sucursal Interlomas">
                                                                    <%
                                                                        for(int i=0;i<estilistasInterlomas.size();i++){
                                                                            voUsuarioEstilista = estilistasInterlomas.get(i);
                                                                    %>
                                                                        <option value="<%=voUsuarioEstilista.getId()%>"><%=voUsuarioEstilista.getNombre()%> <%=voUsuarioEstilista.getPaterno()%> <%=voUsuarioEstilista.getMaterno()%></option>
                                                                    <%                                                                            
                                                                        }
                                                                    %>                                                                        
                                                                    </optgroup>                                                                    
                                                                </select>                                                                    
                                                            </div>
							</div>                                                    
							<div class="col-md-12" >
                                                            &nbsp;
                                                        </div>
							<div class="col-md-6" >
                                                            <div class="input-group mb-6">
                                                              <div class="input-group-prepend">
                                                                <label for="fecha">Seleccionar Fecha</label>
                                                              </div>
                                                              <input id="fecha" name="fecha" required/>
                                                            </div>
                                                        </div> 
                                                        <div class="col-md-12" >                                                            
                                                            &nbsp;
                                                        </div> 
							<div class="col-md-3">
                                                            <button class="btn btn-info btn-block my-4"  id="botoncontinuar" name="botoncontinuar" type="submit" >Consultar Fecha</button>
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
        <script src="js/gijgo.min.js" type="text/javascript"></script>        
        <script>
            $('#fecha').datepicker({
                uiLibrary: 'bootstrap4'
            });
        </script>
        
	<!-- Footer section end -->        
        
	</body>
</html>