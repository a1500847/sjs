<%@ page language="java" contentType="text/html; UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->

<%@ page import="pizzicato.model.Tilaus"%>
<%@ page import="pizzicato.model.PizzaTilaus"%>
<jsp:useBean id="tilaus" class="pizzicato.model.Tilaus" scope="session" />
<%@ page import="pizzicato.model.Pizza"%>
<%@ page import="pizzicato.model.Tayte"%>
<%@ page import="pizzicato.model.Kayttaja"%>
<jsp:useBean id="pizzat" type="java.util.ArrayList<Pizza>"
	scope="request" />
<jsp:useBean id="kayttaja" class="pizzicato.model.Kayttaja"
	scope="session" />
	<jsp:useBean id="pizza" class="pizzicato.model.Pizza"
	scope="request" />
<!--<![endif]-->
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!--[if IE]>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pizzeria Pizzicato -laadukas ja edullinen pizzeria</title>
<!--REQUIRED STYLE SHEETS-->
<!-- JQUERY CODE SOURCE -->
<script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">


<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>




<!-- BOOTSTRAP CORE STYLE CSS -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONTAWESOME STYLE CSS -->
<link type="text/css" href="assets/css/font-awesome.min.css"
	rel="stylesheet" />
<!-- CUSTOM STYLE CSS -->
<link type="text/css" href="assets/css/style.css" rel="stylesheet" />
<!-- GOOGLE FONT -->
<link type="text/css"
	href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet'
	type='text/css'>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->

</head>
</body>
<header class="masthead"> 
<section id="cover">
<div class="jumbotron text-center" id="cover-caption">

	<div>
		
		<img src="assets/images/logo.png" alt="pizzicato" class="img-responsive"style="height:50%;">
		<h1>Tervetuloa!</h1>

		<span><i class="fa fa-home"></i>&nbsp; <a href="https://goo.gl/maps/so8N2prRDWS2">Kuusitie 6, Helsinki</a> </span> <br> 
			<span><i class="fa fa-phone"></i>&nbsp;(+358) 040-123456</span> <br /> <br> 
			<h2>Tutustu menuun ja tee tilaus!</h2>
			<a href="#pizzamenu" class="glyphicon glyphicon-chevron-down"></a>
	</div>
</div>

</section> 
</header>

<!-- navigation -->
<nav class="navbar navbar-inverse" data-spy="affix" data-offset-top="500" id=navigaatio>
<div class="container">

	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-ex1-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="ListaaPizzat">Pizzeria Pizzicato</a>
	</div>
	<!-- Collect the nav links for toggling -->
	<div class="collapse navbar-collapse navbar-ex1-collapse">
		<ul class="nav navbar-nav navbar-right">
			<li><a href="Etusivu">Etusivu</a></li>
			<li><a href="#pizzamenu">Pizzat</a></li>
			<li><a href="#contact">Yhteystiedot</a></li>
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

<script type="text/javascript">

	$('nav').affix({
	    offset: {
	      top: $('nav#navigaatio').offset().top
	    }
	}); 

</script>

<!-- Free Section -->
<section id="tausta"> 
<div class="container">
	<div class="row text-center for-full-back color-light" id="pizzamenu">
		<div class="col-md-8 col-md-offset-2">
			<h1>Pizzalista</h1>
				<%
					String viesti = (String) session.getAttribute("viesti");
					if (viesti != null) {%>
						<div class="alert alert-success">
						<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a> 
						<%out.println("<strong>" + viesti + "</strong>");%>
						</div>
					<%}
					session.setAttribute("viesti", null);%>
			
			<div class="table-responsive" id="pizzataulukon_kehys">
				<table class="table table-hover" align="center" id="pizzataulukko_asiakkaalle">
					<tr>
						<th>Nimi</th>
						<th>Täytteet</th>
						<th>Hinta</th>
						<th></th>
					</tr>
					<%for(int i = 0; i < pizzat.size(); i++) {%>
					<tr>
						<td><%=pizzat.get(i).getpNimi()%></td>
						<td><% for(int j=0; j < pizzat.get(i).getTaytteet().size(); j++){ %>
							<%=pizzat.get(i).getTayte(j).gettNimi() %>
						<% } %> 
						</td>
						<td><%=pizzat.get(i).getpHinta()%></td>
						<td>
							<div class="dropdown">
    						<button class="btn btn-success dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-shopping-cart">
    						<span class="caret"></span></button>
    						<ul class="dropdown-menu noclose pull-right text-center">
     						<li></li>
      						<li><form role="form" method="post" action="ostoskori">
   								<div id=pizzalkm>
   								<label>Pizzojen lkm.</label><br>
      							<input type="number" min="1" max="10" step="1" name="pizzamaara" value="1" pattern="[1-9]" oninvalid="setCustomValidity('Valitse vähintään 1 pizza')" oninput="setCustomValidity('')" required  ><br>
      							<input type="hidden" name="pizza_id" value="<%=pizzat.get(i).getPizzaId()%>"/>
      							</div> 
      							
      							<button type="submit" class="btn btn-success" name="pizzanlisays" > <span class="glyphicon glyphicon-shopping-cart"></span><span class="glyphicon glyphicon-plus"></span></button>
      							</form>
      							</li>
    							</ul>
  							</div>
						</td>
					</tr>
					<% } %>
				</table>
			</div>
		</div>
	</div>
</div>

</section>
<!-- Free Section -->



<!-- Info Section -->
<section class="for-full-back color-white " id="info">
<div class="container">

	<div class="col-lg-12 text-center">
		<h1>Pizzeria Pizzicato</h1>
		<div>
			<h3>Pizzicato on suomalainen pizzeria Meilahden sydämessä. Panostamme menussamme hyvään hinta-laatusuhteeseen. 
			Pizzamme ovat laadukkaita, mutta edullisia. Suosimme myös kotimaisia raaka-aineita. 
			Voit nauttia pizzasi kodikkaassa ravintolassamme, noutaa pizzan mukaasi tai tilata sen kätevästi kotiisi kuljetettuna pääkaupunkiseudun alueella. 
			
			
			</h3>
		</div>

	</div>
</div>
</section>

<!--End Info Section -->

<!-- Free Section -->
<section id="tausta2"> 
<div class="container">
	<div class="row text-center for-full-back color-light" id="uutuuksia">
		<div class="col-sm-13">
			<h1>Maista uutuksia!</h1>

			<img src="assets/images/mainos1.png" alt="Maista Uutuuksia!" style="width:95%; padding-bottom: 2.5%;">
				
	</div>
	</div>
</div>

</section>
<!-- Free Section -->



<div class="container">

	<div class="col-md-8 col-md-offset-2"></div>

</div>





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
<div class="for-full-back " id="footer"><p>2016 | Silver Java Slayers</p></div>
<!--End footer Section -->
<!-- JAVASCRIPT FILES PLACED AT THE BOTTOM TO REDUCE THE LOADING TIME  -->
<!-- BOOTSTRAP CORE SCRIPT   -->
<!-- <script src="assets/plugins/bootstrap.js"></script> tämä ei toiminut niin javascriptkirjasto haetaan netistä headerissa -->
<!-- CUSTOM SCRIPTS -->
<script src="assets/js/custom.js"></script>





</body>

</html>
