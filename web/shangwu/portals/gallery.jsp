<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Gallery</title>
<!-- custom-theme -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //custom-theme -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/simpleLightbox.css" rel="stylesheet" type="text/css" />
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
				<h2>Gallery</h2>
			</div>
		</div>
	</div>
<!-- //banner -->
<!-- gallery -->
	<div class="banner-bottom">
		<div class="container">
			<h3 class="agileits_head">Gallery</h3>
			<p class="w3layouts_para">The Karate</p>
			<div class="w3ls_gallery_grids">
				<div class="col-md-4 w3_agile_gallery_grid">
					<div class="agile_gallery_grid">
						<a title="Donec sapien massa, placerat ac sodales ac, feugiat quis est." href="images/1.jpg">
							<div class="agile_gallery_grid1">
								<img src="images/1.jpg" alt=" " class="img-responsive" />
								<div class="w3layouts_gallery_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
						</a>
					</div>
					<div class="agile_gallery_grid">
						<a title="Donec sapien massa, placerat ac sodales ac, feugiat quis est." href="images/2.jpg">
							<div class="agile_gallery_grid1">
								<img src="images/2.jpg" alt=" " class="img-responsive" />
								<div class="w3layouts_gallery_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
						</a>
					</div>
					<div class="agile_gallery_grid">
						<a title="Donec sapien massa, placerat ac sodales ac, feugiat quis est." href="images/3.jpg">
							<div class="agile_gallery_grid1">
								<img src="images/3.jpg" alt=" " class="img-responsive" />
								<div class="w3layouts_gallery_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-4 w3_agile_gallery_grid">
					<div class="agile_gallery_grid">
						<a title="Donec sapien massa, placerat ac sodales ac, feugiat quis est." href="images/4.jpg">
							<div class="agile_gallery_grid1">
								<img src="images/4.jpg" alt=" " class="img-responsive" />
								<div class="w3layouts_gallery_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
						</a>
					</div>
					<div class="agile_gallery_grid">
						<a title="Donec sapien massa, placerat ac sodales ac, feugiat quis est." href="images/5.jpg">
							<div class="agile_gallery_grid1">
								<img src="images/5.jpg" alt=" " class="img-responsive" />
								<div class="w3layouts_gallery_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
						</a>
					</div>
					<div class="agile_gallery_grid">
						<a title="Donec sapien massa, placerat ac sodales ac, feugiat quis est." href="images/8.jpg">
							<div class="agile_gallery_grid1">
								<img src="images/8.jpg" alt=" " class="img-responsive" />
								<div class="w3layouts_gallery_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="col-md-4 w3_agile_gallery_grid">
					<div class="agile_gallery_grid">
						<a title="Donec sapien massa, placerat ac sodales ac, feugiat quis est." href="images/9.jpg">
							<div class="agile_gallery_grid1">
								<img src="images/9.jpg" alt=" " class="img-responsive" />
								<div class="w3layouts_gallery_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
						</a>
					</div>
					<div class="agile_gallery_grid">
						<a title="Donec sapien massa, placerat ac sodales ac, feugiat quis est." href="images/10.jpg">
							<div class="agile_gallery_grid1">
								<img src="images/10.jpg" alt=" " class="img-responsive" />
								<div class="w3layouts_gallery_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
						</a>
					</div>
					<div class="agile_gallery_grid">
						<a title="Donec sapien massa, placerat ac sodales ac, feugiat quis est." href="images/11.jpg">
							<div class="agile_gallery_grid1">
								<img src="images/11.jpg" alt=" " class="img-responsive" />
								<div class="w3layouts_gallery_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
<!-- //gallery -->
	<script src="js/simpleLightbox.js"></script>
	<script>
		$('.w3_agile_gallery_grid a').simpleLightbox();
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
            $("#gallery").addClass("menu__item--current");
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