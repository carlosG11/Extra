<%@page contentType="text/html" language="java" import="java.util.*,aux2.vo.VOUsuario,aux2.vo.VOProducto" session="true" pageEncoding="UTF-8"%>
<%!    
    private String verificarEnCarrito(int id, String sCarrito){
        String[] sProductos = sCarrito.split(",");
        for (int i=0;i<sProductos.length;i++){
            if (sProductos[i].equals(""+id))
                return "selected";
        }
        return "";
    }
%><%
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
                                            <h1>Consultar Carrito</h1>
					</div>
					<form class="comment-form" name="registrarServicios" method="post" action="SVTClienteCitaDesplegar" onsubmit="return verificarEnvio();">
						<div class="row">
							<div class="col-md-12">
                                                            <div class="input-group mb-12">
                                                                <label for="producto">Productos Seleccionados<br><p></label>
                                                                <select class="browser-default custom-select " id="producto" name="producto" multiple required>
                                                                <%
                                                                    for(int i=0;i<resultados.size();i++){
                                                                        voProductoItem = (VOProducto)resultados.get(i);                                                                        
                                                                %>
                                                                <option value="<%=voProductoItem.getId()%>" onclick="refrescarSaldos()" <%=verificarEnCarrito(voProductoItem.getId(), voUsuario.getCarrito())%>><%=voProductoItem.getNombre()%>, duracion:<%=voProductoItem.getDuracion()%> min., precio:$<%=voProductoItem.getPrecio()%></option>
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
                                                            <button class="btn btn-info btn-block my-4"  id="botoncontinuar" name="botoncontinuar" type="submit" >Desea Registrar una Cita?</button>
							</div>
                                                        <div class="col-md-3" >
                                                            &nbsp;
                                                        </div> 
							<div class="col-md-3">
                                                            <button class="btn btn-info btn-block my-4"  id="botonvaciar" name="botonvaciar" onclick="vaciarCarrito();">Vaciar el Carrito</button>
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
            
            refrescarSaldos()
                        
            function verificarEnvio(){
                var elements = document.registrarServicios.elements;
                for (var i = 0, element; element = elements[i++];)
                    element.disabled=false;
//                alert("duracion["+document.registrarServicios.duracion.value+"]");
//                alert("precio["+document.registrarServicios.precio.value+"]");
                if (bVaciarCarrito){
                    document.registrarServicios.action="SVTClienteCarritoVaciar";
                    return true;
                }
                return true;
            }
            
            var bVaciarCarrito=false;
            
            function vaciarCarrito(){
                bVaciarCarrito=true;                
            }
            
        </script>
        
	<!-- Footer section end -->        
        
	</body>
</html>