<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Mail Us</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<!-- //js -->
<script type="text/javascript" src="//maps.googleapis.com/maps/api/js"></script>
<!-- font-awesome-icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome-icons -->
<link href="http://fonts.googleapis.com/css?family=Shadows+Into+Light+Two&amp;subset=latin-ext" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Karla:400,400i,700,700i&amp;subset=latin-ext" rel="stylesheet">
</head>
	
<body>
<!-- banner -->
	<div class="banner1">
		<div class="container">
				<jsp:include page="header.jsp" flush="true"/>
			<div class="w3layouts_banner1_info">
				<h2>Mail Us</h2>
			</div>
		</div>
	</div>
<!-- //banner -->
<!-- mail -->
	<div class="agileits_w3layouts_mail_grids">	
		<div class="col-md-7 w3l_mail_left">
			<div id="map"></div>
		</div>
		<div class="col-md-5 w3l_mail_right">
			<h3>Contact Info</h3>
			<ul>
				<li><span><i class="fa fa-home" aria-hidden="true"></i>Address<label>:</label></span> 1234k Avenue, New York City.</li>
				<li><span><i class="fa fa-phone" aria-hidden="true"></i>Phone<label>:</label></span> (+012) 345 6432</li>
				<li><span><i class="fa fa-fax" aria-hidden="true"></i>Fax<label>:</label></span> +82-21-1218</li>
				<li><span><i class="fa fa-envelope" aria-hidden="true"></i>Email<label>:</label></span> <a href="mailto:info@example.com">info@example.com</a></li>
				<li><span><i class="fa fa-globe" aria-hidden="true"></i>Website<label>:</label></span> <a href="#">website</a></li>
			</ul>
		</div>
		<div class="clearfix"> </div>
	</div>
	<div class="banner-bottom">
		<div class="container">
			<h3 class="agileits_head">get in touch with us</h3>
			<p class="w3layouts_para">The Karate</p>
			<div class="agileinfo_mail_grids">
				<form action="#" method="post">
					<span class="input input--chisato">
						<input class="input__field input__field--chisato" name="Name" type="text" id="input-13" placeholder=" " required="" />
						<label class="input__label input__label--chisato" for="input-13">
							<span class="input__label-content input__label-content--chisato" data-content="Name">Name</span>
						</label>
					</span>
					<span class="input input--chisato">
						<input class="input__field input__field--chisato" name="Email" type="email" id="input-14" placeholder=" " required="" />
						<label class="input__label input__label--chisato" for="input-14">
							<span class="input__label-content input__label-content--chisato" data-content="Email">Email</span>
						</label>
					</span>
					<span class="input input--chisato">
						<input class="input__field input__field--chisato" name="Subject" type="text" id="input-15" placeholder=" " required="" />
						<label class="input__label input__label--chisato" for="input-15">
							<span class="input__label-content input__label-content--chisato" data-content="Subject">Subject</span>
						</label>
					</span>
					<textarea name="Message" placeholder="Your comment here..." required></textarea>
					<input type="submit" value="Submit">
				</form>
			</div>
		</div>
	</div>
<!-- //mail -->
<!-- map -->
	<script type="text/javascript">
		// When the window has finished loading create our google map below
		google.maps.event.addDomListener(window, 'load', init);
	
		function init() {
			// Basic options for a simple Google Map
			// For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
			var mapOptions = {
				// How zoomed in you want the map to start at (always required)
				zoom: 11,

				// The latitude and longitude to center the map (always required)
				center: new google.maps.LatLng(40.6700, -73.9400), // New York

				// How you would like to style the map. 
				// This is where you would paste any style found on Snazzy Maps.
				styles: [{"featureType":"administrative","elementType":"labels.text.fill","stylers":[{"color":"#444444"}]},{"featureType":"administrative.neighborhood","elementType":"labels.text","stylers":[{"visibility":"off"}]},{"featureType":"administrative.neighborhood","elementType":"labels.icon","stylers":[{"visibility":"on"}]},{"featureType":"landscape","elementType":"all","stylers":[{"color":"#f2f2f2"}]},{"featureType":"poi","elementType":"all","stylers":[{"visibility":"off"}]},{"featureType":"poi.attraction","elementType":"labels","stylers":[{"visibility":"on"}]},{"featureType":"poi.attraction","elementType":"labels.text","stylers":[{"visibility":"on"}]},{"featureType":"poi.attraction","elementType":"labels.icon","stylers":[{"visibility":"on"}]},{"featureType":"poi.business","elementType":"all","stylers":[{"visibility":"simplified"}]},{"featureType":"poi.business","elementType":"labels.text","stylers":[{"visibility":"on"}]},{"featureType":"poi.business","elementType":"labels.icon","stylers":[{"visibility":"on"},{"weight":"1.10"}]},{"featureType":"poi.government","elementType":"labels","stylers":[{"visibility":"on"}]},{"featureType":"poi.park","elementType":"geometry","stylers":[{"visibility":"on"}]},{"featureType":"poi.park","elementType":"labels.text","stylers":[{"visibility":"simplified"}]},{"featureType":"road","elementType":"all","stylers":[{"saturation":-100},{"lightness":45}]},{"featureType":"road.highway","elementType":"all","stylers":[{"visibility":"simplified"}]},{"featureType":"road.arterial","elementType":"labels.text","stylers":[{"visibility":"on"}]},{"featureType":"road.arterial","elementType":"labels.icon","stylers":[{"visibility":"off"}]},{"featureType":"road.local","elementType":"labels.text","stylers":[{"visibility":"off"}]},{"featureType":"transit","elementType":"all","stylers":[{"visibility":"off"}]},{"featureType":"water","elementType":"all","stylers":[{"color":"#91bde7"},{"visibility":"on"}]}]
			};

			// Get the HTML DOM element that will contain your map 
			// We are using a div with id="map" seen below in the <body>
			var mapElement = document.getElementById('map');

			// Create the Google Map using our element and options defined above
			var map = new google.maps.Map(mapElement, mapOptions);

			// Let's also add a marker while we're at it
			var marker = new google.maps.Marker({
				position: new google.maps.LatLng(40.6700, -73.9400),
				map: map,
				title: 'Snazzy!'
			});
		}
	</script>
<!-- //map -->
	<script src="js/classie.js"></script>
	<script>
		(function() {
			// trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
			if (!String.prototype.trim) {
				(function() {
					// Make sure we trim BOM and NBSP
					var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
					String.prototype.trim = function() {
						return this.replace(rtrim, '');
					};
				})();
			}

			[].slice.call( document.querySelectorAll( 'input.input__field' ) ).forEach( function( inputEl ) {
				// in case the input is already filled..
				if( inputEl.value.trim() !== '' ) {
					classie.add( inputEl.parentNode, 'input--filled' );
				}

				// events:
				inputEl.addEventListener( 'focus', onInputFocus );
				inputEl.addEventListener( 'blur', onInputBlur );
			} );

			function onInputFocus( ev ) {
				classie.add( ev.target.parentNode, 'input--filled' );
			}

			function onInputBlur( ev ) {
				if( ev.target.value.trim() === '' ) {
					classie.remove( ev.target.parentNode, 'input--filled' );
				}
			}
		})();
	</script>
<!-- footer -->
<jsp:include page="footer.jsp" flush="true"/>
<!-- //footer -->
<!-- bootstrap-pop-up -->
	<div class="modal video-modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModal">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					Karate
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>						
				</div>
				<section>
					<div class="modal-body">
						<img src="images/4.jpg" alt=" " class="img-responsive" />
						<p>Ut enim ad minima veniam, quis nostrum 
							exercitationem ullam corporis suscipit laboriosam, 
							nisi ut aliquid ex ea commodi consequatur? Quis autem 
							vel eum iure reprehenderit qui in ea voluptate velit 
							esse quam nihil molestiae consequatur, vel illum qui 
							dolorem eum fugiat quo voluptas nulla pariatur.
							<i>" Quis autem vel eum iure reprehenderit qui in ea voluptate velit 
								esse quam nihil molestiae consequatur.</i></p>
					</div>
				</section>
			</div>
		</div>
	</div>
<!-- //bootstrap-pop-up -->
<!-- for bootstrap working -->
	<script src="js/bootstrap.js"></script>
<!-- //for bootstrap working -->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
            //给当前标题加上样式
            $("#contactUs").addClass("menu__item--current");
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			*/
            //回到顶部的功能
			$().UItoTop({ easingType: 'easeOutQuart' });
								
			});
	</script>
<!-- //here ends scrolling icon -->
</body>
</html>