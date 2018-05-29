<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.footer {
	padding: 24px 0
}

.footer.footer-default {
	background-color: #f2f2f2
}

.footer nav {
	display: inline-block;
	float: left;
	padding-left: 7px
}

.footer ul {
	margin-bottom: 0;
	padding: 0;
	list-style: none
}

.footer ul li {
	display: inline-block
}

.footer ul li a {
	color: inherit;
	padding: .5rem;
	font-size: .8571em;
	text-transform: uppercase
}

.footer ul li a, .footer ul li a:hover {
	text-decoration: none
}

.footer .copyright {
	font-size: .8571em;
	line-height: 1.8
}

.footer:after {
	display: table;
	clear: both;
	content: " "
}

.main-panel {
	/*position:relative;
    float:right;
    width:calc(100% - 260px);*/
	background-color: #e3e3e3;
	background-color: #ebecf1;
	transition: all .5s cubic-bezier(.685, .0473, .346, 1)
}

.main-panel>.content {
	padding: 0 30px 30px;
	min-height: calc(100vh - 123px);
	margin-top: -30px
}

.main-panel>.navbar {
	margin-bottom: 0
}

.main-panel .header {
	margin-bottom: 50px
}

.perfect-scrollbar-on .main-panel, .perfect-scrollbar-on .sidebar {
	height: 100%;
	max-height: 100%
}

.panel-header {
	/*height:260px;*/
	padding-top: 80px;
	padding-bottom: 45px;
	background: #141e30;
	background: linear-gradient(90deg, #0c2646 0, #204065 60%, #2a5788);
	position: relative;
	overflow: hidden
}

.panel-header .header .title {
	color: #fff
}

.panel-header .header .category {
	max-width: 600px;
	color: hsla(0, 0%, 100%, .5);
	margin: 0 auto;
	font-size: 13px
}

.panel-header .header .category a {
	color: #fff
}

.panel-header-sm {
	height: 135px
}

.panel-header-lg {
	/*height:380px*/
	
}
</style>
</head>
<body>
	<div class="container">
		<div class="main-panel ps-container ps-theme-default ps-active-y"
			data-ps-id="ce312bd9-8126-270e-3953-da645e4c0f08">
			<!-- Navbar -->
			<nav
				class="navbar navbar-expand-lg navbar-transparent  navbar-absolute bg-primary fixed-top">
			<div class="container-fluid">
				<div class="navbar-wrapper">
					<div class="navbar-toggle">
						<button type="button" class="navbar-toggler">
							<span class="navbar-toggler-bar bar1"></span> <span
								class="navbar-toggler-bar bar2"></span> <span
								class="navbar-toggler-bar bar3"></span>
						</button>
					</div>
					<a class="navbar-brand" href="#">Dashboard</a>
				</div>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navigation" aria-controls="navigation-index"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-bar navbar-kebab"></span> <span
						class="navbar-toggler-bar navbar-kebab"></span> <span
						class="navbar-toggler-bar navbar-kebab"></span>
				</button>
				<div class="collapse navbar-collapse justify-content-end"
					id="navigation">

					<ul class="navbar-nav">
						<!--li class="nav-item">
                           <a class="nav-link" href="#pablo">
                              <i class="now-ui-icons media-2_sound-wave"></i>
                              <p>
                                 <span class="d-lg-none d-md-block">Stats</span>
                              </p>
                           </a>
                        </li-->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <i
								class="now-ui-icons users_single-02"></i>
								<p>
									<span class="d-lg-none d-md-block">Cuenta</span>
								</p>
						</a>
							<div class="dropdown-menu dropdown-menu-right"
								aria-labelledby="navbarDropdownMenuLink">
								<!--a class="dropdown-item" href="#">Action</a-->
								<a class="dropdown-item" href="#">Perfil</a> <a
									class="dropdown-item" href="#">Salir</a>
							</div></li>
						<!--li class="nav-item">
                           <a class="nav-link" href="#pablo">
                              <i class="now-ui-icons users_single-02"></i>
                              <p>
                                 <span class="d-lg-none d-md-block">Account</span>
                              </p>
                           </a>
                        </li-->
					</ul>
				</div>
			</div>
			</nav>
			<!-- End Navbar -->
			<div class="panel-header panel-header-lg">
				<div class="chartjs-size-monitor"
					style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
					<div class="chartjs-size-monitor-expand"
						style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
						<div
							style="position: absolute; width: 1000000px; height: 1000000px; left: 0; top: 0"></div>
					</div>
					<div class="chartjs-size-monitor-shrink"
						style="position: absolute; left: 0; top: 0; right: 0; bottom: 0; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
						<div
							style="position: absolute; width: 200%; height: 200%; left: 0; top: 0"></div>
					</div>
				</div>

			</div>
			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="card card-stats card-raised">
							<div class="card-body">
								<div class="card ">
									<div class="card-header ">
										<h4 class="card-title">Login</h4>
									</div>
									<div class="card-body ">
										<form action="seguridad-login.action" method="post">
											<label>User</label>
											<div class="form-group">
												<input type="text" class="form-control" name="username"
													placeholder="Username">
											</div>

											<label>Password</label>
											<div class="form-group">
												<input type="password" class="form-control" name="password"
													placeholder="Password">
											</div>
											<button type="submit" class="btn btn-primary">Sign
												in</button>
										</form>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<footer class="footer">
			<div class="container-fluid">
				<nav>
				<ul>
					<li><a href="#"> Template Piloto </a></li>
					<li><a href="#"> Acerca de... </a></li>
					<li><a href="#"> Blog </a></li>
				</ul>
				</nav>
				<div class="copyright">
					© Diseñado por <a href="#" target="_blank">OSR</a> . Codigo por <a
						href="#" target="_blank">OSR DEV</a>.
				</div>
			</div>
			</footer>
			<!--div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 0px;">
				<div class="ps-scrollbar-x" tabindex="0"
					style="left: 0px; width: 0px;"></div>
			</div>
			<div class="ps-scrollbar-y-rail"
				style="top: 0px; right: 0px; height: 597px;">
				<div class="ps-scrollbar-y" tabindex="0"
					style="top: 0px; height: 174px;"></div>
			</div-->
		</div>
	</div>
</body>
</html>