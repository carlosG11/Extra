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
	<header class="header-section">
		<nav class="navbar navbar-expand-md navbar-dark bg-dark site-navbar">
			<a class="navbar-brand site-logo" href="index.html#">
				<h2><span>AUX</span>Style</h2>
				<p>Fashion Forward</p>
			</a>
			<button class="navbar-toggler d-lg-none" type="button" data-toggle="collapse" data-target="#collapsibleNavId" aria-controls="collapsibleNavId"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="collapsibleNavId">
                            <!-- Main menu -->
                            <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                                    <li class="nav-item">
                                            <a class="nav-link" href="login.jsp">Inicio</a>
                                    </li>
                            </ul>
			</div>
		</nav>
	</header>
	<!-- Header section end-->

	<!-- Contact page -->
	<section class="page-warp contact-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="section-title">
						<span> </span>
						<h1>Nuevo Usuario</h1>
					</div>
					<form class="comment-form" method="post" action="SVTUsuarioRegistrar" >
						<div class="row">
							<div class="col-md-6">
								<input name="usuario" pattern="[a-zA-Z][a-zA-Z\d]+" id="usuario" placeholder="usuario" required >
							</div>
							<div class="col-md-6">
								<input type="password" name="password"  id="password" placeholder="password" required minlength=6 maxlength=20 >
							</div>
							<div class="col-md-6">
								<input type="text" name="nombre" pattern="[a-zA-Z \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+" id="nombre" placeholder="nombre" required minlength=1 maxlength=20 >
							</div>
							<div class="col-md-6">
								<input type="text" name="paterno" pattern="[a-zA-Z \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+" id="paterno" placeholder="ap. paterno" required minlength=1 maxlength=20 >
							</div>
							<div class="col-md-6">
								<input type="text" name="materno" pattern="[a-zA-Z \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+" id="materno" placeholder="ap. materno" minlength=1 maxlength=20 >
							</div>
							<div class="col-md-6">
							</div>
							<div class="col-md-12">
                                                            <h3>Domicilio</h3>
							</div>
							<div class="col-md-6">
								<input type="text" name="calle"  pattern="[a-zA-Z0-9 \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+"  id="calle" placeholder="calle" required minlength=1 maxlength=36 >
							</div>
							<div class="col-md-3">
								<input type="text" name="exterior" pattern="sn|SN|[ \d-]*" id="exterior" placeholder="no. exterior" required minlength=1 maxlength=6 >
							</div>
							<div class="col-md-3">
								<input type="text" name="interior" pattern="sn|SN|[ \d-]*" id="interior" placeholder="no. interior" maxlength=6 >
							</div>
							<div class="col-md-6">
								<input type="text" name="colonia"  pattern="[a-zA-Z0-9 \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+"   id="colonia" placeholder="colonia" required maxlength=36 >
							</div>
							<div class="col-md-6">
								<input type="text" name="municipio"  pattern="[a-zA-Z0-9 \u00c1\u00e1\u00c9\u00e9\u00cd\u00ed\u00d3\u00f3\u00da\u00fa\u00d1\u00f1]+"  id="municipio" placeholder="municipio" required maxlength=20 >
							</div>
							<div class="col-md-6">
								<input type="text" name="telefono"  pattern="\d{2}[-| ]\d{4}[-| ]\d{4}"  id="telefono" placeholder="telefono (MEX:12-3456-7890)" required maxlength=20 >
							</div>
							<div class="col-md-6">
								<input type="email" name="correo"  id="correo" placeholder="correo" required maxlength=32 >
							</div>
							<div class="col-md-12">
								<button class="site-btn sb-dark">SEND <i class="fa fa-angle-double-right"></i></button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<!-- Contact page end-->		
	
	<!--====== Javascripts & Jquery ======-->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/main.js"></script>
	</body>
</html>