<%@page contentType="text/html" language="java" import="java.util.*,aux2.vo.VOUsuario,aux2.vo.VOProducto" session="true" pageEncoding="UTF-8"%>
<%
    String sMensaje= (String)request.getAttribute("mensaje");
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    VOProducto voProductoItem;
    ArrayList <VOProducto>resultados;
    resultados = (ArrayList)request.getAttribute("productos");    
    
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
					<form class="comment-form" name="registrarServicios" method="post" action="SVTClienteCitaConsultarFecha" onsubmit="return verificarEnvio();">
						<div class="row">
							<div class="col-md-3" >
                                                            <div class="input-group mb-3">
                                                                <label for="fecha">Seleccionar Fecha</label>
                                                                <input id="fecha" name="fecha" required/>
                                                            </div>
                                                        </div>
							<div class="col-md-9" >
                                                            &nbsp;
                                                        </div>
							<div class="col-md-12" >
                                                            &nbsp;
                                                        </div>
							<div class="col-md-3">
                                                            <div class="input-group mb-3">
                                                                <label for="sucursal">Seleccionar Sucursal<br><p></label>
                                                                <select class="browser-default custom-select " id="sucursal" name="sucursal" required>
                                                                    <option value="polanco" selected>Sucursal Polanco</option>
                                                                    <option value="lindavista">Sucursal Lindavista</option>
                                                                    <option value="santafe">Sucursal Santa F&eacute;</option>
                                                                    <option value="interlomas">Sucursal Interlomas</option>
                                                                </select>                                                                    
                                                            </div>
							</div>                                                    
							<div class="col-md-9" >
                                                            &nbsp;
                                                        </div>
							<div class="col-md-12" >
                                                            &nbsp;
                                                        </div>
							<div class="col-md-12">
                                                            <div class="input-group mb-12">
                                                                <label for="producto">Seleccionar Productos<br><p></label>
                                                                <select class="browser-default custom-select " id="producto" name="producto" multiple required>
                                                                <%
                                                                    for(int i=0;i<resultados.size();i++){
                                                                        voProductoItem = (VOProducto)resultados.get(i);                                                                        
                                                                %>
                                                                    <option value="<%=voProductoItem.getId()%>" onclick="refrescarSaldos()"><%=voProductoItem.getNombre()%>, duracion:<%=voProductoItem.getDuracion()%> min., precio:$<%=voProductoItem.getPrecio()%></option>
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
                                                            <div class="form-group row">
                                                              <label for="duracion" >Duraci&oacute;n<br></label>
                                                                <input class="form-control" type="text" value="0" id="duracion" name="duracion" disabled>
                                                            </div>                                                                                                                        
							</div>
							<div class="col-md-3">
                                                            &nbsp;
							</div>
							<div class="col-md-3">
                                                            <div class="form-group row">
                                                              <label for="precio" >Precio<br></label>
                                                                <input class="form-control" type="text" value="0" id="precio" name="precio" disabled>
                                                            </div>                                                                                                                        
							</div>
							<div class="col-md-3">
                                                            &nbsp;
							</div>
							<div class="col-md-12" >
                                                            &nbsp;
                                                        </div>
                                                        <div class="col-md-12" >                                                            
                                                            &nbsp;
                                                        </div> 
							<div class="col-md-3">
                                                            <button class="btn btn-info btn-block my-4"  id="botoncontinuar" name="botoncontinuar" type="submit" >Consultar Disponibilidad</button>
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
        <script language="javascript">
            
            document.registrarServicios.duracion.value="0";
            document.registrarServicios.precio.value="0";            
            
            //retorna la duracion del texto de una opcion
            function obtenerDuracion(s){
               var i = s.indexOf("duracion:");
               var j = s.indexOf("min., precio:$");
               return s.substr(i+9,j-i-9);
            }
            //retorna el precio del texto de una opcion
            function obtenerPrecio(s){
               var i = s.indexOf("precio:$");
               return s.substr(i+8);
            }            
            
            function refrescarSaldos(){
                document.registrarServicios.duracion.value="0";
                document.registrarServicios.precio.value="0";            
                for (var i=0;i<document.registrarServicios.producto.length;i++){
                    var opt = document.registrarServicios.producto[i];
                    if (opt.selected){
                        document.registrarServicios.duracion.value = 
                                parseInt(document.registrarServicios.duracion.value) + parseInt(obtenerDuracion(opt.text));
                        document.registrarServicios.precio.value = 
                                parseFloat(document.registrarServicios.precio.value) + parseFloat(obtenerPrecio(opt.text));
                    }
                }
            }            
                        
            function verificarEnvio(){
                var dFechaHoy = new Date();
                var dFecha = new Date();
//                alert("verificarEnvio.1 fecha["+document.registrarServicios.fecha.value+"]");
                dFecha.setTime(Date.parse(document.registrarServicios.fecha.value));
//                alert("verificarEnvio.dFecha["+dFecha+"], dFechaHoy["+dFechaHoy+"]");
                if (dFecha<dFechaHoy){
                    alert("No se puede asignar una cita para una fecha anterior.");
                    return false;
                }
//                alert("verificarEnvio.3");
                //si la cita cae en sabado o domingo, 
                if (dFecha.getDay()==0 || dFecha.getDay()==6){
                    alert("Solo se pueden asignar citas de lunes a viernes.");
                    return false;                    
                }
                var elements = document.registrarServicios.elements;
                for (var i = 0, element; element = elements[i++];)
                    element.disabled=false;
//                alert("duracion["+document.registrarServicios.duracion.value+"]");
//                alert("precio["+document.registrarServicios.precio.value+"]");
                return true;
            }
            
        </script>
        
	<!-- Footer section end -->        
        
	</body>
</html>