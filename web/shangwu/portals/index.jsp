<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<html>
<head>
<title>尚武道场</title>
	<link rel="shortcut icon" href="/resource/images/icon.png" type="image/x-icon"/>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>

<link href="shangwu/portals/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="shangwu/portals/css/style.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" href="shangwu/portals/css/flexslider.css" type="text/css" media="screen" property="" />
<script type="text/javascript" src="shangwu/portals/js/jquery-2.1.4.min.js"></script>
<link href="shangwu/portals/css/font-awesome.css" rel="stylesheet">
</head>
	
<body>
<!-- banner -->
	<div class="banner">
		<div class="container">
			<jsp:include page="header.jsp" flush="true"/>
			<div class="w3_agile_banner_info">
				<section class="slider">
					<div class="flexslider">
						<ul class="slides">
							<li>
								<div class="agile_banner_info_grid">
									<p>01.</p>
									<div class="agile_banner_info_grid_pos">
										<p><span>Karate</span> is the best thing you can do for your child</p>
									</div>
								</div>
							</li>
							<li>
								<div class="agile_banner_info_grid">
									<p>02.</p>
									<div class="agile_banner_info_grid_pos">
										<p><span>Karate</span> makes us see sides of ourselves that we hide</p>
									</div>
								</div>
							</li>
							<li>
								<div class="agile_banner_info_grid">
									<p>03.</p>
									<div class="agile_banner_info_grid_pos">
										<p><span>Karate</span> begins and ends with courtesy</p>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</section>			
			</div>
		</div>
	</div>
<!-- //banner -->
<!-- banner-bottom -->
	<div class="banner-bottom">
		<div class="container">
			<div class="col-md-2 w3_agileits_banner_bottom_grid">
				<h2>Karate</h2>
			</div>
			<div class="col-md-10 w3_agileits_banner_bottom_grid_left">
				<ul id="flexiselDemo1">	
					<li>
						<div class="agileits_w3layouts_banner_bottom_grid">
							<div class="wthree_banner_bottom_grid1">
								<img src="shangwu/portals/images/1.jpg" alt=" " class="img-responsive" />
								<div class="agileinfo_banner_bottom_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
							<h4><a href="#" data-toggle="modal" data-target="#myModal">Vivamus et dolor pulvinar, iaculis diam.</a></h4>
						</div>
					</li>
					<li>
						<div class="agileits_w3layouts_banner_bottom_grid">
							<div class="wthree_banner_bottom_grid1">
								<img src="shangwu/portals/images/2.jpg" alt=" " class="img-responsive" />
								<div class="agileinfo_banner_bottom_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
							<h4><a href="#" data-toggle="modal" data-target="#myModal">Maecenas semper augue accumsan rutrum.</a></h4>
						</div>
					</li>
					<li>
						<div class="agileits_w3layouts_banner_bottom_grid">
							<div class="wthree_banner_bottom_grid1">
								<img src="shangwu/portals/images/4.jpg" alt=" " class="img-responsive" />
								<div class="agileinfo_banner_bottom_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
							<h4><a href="#" data-toggle="modal" data-target="#myModal">In nunc purus, luctus in venenatis.</a></h4>
						</div>
					</li>
					<li>
						<div class="agileits_w3layouts_banner_bottom_grid">
							<div class="wthree_banner_bottom_grid1">
								<img src="shangwu/portals/images/3.jpg" alt=" " class="img-responsive" />
								<div class="agileinfo_banner_bottom_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
							<h4><a href="#" data-toggle="modal" data-target="#myModal">habitant morbi tristique senectus diam.</a></h4>
						</div>
					</li>
					<li>
						<div class="agileits_w3layouts_banner_bottom_grid">
							<div class="wthree_banner_bottom_grid1">
								<img src="shangwu/portals/images/5.jpg" alt=" " class="img-responsive" />
								<div class="agileinfo_banner_bottom_grid1_pos">
									<h3>The Karate</h3>
									<p>Donec vitae hendrerit faucibus.</p>
								</div>
							</div>
							<h4><a href="#" data-toggle="modal" data-target="#myModal">Maecenas dictum honcus scelerisque.</a></h4>
						</div>
					</li>
				</ul>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
<!-- //banner-bottom -->
<div class="tlinks">Collect from <a href="http://www.cssmoban.com/" >网页模板</a></div>
<!-- about -->
	<div class="about">
		<div class="container">
			<div class="col-md-6 agileits_about_grid_left">
				
			</div>
			<div class="col-md-6 agileits_about_grid_right">
				<h3>about <span>Karate</span></h3>
				<h4>Pellentesque habitant morbi tristique senectus</h4>
				<p><i>In non dui auctor sapien accumsan malesuada. Donec nibh eros, 
					placerat facilisis ultricies et, sagittis vitae metus.</i> Integer aliquam faucibus sapien, 
					ultricies posuere nisl euismod ut. Nulla eget mauris orci. Duis dictum, justo a sodales porta, 
					justo dui dapibus purus, mattis vehicula dui nibh eu felis.</p>
			</div>
			<div class="clearfix"> </div>
		</div>
		<div class="w3ls_about_bottom">
			<div class="container">
				<div class="w3layouts_about_grid_left_pos">
					<img src="shangwu/portals/images/1.png" alt=" " class="img-responsive" />
				</div>
				<div class="w3_about_bottom_grid">
					<h3><span>Martial Arts</span> is not about fighting; it's about building character</h3>
					<div class="link-effect-2" id="link-effect-2">
						<a href="#" class="w3l_more" data-toggle="modal" data-target="#myModal"><span data-hover="Learn More">Learn More</span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- //about -->
<!-- events -->
	<div class="events">
		<div class="container">
			<div class="w3layouts_events_grids">
				<div class="col-md-6 w3_events_grid_left">
					<h3>be confident</h3>
					<h4><a href="#" data-toggle="modal" data-target="#myModal">Integer aliquam faucibus</a></h4>
					<p>In non dui auctor sapien accumsan malesuada. Donec nibh eros, placerat 
						facilisis ultricies et, sagittis vitae metus. Integer aliquam faucibus sapien, 
						ultricies posuere nisl euismod ut.</p>
					<ul>
						<li><a href="#"><i class="fa fa-comments-o" aria-hidden="true"></i>5 Comments</a></li>
						<li><a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i>10 Likes</a></li>
						<li><a href="#"><i class="fa fa-share" aria-hidden="true"></i>5 Shares</a></li>
					</ul>
				</div>
				<div class="col-md-6 w3_events_grid_right">
					<img src="shangwu/portals/images/2.jpg" alt=" " class="img-responsive" />
					<div class="w3_events_grid_right_pos">
						<h3>The Karate</h3>
						<p>Donec vitae hendrerit faucibus.Vivamus sodales orci felis, vel iaculis est 
							dapibus eu. Pellentesque habitant morbi tristique senectus et netus et malesuada 
							fames ac turpis egestas.</p>
					</div>
					<div class="w3_agile_grid_right_grid_pos">
						<h3>01/3/2017</h3>
					</div>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="w3layouts_events_grids">
				<div class="col-md-6 w3_events_grid_right">
					<img src="shangwu/portals/images/5.jpg" alt=" " class="img-responsive" />
					<div class="w3_events_grid_right_pos">
						<h3>The Karate</h3>
						<p>Donec vitae hendrerit faucibus.Vivamus sodales orci felis, vel iaculis est 
							dapibus eu. Pellentesque habitant morbi tristique senectus et netus et malesuada 
							fames ac turpis egestas.</p>
					</div>
					<div class="w3_agile_grid_right_grid_pos w3_events_grid_right_second">
						<h3>02/3/2017</h3>
					</div>
				</div>
				<div class="col-md-6 w3_events_grid_left">
					<h3>be Focus</h3>
					<h4><a href="#" data-toggle="modal" data-target="#myModal">Vivamus egestas commodo</a></h4>
					<p>In non dui auctor sapien accumsan malesuada. Donec nibh eros, placerat 
						facilisis ultricies et, sagittis vitae metus. Integer aliquam faucibus sapien, 
						ultricies posuere nisl euismod ut.</p>
					<ul>
						<li><a href="#"><i class="fa fa-comments-o" aria-hidden="true"></i>8 Comments</a></li>
						<li><a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i>12 Likes</a></li>
						<li><a href="#"><i class="fa fa-share" aria-hidden="true"></i>2 Shares</a></li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
<!-- //events -->
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
						<img src="shangwu/portals/images/4.jpg" alt=" " class="img-responsive" />
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
<!-- flexSlider -->
	<script defer src="shangwu/portals/js/jquery.flexslider.js"></script>
	<script type="text/javascript">
		$(window).load(function(){
		  $('.flexslider').flexslider({
			animation: "slide",
			start: function(slider){
			  $('body').removeClass('loading');
			}
		  });
		});
  </script>
<!-- //flexSlider -->
<!-- flexisel -->
		<script type="text/javascript">
		$(window).load(function() {
		    //给当前标题加上样式
			$("#index").addClass("menu__item--current");
			$("#flexiselDemo1").flexisel({
				visibleItems: 4,
				animationSpeed: 1000,
				autoPlay: true,
				autoPlaySpeed: 3000,    		
				pauseOnHover: true,
				enableResponsiveBreakpoints: true,
				responsiveBreakpoints: { 
					portrait: { 
						changePoint:480,
						visibleItems: 1
					}, 
					landscape: { 
						changePoint:640,
						visibleItems:2
					},
					tablet: { 
						changePoint:768,
						visibleItems: 2
					}
				}
			});
			//回到顶部的功能
            $().UItoTop({ easingType: 'easeOutQuart' });
		});
	</script>
	<script type="text/javascript" src="shangwu/portals/js/jquery.flexisel.js"></script>
<!-- //flexisel -->
<!-- for bootstrap working -->
	<script src="shangwu/portals/js/bootstrap.js"></script>
<!-- //for bootstrap working -->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="shangwu/portals/js/move-top.js"></script>
<script type="text/javascript" src="shangwu/portals/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
</body>
</html>