<%@page contentType="text/html" language="java" import="java.util.*,java.text.SimpleDateFormat,aux2.vo.VOUsuario,aux2.vo.VOCita,aux2.vo.VOProducto,aux2.vo.VOPago" session="true" pageEncoding="UTF-8"%>
<%!
    
    private String seleccionarProducto(VOProducto voProducto,String[] sProductos){
        for (int i=0;i<sProductos.length;i++){
            if (sProductos[i].equals(""+voProducto.getId()))
                return "selected";
        }
        return "";
    }
%><%
    String sMensaje= (String)request.getAttribute("mensaje");
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    VOProducto voProductoItem;
    VOCita voCita;
    VOPago voPago;
    voCita = (VOCita)request.getAttribute("cita");
    ArrayList <VOProducto>resultados;
    ArrayList <VOPago>pagos;
    resultados = (ArrayList)request.getAttribute("productos");    
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    pagos = voCita.getPagos();
    
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

	<!-- Forma -->
	<!-- Back to top -->
        <form class="comment-form" name="cancelarCita" method="post" action="SVTClienteCitaCancelar" onsubmit="return verificarCancelacion();">
	<div class="container">
            <div class="row">
                <div class="section-title">
                    <span><%= sMensaje %></span>
                    <h3>Consultar Cita<%="cancelada".equals(voCita.getEstatus())?" (Cancelada)":""%></h3>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <div class="input-group mb-3">
                        <input type="hidden" id="id" name="id" value="<%=voCita.getId()%>" />                        
                        <label for="sucursal">Sucursal<br><p></label>
                        <select class="browser-default custom-select " id="sucursal" name="sucursal" required disabled>
                            <option value="polanco" <%="polanco".equals(voCita.getSucursal())?"selected":""%>>Sucursal Polanco</option>
                            <option value="lindavista" <%="lindavista".equals(voCita.getSucursal())?"selected":""%>>Sucursal Llindavista</option>
                            <option value="santafe" <%="santafe".equals(voCita.getSucursal())?"selected":""%>>Sucursal Santa F&eacute;</option>
                            <option value="interlomas" <%="interlomas".equals(voCita.getSucursal())?"selected":""%>>Sucursal Interlomas</option>
                        </select>                                                                    
                    </div>
                </div>
                <div class="col-md-9" >
                    &nbsp;
                </div>
            </div>                    
            <div class="row">
                <div class="col-md-3" >
                    <div class="input-group mb-3">
                        <label for="fecha">Fecha</label>
                        <input type="text" id="fecha" name="fecha" value="<%=formatter.format(voCita.getTiempo())%>" required disabled/>
                    </div>
                </div>
                <div class="col-md-9" >
                    &nbsp;
                </div>
            </div>   
                    
            <div class="row">
                <div class="col-md-6" >
                    <div class="input-group mb-6">
                        <label for="estilista">Estilista<br><p></label>
                        <select class="browser-default custom-select " id="estilista" name="estilista" required disabled>
                            <option value="<%=voCita.getEstilista()%>" selected><%=voCita.getEstilistaNombre()%> <%=voCita.getEstilistaPaterno()%> <%=voCita.getEstilistaMaterno()%> </option>
                        </select>                                                                    
                    </div>
                </div>
                <div class="col-md-6" >
                    &nbsp;
                </div>
            </div>                    
            <div class="row">
                <div class="col-md-12" >
                    <div class="input-group mb-12">
                        <label for="producto">Productos<br><p></label>
                        <select class="browser-default custom-select " id="producto" name="producto" multiple required disabled>
                        <%
                            for(int i=0;i<resultados.size();i++){
                                voProductoItem = (VOProducto)resultados.get(i);
                                if ("selected".equals(seleccionarProducto(voProductoItem,voCita.getsProductos()))){
                        %>
                            <option value="<%=voProductoItem.getId()%>" selected><%=voProductoItem.getNombre()%>, dur. <%=voProductoItem.getDuracion()%> min., precio: $<%=voProductoItem.getPrecio()%></option>
                        <%                                                                                                                    
                                }
                            }                                                                                                                                         
                        %>
                        </select>
                    </div>
                </div>
            </div>

                       
            <div class="row">
                <div class="col-md-3">
                    <div class="form-group row">
                      <label for="duracion" >Duraci&oacute;n<br><p></label>
                        <input class="form-control" type="text" value="<%=voCita.getDuracion()%>" id="duracion" name="duracion" disabled>
                    </div>                                                                                                                        
                </div>
                <div class="col-md-3">
                    <div class="form-group row">
                      <label for="duracion" >Precio Total<br><p></label>
                        <input class="form-control" type="text" value="<%=voCita.getPrecio()%>" id="precio" name="precio" disabled>
                    </div>                                                                                                                        
                </div>
                <div class="col-md-6">
                    &nbsp;
                </div>
            </div>
            <div class="row">
                <div class="container">
                  <h2>Pagos Realizados</h2>
                  <table class="table">
                    <thead>
                      <tr>
                        <th>Fecha</th>
                        <th>TDC</th>
                        <th>Importe</th>
                      </tr>
                    </thead>
                    <tbody>
                    <%
                        for (int i=0;i<pagos.size();i++){
                            voPago = (VOPago)pagos.get(i);
                    %>
                      <tr>
                        <td><%=voPago.getTiempo()%></td>
                        <td><%=voPago.getTdc()%></td>
                        <td><%=voPago.getImporte()%></td>
                      </tr>
                    <%
                        }                    
                    %>
                    </tbody>
                  </table>
                </div>
            </div>

            <%
            if ("registrada".equals(voCita.getEstatus())){                
            %>
            <div class="row">
                <div class="col-md-3">
                    <button class="btn btn-info btn-block my-6"  id="botoncancelar" name="botoncancelar" type="submit" >Cancelar Cita</button>
                </div>
                <div class="col-md-9">
                    &nbsp;
                </div>
            </div>            
            <%
            }
            %>
                        
	</div>
        </form>
	<!-- Contact page end-->	
        

	<!-- Footer section -->
        <jsp:include page="footer.jsp" /> 
        <script src="js/gijgo.min.js" type="text/javascript"></script>                
	<!-- Footer section end -->        

        <script language="javascript">
                        
            function verificarCancelacion(){
                return confirm("Desea Cancelar su Cita?");
            }
            
        </script>
        
        
	</body>
</html>