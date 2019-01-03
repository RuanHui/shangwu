<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Services</title>
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
				<h2>Services</h2>
			</div>
		</div>
	</div>
<!-- //banner -->
<!-- banner-bottom -->
	<div class="banner-bottom">
		<div class="container">
			<h3 class="agileits_head">what we offer</h3>
			<p class="w3layouts_para">The Karate</p>
			<div class="w3ls_service_grids">
				<div class="col-md-3 w3l_service_grid_left">
					<div class="w3_service_grid_left_grid">
						<img src="images/2.png" alt=" " class="img-responsive" />
					</div>
					<h4>Duis lacinia sapien</h4>
					<p>Nunc posuere sapien eros, sed faucibus metus feugiat non.</p>
					<div class="w3_agile_service_more">
						<a href="#" data-toggle="modal" data-target="#myModal">Read More<i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
					</div>
				</div>
				<div class="col-md-3 w3l_service_grid_left">
					<div class="w3_service_grid_left_grid">
						<img src="images/3.png" alt=" " class="img-responsive" />
					</div>
					<h4>Proin eros metus</h4>
					<p>Nunc posuere sapien eros, sed faucibus metus feugiat non.</p>
					<div class="w3_agile_service_more">
						<a href="#" data-toggle="modal" data-target="#myModal">Read More<i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
					</div>
				</div>
				<div class="col-md-3 w3l_service_grid_left">
					<div class="w3_service_grid_left_grid">
						<img src="images/4.png" alt=" " class="img-responsive" />
					</div>
					<h4>faucibus metus feugi</h4>
					<p>Nunc posuere sapien eros, sed faucibus metus feugiat non.</p>
					<div class="w3_agile_service_more">
						<a href="#" data-toggle="modal" data-target="#myModal">Read More<i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
					</div>
				</div>
				<div class="col-md-3 w3l_service_grid_left">
					<div class="w3_service_grid_left_grid">
						<img src="images/5.png" alt=" " class="img-responsive" />
					</div>
					<h4>lorem egestas comm</h4>
					<p>Nunc posuere sapien eros, sed faucibus metus feugiat non.</p>
					<div class="w3_agile_service_more">
						<a href="#" data-toggle="modal" data-target="#myModal">Read More<i class="fa fa-long-arrow-right" aria-hidden="true"></i></a>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //banner-bottom -->
<!-- stats -->
	<div class="stats">
		<div class="container">
			<div class="col-md-3 w3_stats_grid">
				<h3 id="w3l_stats1" class="odometer">0</h3>
				<p>Years of Experience</p>
			</div>
			<div class="col-md-3 w3_stats_grid">
				<h3 id="w3l_stats2" class="odometer">0</h3>
				<p>Awards Winning</p>
			</div>
			<div class="col-md-3 w3_stats_grid">
				<h3 id="w3l_stats3" class="odometer">0</h3>
				<p>Students</p>
			</div>
			<div class="col-md-3 w3_stats_grid">
				<h3 id="w3l_stats4" class="odometer">0</h3>
				<p>Branches</p>
			</div>
			<div class="clearfix"> </div>
		<!-- odometer-script -->
			<script src="js/odometer.js"></script>
			<script>
				window.odometerOptions = {
				  format: '(,ddd).dd'
				};
				setTimeout(function(){
					jQuery('#w3l_stats1').html(25);
				}, 1000);
			</script>
			<script>
				window.odometerOptions = {
				  format: '(,ddd).dd'
				};
				setTimeout(function(){
					jQuery('#w3l_stats2').html(330);
				}, 1000);
			</script>
			<script>
				window.odometerOptions = {
				  format: '(,ddd).dd'
				};
				setTimeout(function(){
					jQuery('#w3l_stats3').html(22496);
				}, 1000);
			</script>
			<script>
				window.odometerOptions = {
				  format: '(,ddd).dd'
				};
				setTimeout(function(){
					jQuery('#w3l_stats4').html(620);
				}, 1000);
			</script>
		<!-- //odometer-script -->
		</div>
	</div>
<!-- //stats -->
<!-- featured-services -->
	<div class="banner-bottom">
		<div class="container">
			<h3 class="agileits_head">Our Featured Services</h3>
			<p class="w3layouts_para">The Karate</p>
			<div class="w3ls_service_grids">
				<div class="col-md-4 w3_agile_services_grid">
					<div class="agile_services_grid">
						<div class="hover06 column">
							<div>
								<figure><img src="images/5.jpg" alt=" " class="img-responsive" /></figure>
							</div>
						</div>
						<div class="agile_services_grid_pos">
							<i class="fa fa-cubes" aria-hidden="true"></i>
						</div>
					</div>
					<h4>tempus eu turpis</h4>
					<p>Aliquam lacus turpis, lobortis quis dolor sed, dignissim rhoncus neque.</p>
				</div>
				<div class="col-md-4 w3_agile_services_grid">
					<div class="agile_services_grid">
						<div class="hover06 column">
							<div>
								<figure><img src="images/2.jpg" alt=" " class="img-responsive" /></figure>
							</div>
						</div>
						<div class="agile_services_grid_pos">
							<i class="fa fa-line-chart" aria-hidden="true"></i>
						</div>
					</div>
					<h4>lobortis quis dolor</h4>
					<p>Aliquam lacus turpis, lobortis quis dolor sed, dignissim rhoncus neque.</p>
				</div>
				<div class="col-md-4 w3_agile_services_grid">
					<div class="agile_services_grid">
						<div class="hover06 column">
							<div>
								<figure><img src="images/3.jpg" alt=" " class="img-responsive" /></figure>
							</div>
						</div>
						<div class="agile_services_grid_pos">
							<i class="fa fa-cog" aria-hidden="true"></i>
						</div>
					</div>
					<h4>dignissim rhoncus</h4>
					<p>Aliquam lacus turpis, lobortis quis dolor sed, dignissim rhoncus neque.</p>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //featured-services -->
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
            $("#service").addClass("menu__item--current");
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