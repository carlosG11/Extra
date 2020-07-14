<%@page import="aux2.app.Constantes"%>
<%@page contentType="text/html" language="java" import="java.util.*,java.text.SimpleDateFormat,aux2.vo.VOUsuario,aux2.vo.VOCita,aux2.vo.VOProducto" session="true" pageEncoding="UTF-8"%>
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
    VOCita item;
    ArrayList <VOCita>resultados;
    resultados = (ArrayList)request.getAttribute("resultados");
    SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd 'de' MMM 'de' yyyy, HH:mm");
    if (null==resultados)
        resultados = new <VOCita>ArrayList();    
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
                            <h1>Buscar Citas</h1>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-content">
                                <h2>
                                    <%=resultados.size()%> Resultados de: <span class="text-navy">"Cita"</span>
                                </h2>
                                <small>Request time  (0.21 seconds)</small>

                                <div class="search-form">
                                    <form name="formulario" method="post" action="SVTGerenteCitaBuscar" >
                                        <div class="input-group">
                                            <input type="text" placeholder="nombre" name="nombre" class="form-control input-lg">
                                            <div class="input-group-append">
                                                <button class="btn btn-secondary"><i class="fa fa-search"></i></button>
                                            </div>
                                        </div>
                                    </form>

                                    <form name="formularioConsultar" id="formularioConsultar" method="post" action="SVTGerenteCitaConsultar" >
                                        <input type="hidden" name="id" value="">
                                    </form>                        
                                </div>

                                <%
                                    for (int i=0; i<resultados.size();i++){
                                        item = (VOCita)resultados.get(i);

                                %>
                                    <div class="hr-line-dashed"></div>
                                    <div class="search-result">                                                                    
                                        <h3><a href="#" onclick="consultarCita(<%=item.getId()%>);" ><%="cancelada".equals(item.getEstatus())?"(Cancelada) ":""%><%=obtenerSucursal(item)%> - <%=formatter.format(item.getTiempo())%></a></h3>
                                        <a href="#" onclick="consultarCita(<%=item.getId()%>);" class="search-link">Duraci&oacute;n: <%=item.getDuracion()%> min.</a>
                                        <p>
                                            Estilista: <%=item.getEstilistaNombre()%> <%=item.getEstilistaPaterno()%> <%=item.getEstilistaMaterno()%>
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
        <jsp:include page="footer.jsp" /> 
        <script src="js/gijgo.min.js" type="text/javascript"></script>        
        <script language="javascript">
            function consultarCita(id){
                document.formularioConsultar.id.value=id;
                document.formularioConsultar.submit();
            }
        </script>
        
	<!-- Footer section end -->        
        
	</body>
</html>