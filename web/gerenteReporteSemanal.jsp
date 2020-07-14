<%@page import="aux2.app.Constantes"%>
<%@page contentType="text/html" language="java" import="java.util.*,java.text.SimpleDateFormat,aux2.vo.VOUsuario,aux2.vo.VOCita,aux2.vo.VOProducto,aux2.vo.VOReporteSemanal" session="true" pageEncoding="UTF-8"%>
<%!
    
    private String obtenerSucursal(VOCita voCita){
        if (voCita.getSucursal().equals(Constantes.SUCURSALES[Constantes.SUCURSAL_POLANCO]))
            return "Sucursal Polanco";
        if (voCita.getSucursal().equals(Constantes.SUCURSALES[Constantes.SUCURSAL_LINDAVISTA]))
            return "Sucursal Lindavista";
        if (voCita.getSucursal().equals(Constantes.SUCURSALES[Constantes.SUCURSAL_SANTAFE]))
            return "Sucursal Santa Fe&acute;";
        if (voCita.getSucursal().equals(Constantes.SUCURSALES[Constantes.SUCURSAL_INTERLOMAS]))
            return "Sucursal Interlomas";
        return "Sucursal Desconocida";
    }
%><%
    String sMensaje= (String)request.getAttribute("mensaje");    
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    VOReporteSemanal item;
    ArrayList <VOReporteSemanal>resultados;
    resultados = (ArrayList)request.getAttribute("resultados");
    SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd 'de' MMM 'de' yyyy");
    VOCita voCita=(VOCita)request.getAttribute("cita");
    if (null==resultados)
        resultados = new <VOReporteSemanal>ArrayList();    
    if (null==sMensaje)
        sMensaje ="";    
    
    //valida que el usuario se haya identificado
    if(null==voUsuario){        
        %><jsp:forward page="login.jsp">
        <jsp:param name="mensaje" value="Es obligatorio identificarse."/>
        </jsp:forward><%           
    }
    //valida que el usuario sea administrador
    if(!"gerente".equals(voUsuario.getPerfil())){
        %><jsp:forward page="login.jsp">
        <jsp:param name="mensaje" value="Se requiere acceso de Gerente."/>
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
        <jsp:include page="gerenteMenu.jsp" /> 
	<!-- Header section end-->

	<!-- Contact page -->
	<section class="page-warp contact-page">
            <div class="container bootstrap snippet">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <span><%= sMensaje %></span>
                            <h1>Reporte De Servicios</h1>
                        </div>
                    </div>
                </div>
                <form class="comment-form" name="generarReporte" method="post" action="SVTGerenteGenerarReporteSemanal" onsubmit="return validarReporte();">
                <div class="row">
                    <div class="col-md-3" >
                        <div class="input-group mb-3">
                            <label for="fecha">Seleccionar Fecha Inicio</label>
                            <input id="fecha" name="fecha" required/>
                        </div>
                    </div>
                    <div class="col-md-3" >
                        &nbsp;
                    </div>
                    <div class="col-md-3" >
                        <div class="input-group mb-3">
                            <label for="fechaFin">Seleccionar Fecha Fin</label>
                            <input id="fechaFin" name="fechaFin" required/>
                        </div>
                    </div>
                    <div class="col-md-12" >
                        &nbsp;
                    </div>
                    <div class="col-md-3">
                        <button class="btn btn-info btn-block my-4"  id="botonreporte" name="botonreporte" type="submit" >Generar Reporte</button>
                    </div>                    
                    <div class="col-md-9" >
                        &nbsp;
                    </div>
                </div>
                <%
                if (resultados.size()>0){
                %>
                <div class="row">
                    <div class="container">
                      <h2>Servicios Realizados</h2>
                      <h3>Sucursal <%=voUsuario.getSucursal()%></h3>
                      <h3><%="De " + formatter.format(voCita.getTiempo())+" a " + formatter.format(voCita.getTiempoFin())%></h3>
                      <table class="table">
                        <thead>
                          <tr>
                            <th>Id</th>
                            <th>Servicio</th>
                            <th>No. Ocurrencias</th>
                            <th>Importe</th>
                          </tr>
                        </thead>
                        <tbody>
                        <%
                            for(int i=0;i<resultados.size();i++){
                                item = (VOReporteSemanal) resultados.get(i);
                        %>
                          <tr>
                            <td><%=item.getId()%></td>
                            <td><%=item.getNombre()%></td>
                            <td><%=item.getFrecuencia()%></td>
                            <td><%=(item.getFrecuencia()*item.getImporte())%></td>
                          </tr>
                        <%
                            }                    
                        %>
                        </tbody>
                      </table>
                    </div>
                </div>                
                <%                    
                    }
                %>
                    
                </form>
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
            $('#fechaFin').datepicker({
                uiLibrary: 'bootstrap4'
            });
        </script>
        <script language="javascript">
            
            var bEstaSemana=false;
            var bEsteMes=false;
            
            function estaSemana(){
                bEstaSemana=true;                
            }
            
            function esteMes(){
                bEsteMes=true;                
            }
            
            function validarReporte(){
                var dFechaIni = new Date();
                var dFechaFin = new Date();
                
                if (bEstaSemana){
                    bEstaSemana=false;
                    
                }
                
                
//                alert("verificarEnvio.1 fecha["+document.registrarServicios.fecha.value+"]");
                dFechaIni.setTime(Date.parse(document.generarReporte.fecha.value));
                dFechaFin.setTime(Date.parse(document.generarReporte.fechaFin.value));
//                alert("verificarEnvio.dFecha["+dFecha+"], dFechaHoy["+dFechaHoy+"]");
                if (dFechaFin<dFechaIni){
                    alert("No se puede generar reporte para una fecha anterior.");
                    return false;
                }
                return true;                
            }
            
/*            function _calEstaSemana(fIni,fFin){
                    var d=new Date();
                    fIni.value=_fmtFecha(d);
                    d.setDate(d.getDate()+1);
                    fFin.value=_fmtFecha(d);
            }
            function _calEsteMes(fIni, fFin){
                    var d = new Date();
                    d.setDate(1);
                    fIni.value = _fmtFecha(d);
                    d.setMonth(d.getMonth()+1);
                    fFin.value = _fmtFecha(d);
            }//*/

    
    
            
        </script>
        
	<!-- Footer section end -->        
        
	</body>
</html>