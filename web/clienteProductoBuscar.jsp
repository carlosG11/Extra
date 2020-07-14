<%@page import="aux2.app.Constantes"%>
<%@page contentType="text/html" language="java" import="java.util.*,java.text.SimpleDateFormat,aux2.vo.VOUsuario,aux2.vo.VOCita,aux2.vo.VOProducto" session="true" pageEncoding="UTF-8"%>
<%
    String sMensaje= (String)request.getAttribute("mensaje");    
    session = request.getSession();
    VOUsuario voUsuario = (VOUsuario)session.getAttribute("usuario");
    VOProducto item;
    ArrayList <VOProducto>resultados;
    resultados = (ArrayList)request.getAttribute("productos");    
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

	<!-- Contact page end-->
        <section class="blog-section spad">
            <div class="container">
                <div class="section-title">
                        <span><%= sMensaje %></span>

                </div>
                <div class="search-form">
                    <form name="formularioConsultar" id="formularioConsultar" method="post" action="SVTClienteProductoConsultar" >
                            <input type="hidden" name="id" value="">
                    </form>                        
                </div>
                <div class="blog-slider owl-carousel"> 
                    <%
                        for(int i=0;i<resultados.size();i++){
                            item = (VOProducto)resultados.get(i);                                               
                    %>
                        <div class="blog-item">
                                <div class="blog-thumb set-bg" data-setbg="img/productos/<%=item.getId()%>.jpg">
                                        <div class="blog-date">
                                                <h2><%=item.getDuracion()%> </h2>
                                                <p>Min</p>
                                        </div>
                                </div>
                                <div class="blog-head">
                                        <div class="blog-tags">$<%=item.getPrecio()%></div>
                                        <h2><a href="#" onclick="consultarProducto(<%=item.getId()%>);" ><%=item.getNombre()%></a></h2>
                                </div>
                        </div>
                        <%                                                                    
                            }                                                                                                                                         
                        %>
                </div>
            </div>                        
	</section>

        

	<!-- Footer section -->
        <jsp:include page="footer.jsp" /> 
        <script src="js/gijgo.min.js" type="text/javascript"></script>        
        <script language="javascript">
            function consultarProducto(id){
                document.formularioConsultar.id.value=id;
                document.formularioConsultar.submit();
            }
        </script>
        
	<!-- Footer section end -->        
        
	</body>
</html>