    <%@ include file="/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title></title>

    <script src="resources/jquery/js/jquery.min.js"></script>
    <link href="resources/bootstrap/css/bootstrap_t.css" rel="stylesheet">
     <link href="resources/bootstrap/css/docs.min.css" rel="stylesheet">
     <link href="resources/bootstrap/css/datepicker.min.css" rel="stylesheet">
     <link href="resources/bootstrap/css/datepicker3.min.css" rel="stylesheet">
     <script src="resources/bootstrap/js/bootstraps.min.js"></script>
     <script src="resources/bootstrap/js/docs.min.js"></script>
     <script src="resources/bootstrap/js/bootstrap-datepicker.min.js"></script>
    <style type="text/css">
    	#main-body{
	    	padding: 5px;
    	}
    	#main-menu{
	        height:10%;
		}
    	#main-right{
	    	height:80%;
	        float:right;
	        width:80%;
    	}
    	#main-left{
	    	height:20%;
	        float:left;
	        width:20%;
    	}
    </style>
    <script type="text/javascript">
		$(document).ready(function(){
			$("#main-right").load("inicio!input.action");
		});
		volver = function(){
			$('#myPleaseWait').modal('show');
			$("#main-right").load("inicio!input.action");
			$('#myPleaseWait').modal('hide');
		};
		emisoras = function(){
			$('#myPleaseWait').modal('show');
			$("#main-right").load("emisoras!input.action");
			$('#myPleaseWait').modal('hide');
		};
		
		retirosefectivo = function(){
// 		$('#myPleaseWait').modal('show');
		$("#main-right").load("retiros-efectivo!input.action");
// 		$('#myPleaseWait').modal('hide');
		};
		
		pagosservicios = function(){
// 		$('#myPleaseWait').modal('show');
		$("#main-right").load("pagos-servicios.action");
// 		$('#myPleaseWait').modal('hide');
		};
		
		pagostdc = function(){
// 		$('#myPleaseWait').modal('show');
		$("#main-right").load("pagos-tdc.action");
// 		$('#myPleaseWait').modal('hide');
		};
		
		agrupaciones = function(){
		$('#myPleaseWait').modal('show');
		$("#main-right").load("grupos!input.action");
		$('#myPleaseWait').modal('hide');
		};
		
		agrupaemisoras = function(){
// 		$('#myPleaseWait').modal('show');
		$("#main-right").load("grupo-emisora!input.action");
// 		$('#myPleaseWait').modal('hide');
		};
		
		prefFrecuentes = function(){
// 		$('#myPleaseWait').modal('show');
		$("#main-right").load("pref-frecuentes!input.action");
// 		$('#myPleaseWait').modal('hide');
		};
		
		
	</script>
</head>
<body>
<div id="main-body">
<div id="main-menu">
<h3>Banorte <small>FastCash v1</small></h3>
</div>
<div id="main-left">
			<nav class="bs-docs-sidebar hidden-print affix-top">
			<ul class="nav bs-docs-sidenav">
				
				<li id="jsretefectivo" onclick="retirosefectivo();$('#jstablero').addClass('active'); $('#jsclientes').removeClass('active'); $('#jsficha').removeClass('active'); $('#jsfoda').removeClass('active'); $('#jsmapas').removeClass('active'); $('#jsminutas').removeClass('active'); $('#jspanel').removeClass('active'); $('#jsvolver').removeClass('active');" class=""><a href="#jstablero">Retiro de efectivo</a></li>
				<li id="jspagservicios" onclick="pagosservicios();$('#jstablero').addClass('active'); $('#jsclientes').removeClass('active'); $('#jsficha').removeClass('active'); $('#jsfoda').removeClass('active'); $('#jsmapas').removeClass('active'); $('#jsminutas').removeClass('active'); $('#jspanel').removeClass('active'); $('#jsvolver').removeClass('active');" class=""><a href="#jstablero">Pagos de Servicios</a></li>
				<li id="jspagostdc" onclick="pagostdc();$('#jstablero').addClass('active'); $('#jsclientes').removeClass('active'); $('#jsficha').removeClass('active'); $('#jsfoda').removeClass('active'); $('#jsmapas').removeClass('active'); $('#jsminutas').removeClass('active'); $('#jspanel').removeClass('active'); $('#jsvolver').removeClass('active');" class=""><a href="#jstablero">Pagos TDC</a></li>
				<li id="jsagrupaciones" onclick="agrupaciones();$('#jstablero').addClass('active'); $('#jsclientes').removeClass('active'); $('#jsficha').removeClass('active'); $('#jsfoda').removeClass('active'); $('#jsmapas').removeClass('active'); $('#jsminutas').removeClass('active'); $('#jspanel').removeClass('active'); $('#jsvolver').removeClass('active');" class=""><a href="#jstablero">Agrupaciones</a></li>
				<!--
				<li id="jsagrupaemisoras" onclick="agrupaemisoras();$('#jstablero').addClass('active'); $('#jsclientes').removeClass('active'); $('#jsficha').removeClass('active'); $('#jsfoda').removeClass('active'); $('#jsmapas').removeClass('active'); $('#jsminutas').removeClass('active'); $('#jspanel').removeClass('active'); $('#jsvolver').removeClass('active');" class=""><a href="#jstablero">Agrupa-Emisoras</a></li>
				<li id="jsemisoras" onclick="emisoras();$('#jstablero').addClass('active'); $('#jsclientes').removeClass('active'); $('#jsficha').removeClass('active'); $('#jsfoda').removeClass('active'); $('#jsmapas').removeClass('active'); $('#jsminutas').removeClass('active'); $('#jspanel').removeClass('active'); $('#jsvolver').removeClass('active');" class=""><a href="#jstablero">Emisoras</a></li>
				<li id="jspreffrecuentes" onclick="prefFrecuentes();$('#jstablero').addClass('active'); $('#jsclientes').removeClass('active'); $('#jsficha').removeClass('active'); $('#jsfoda').removeClass('active'); $('#jsmapas').removeClass('active'); $('#jsminutas').removeClass('active'); $('#jspanel').removeClass('active'); $('#jsvolver').removeClass('active');" class=""><a href="#jstablero">Pref Frecuentes</a></li>
				-->
				<li id="jsvolver" onclick="volver();$('#jsvolver').addClass('active'); $('#jsclientes').removeClass('active'); $('#jsficha').removeClass('active');  $('#jsfoda').removeClass('active'); $('#jsmapas').removeClass('active'); $('#jsminutas').removeClass('active'); $('#jspanel').removeClass('active'); $('#jstablero').removeClass('active');" class=""><a href="#jsvolver">Volver a Página Incial</a></li>
			</ul>
			</nav>
		</div>
<div id="main-right">

</div>
</div>
</body>
</html>