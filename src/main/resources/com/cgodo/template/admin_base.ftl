[#include "/common/core.ftl" /]
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<title>后台管理系统</title>

<meta name="description" content="overview &amp; stats" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" type="text/css" href="[@s.url '/res/rms/styles/inside.css'/]"
	media="all" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="[@s.url '/res/rms/styles/bootstrap.css'/]" />
<link rel="stylesheet" href="[@s.url '/res/rms/styles/font-awesome.css'/]" />
<link rel="stylesheet" href="[@s.url '/res/rms/styles/jquery-ui.custom.css'/]" />
<link rel="stylesheet" href="[@s.url '/res/rms/styles/chosen.css'/]" />
<link rel="stylesheet" href="[@s.url '/res/rms/styles/bootstrap-datepicker3.css'/]" />
<link rel="stylesheet" href="[@s.url '/res/rms/styles/bootstrap-timepicker.css'/]" />
<link rel="stylesheet" href="[@s.url '/res/rms/styles/daterangepicker.css'/]" />
<link rel="stylesheet"
	href="[@s.url '/res/rms/styles/bootstrap-datetimepicker.css'/]" />
<link rel="stylesheet" href="[@s.url '/res/rms/styles/colorpicker.css'/]" />

<!-- page specific plugin styles -->

<!-- text fonts -->
<link rel="stylesheet" href="[@s.url '/res/rms/styles/ace-fonts.css'/]" />

<!--[if lte IE 9]>
	<link rel="stylesheet" href="/res/rms/styles/ace-part2.css" />
<![endif]-->
<link rel="stylesheet" href="[@s.url '/res/rms/styles/ace-skins.css'/]" />
<link rel="stylesheet" href="[@s.url '/res/rms/styles/ace-rtl.css'/]" />
<!--[if lte IE 9]>
	<link rel="stylesheet" href="[@s.url '/res/rms/styles/ace-ie.css'/]" />
<![endif]-->

[@override overrideName="css"] [/@override]

<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src='[@s.url "/res/rms/javascripts/jquery.min.js"/]'></script>
<script src="[@s.url '/res/rms/javascripts/ace-extra.js'/]"></script>
[@override overrideName="head"] [/@override]

<!-- ace styles -->
<link rel="stylesheet" href="[@s.url '/res/rms/styles/ace.css'/]" />
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lte IE 8]>
	<script src="[@s.url '/res/rms/javascripts/html5shiv.js'/]"></script>
	<script src="[@s.url '/res/rms/javascripts/respond.js'/]"></script>
<![endif]-->

<style type="text/css">
<!--
.maroon {
	color: #f30;
	margin-left: 5px;
}

-->
.modal {
	display: none;
	overflow: hidden;
	position: fixed;
	top: 30%;
	right: 0;
	bottom: 0;
	left: 0;
	z-index: 1050;
	-webkit-overflow-scrolling: touch;
	outline: 0;
}
</style>
</head>

<body class="no-skin">
	<!-- #section:basics/navbar.layout -->
	<div id="navbar" class="navbar navbar-default">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<!-- #section:basics/sidebar.mobile.toggle -->
			<button type="button" class="navbar-toggle menu-toggler pull-left"
				id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">Toggle sidebar</span> <span class="icon-bar"></span>

				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>

			<!-- /section:basics/sidebar.mobile.toggle -->
			<div class="navbar-header pull-left">
				<!-- #section:basics/navbar.layout.brand -->
				<a href="#" class="navbar-brand"> <small> <i
						class="fa fa-leaf"></i>  后台管理系统
				</small>
				</a>
				
				<!-- /section:basics/navbar.layout.brand -->
				<!-- #section:basics/navbar.toggle -->
				<!-- /section:basics/navbar.toggle -->
			</div>

			<!-- #section:basics/navbar.dropdown -->
			<div class="navbar-buttons navbar-header pull-right"
				role="navigation">
				<ul class="nav ace-nav">
					<li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="ace-icon fa fa-tasks"></i>
								<span class="badge badge-grey"></span>
							</a>
					</li>
					<!-- #section:basics/navbar.user_menu -->
					<li class="light-blue">
						<a data-toggle="dropdown" href="#"
							class="dropdown-toggle"> <span class="user-info"> <small>欢迎,${r"${user.userName!}"}</small>
									<span id="J_QueryName" username='admin' ></span>
							</span> <i class="ace-icon fa fa-caret-down"></i>
						</a>

						<ul
							class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li><a href="[@s.url '/admin/member/update_password.html'/]"> <i class="ace-icon fa fa-cog"></i> 密码修改
							</a></li>
							<li><a href="[@s.url '/admin/logout.html'/]"> <i
									class="ace-icon fa fa-power-off"></i> 退出
							</a></li>
						</ul>
					</li>
					<!-- /section:basics/navbar.user_menu -->
				</ul>
			</div>

			<!-- /section:basics/navbar.dropdown -->
		</div>
		<!-- /.navbar-container -->
	</div>

	<!-- /section:basics/navbar.layout -->
	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<!-- #section:basics/sidebar -->
		<div id="sidebar" class="sidebar   responsive ace-save-state">
			<script type="text/javascript">
					try{ace.settings.loadState('sidebar')}catch(e){}
			</script>


			<ul class="nav nav-list" >
				<li class="[#if active=='首页']active[/#if]"><a
					href="[@s.url '/admin/index.html'/]"> <i
						class="menu-icon fa fa-tachometer"></i> <span class="menu-text">后台首页
					</span>
				</a>
				<#list database?keys as table>
				</li>
				<li class="hsub [#if active=='${table.remark}管理']active open[/#if]">
					<a href="[@s.url '/admin/${table.tableName}/list.html'/]" class=""> <i
						class="menu-icon fa fa-tag"></i> <span class="menu-text">${table.remark}管理</span>
					</a> 
				</li>
				</#list>
			</ul>
			<script type="text/javascript">
				  $(".submenu").each(function(index,element){
					  if($(element).find('li').length==0){
							$(element).parents('.hsub').remove();
					  }
				  });
			</script>
			<!-- /.nav-list -->

			<!-- #section:basics/sidebar.layout.minimize -->
			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i class="ace-icon fa fa-angle-double-left"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>

			<!-- /section:basics/sidebar.layout.minimize -->
			<script type="text/javascript">
				try {
					ace.settings.check('sidebar', 'collapsed')
				} catch (e) {
				}
			</script>
		</div>

		<!-- /section:basics/sidebar -->
		<div class="main-content">[@override overrideName="main"]

			[/@override]</div>
		<div class="footer">
			<div class="footer-inner">
				<!-- #section:basics/footer -->
				<div class="footer-content">
					<span class="bigger-120"> <span class="blue bolder">上饶市螺丝钉网络科技有限公司</span>
						Application &copy; 2017
					</span> &nbsp; &nbsp; 
					
					[#--]<span class="action-buttons"> <a href="#">
							<i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
					</a> <a href="#"> <i
							class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
					</a> <a href="#"> <i
							class="ace-icon fa fa-rss-square orange bigger-150"></i>
					</a>[--]
					</span>
				</div>

				<!-- /section:basics/footer -->
			</div>
		</div>

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	
	
	<!-- /.main-container -->
	<script src="[@s.url '/res/rms/javascripts/date-time/bootstrap-datepicker.js'/]"></script>
	<script src="[@s.url '/res/rms/javascripts/date-time/bootstrap-timepicker.js'/]"></script>
	<script src="[@s.url '/res/rms/javascripts/date-time/moment.js'/]"></script>
	<script src="[@s.url '/res/rms/javascripts/date-time/daterangepicker.js'/]"></script>
	<script
		src="[@s.url '/res/rms/javascripts/date-time/bootstrap-datetimepicker.js'/]"></script>
	<script src="[@s.url '/res/rms/javascripts/bootstrap-colorpicker.js'/]"></script>

	<script src="[@s.url '/res/rms/javascripts/bootstrap.min.js'/]"></script>
	<script type="text/javascript"
		src="[@s.url '/res/rms/javascripts/jquery_validate_min.js'/]"></script>
	<script type="text/javascript"
		src="[@s.url '/res/rms/javascripts/jquery_validate_methods.js'/]"></script>
	<script type="text/javascript"
		src="[@s.url '/res/rms/javascripts/jquery_form_min.js'/]"></script>
	<script src="[@s.url '/res/rms/javascripts/bootbox.js'/]"></script>
	<!-- page specific plugin scripts -->

	<!--[if lte IE 8]>
		  <script src="[@s.url '/res/rms/javascripts/excanvas.js'/]"></script>
		<![endif]-->
	<script type="text/javascript" src="[@s.url '/res/rms/javascripts/inside.js'/]"></script>
	<!-- ace scripts -->
	<script src="[@s.url '/res/rms/javascripts/ace-elements.js'/]"></script>
	<script src="[@s.url '/res/rms/javascripts/ace.js'/]"></script>
	[@override overrideName="javascript"] [/@override]
</body>
</html>
