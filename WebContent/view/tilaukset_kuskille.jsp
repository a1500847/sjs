<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<jsp:useBean id="tilaus" class="pizzicato.model.Tilaus" scope="request" />
<%@ page import="pizzicato.model.Kayttaja"%>
<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.PizzaTilaus"%>
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="java.text.DecimalFormat"%>
<jsp:useBean id="tilaukset" type="java.util.ArrayList<Tilaus>"
	scope="request" />

<jsp:useBean id="kayttaja" class="pizzicato.model.Kayttaja"
	scope="session" />
	

<html lang="fi">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<title>Pizzeria Pizzicato</title>
<!--REQUIRED STYLE SHEETS-->
    <!-- JQUERY CODE SOURCE -->
    <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<!-- BOOTSTRAP CORE STYLE CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLE CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<!-- CUSTOM STYLE CSS -->
<link href="assets/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link href='https://fonts.googleapis.com/css?family=Raleway'
	rel='stylesheet' type='text/css'>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="assets/js/html5.image.preview.min.js"></script>
<!-- tämä on skripti kuvien esikatseluun  -->
</head>
<body>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<div class="container">

	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="Etusivu">Pizzeria Pizzicato</a>
	</div>
	<!-- Collect the nav links for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="Etusivu">Etusivu</a></li>
			<li><a href="Etusivu#pizzamenu">Pizzat</a></li>
			<li><a href="Etusivu#contact">Yhteystiedot</a></li>
			<li id="ostoskorinappi2"><a href="ostoskori" class="btn btn-primary" role="button" id="ostoskorinappi"><span class="glyphicon glyphicon-shopping-cart"></span> <span class="badge"><%=tilaus.getPizzaTilLkm()%></span></a></li>
			<li>
			
			
			
			<% 		
				if (kayttaja!= null &&  kayttaja.getUserRole()!= null){
					
					%> <li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">
						<%out.println("Tervetuloa "+"<b>"+ kayttaja.getUsername() +"</b>"+ "!"); %><b class="caret"></b></a>
						<ul class="dropdown-menu">	
           				<li>  <a href="KirjauduUlos" id="kirjaudu-ulos-nappi"><span class="glyphicon glyphicon-log-out"></span> Kirjaudu ulos</a></li>
           				
           				  <% if (kayttaja != null && kayttaja.getUserRole().equals("kokki") && kayttaja.getUserRole().equals("kuljettaja")){%>
           				<li> <a href="roolinvalitseminen" id="kirjaudu-ulos-nappi"><i class="fa fa-user" aria-hidden="true"></i> Roolin valitseminen</a></li>
           				
           					<%} %>
           					
           				 <% if (kayttaja != null && kayttaja.getUserRole().equals("omistaja")){%>
     
           				<li> <a href="ListaaPizzat" id="kirjaudu-ulos-nappi"><i class="fa fa-pencil-square-o" aria-hidden="true"></i> Muokkaa pizzoja</a></li>
           				<li> <a href="roolinvalitseminen" id="kirjaudu-ulos-nappi"><i class="fa fa-user" aria-hidden="true"></i> Roolinäkymät</a></li>
           					<%} %>
           				
						</ul>
					
					<%} 
			
			
				else {%> <li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span>
						Kirjaudu <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<form method="post" role="form" class="navbar-form navbar-right">
							<div class="form-group">
								<input type="text" class="form-control" name="username"
									placeholder="Käyttäjätunnus" autocomplete="off">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" name="password"
									placeholder="Salasana" autocomplete="off">
							</div class="form-group">
							<button type="submit" name="kirjautumisnappi" class="btn btn-primary">Kirjaudu</button>
						</form>
						<div id=huomio>
							<span id="ilmoitus">
								<%
									String message = (String) request.getAttribute("message");
									if (message != null) {
										out.println("<p>" + message + "</p>");
									}
								%>
							</span>
						</div>


					</ul>
					
				<li><a href="Rekisteroityminen">Rekisteröidy</a></li>
					</li>
		
		
				<%} %>
		
		
			
			
		</ul>
	</div>
	<!-- /.navbar-collapse -->
</div>
<!-- /.container --> </nav>
<!-- end of navigation -->


	<!-- Free Section -->

	<section id="pizzat">
	<div class="container-fluid">
		<div class="row text-center for-full-back color-light jumbotron" id="tilaustaulu">
			<H1>Tilaukset</H1>
			<h2>(Kuskin näkymä)</h2>
			<div class="container-fluid" id="kokintilaukset">

				<div class="table-responsive">
					<table class="table">
						<tbody>
							<%for(int i = 0; i < tilaukset.size(); i++) {%>
							<tr>
								<th>TilausId</th>
								<th>Sukunimi</th>
								<th>Puhelin</th>
								<th>Osoite</th>
								<th>Postinro</th>
								<th>Postitmp</th>
								<th>Status</th>
								<th>Tilauksen ajankohta</th>
								<th>Cola</th>
								<th>Fanta</th>
								<th>Sprite</th>
								<th>Yhteishinta</th>
								<th>Kuittaa</th>
							</tr>
							<tr>
								<td><%=tilaukset.get(i).getTilausId()%></td>
								<td><%=tilaukset.get(i).getaSukunimi()%></td>
								<td><%=tilaukset.get(i).getaPuh()%></td>
								<td><%=tilaukset.get(i).getaOsoite()%></td>
								<td><%=tilaukset.get(i).getaPostiNro()%></td>
								<td><%=tilaukset.get(i).getaPostiTmp()%></td>								
								<td><%=tilaukset.get(i).getStatus()%></td>
								<td><%=tilaukset.get(i).getTilAjankohta()%></td>
								<td><%=tilaukset.get(i).getCola()%></td>
								<td><%=tilaukset.get(i).getFanta()%></td>
								<td><%=tilaukset.get(i).getSprite()%></td>
								<%DecimalFormat des = new DecimalFormat("0.00"); %>
								<td><%=des.format(tilaukset.get(i).getYhtHinta())%></td>			
								<td>
									<form method="post">
										<button type="submit" class="btn btn-success"name="nappi" value="<%=tilaukset.get(i).getTilausId()%>"> Toimitettu <i class="fa fa-check" aria-hidden="true"></i> </button>
									</form>
								</td>														
							</tr>
							<tr>
									<th>Pizzat<th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
									<th></th>
							</tr>
								<%for (int j=0; j<tilaukset.get(i).getPizzatilaukset().size(); j++){ %> 
									<tr>
										<td><%=tilaukset.get(i).getPizzaTilaus(j).getPizza().getpNimi()%></td>
										<td></td>							
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>										
									</tr>
								<% } %>																		
								<tr><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td><td class="tableseparator"></td></tr>
							<% } %>						
						</tbody>
					</table>





				</div>

			</div>
		</div>
		</div>
		</section>
	<!--End Free Section -->

<!-- Contact Section -->
<section class="for-full-back color-white " id="contact">
<div class="container">

	<div class="col-md-4 contact-cls"  id="osoitekartta">
		<h3><span><i class="fa fa-home"></i>&nbsp;Osoite:</span></h3>
		<div>
			<span>Kuusitie 6, Meilahti, Helsinki</span> <br />
			<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script><div style="overflow:hidden;height:200px;width:250px;"><div id="gmap_canvas" style="height:200px;width:290px;"><style>#gmap_canvas img{max-width:none!important;background:none!important}</style><a class="google-map-code" href="http://www.themecircle.net" id="get-map-data">Kartta</a></div></div><script type="text/javascript"> function init_map(){var myOptions = {zoom:16,center:new google.maps.LatLng(60.19484920000001,24.89962639999999),mapTypeId: google.maps.MapTypeId.ROADMAP};map = new google.maps.Map(document.getElementById("gmap_canvas"), myOptions);marker = new google.maps.Marker({map: map,position: new google.maps.LatLng(60.19484920000001, 24.89962639999999)});infowindow = new google.maps.InfoWindow({content:"<b>Pizzeria Pizzicato</b><br/>kuusitie 6<br/> Helsinki" });google.maps.event.addListener(marker, "click", function(){infowindow.open(map,marker);});infowindow.open(map,marker);}google.maps.event.addDomListener(window, 'load', init_map);</script>

		</div>

	</div>
	
	<div class="col-md-2 contact-cls">
		<h3><span><i class="fa fa-clock-o"></i>&nbsp;Aukioloajat:</span></h3>
		<div>
			<span>ma-to 11 - 21<br>pe-la  11 - 22<br>su		12 - 19  </span>
			

		</div>

	</div>
	
	<div class="col-md-5 contact-cls">
		<h3><span><i class="fa fa-phone"></i>&nbsp;Puhelin:</span></h3>
		<div>
			<span>(+358) 040-123456</span> <br /> 
		</div>
	</div>
	
	
</section>

<!--End Contact Section -->
	<!--footer Section -->
	<div class="for-full-back " id="footer">2016 | Silver Java
		Slayers</div>
	<!--End footer Section -->
	<!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
	<!-- BOOTSTRAP CORE SCRIPT   -->
	<script src="assets/plugins/bootstrap.js"></script>
	<!-- CUSTOM SCRIPTS -->
	<script src="assets/js/custom.js"></script>
	

</body>
</html>