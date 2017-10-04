<div class="navbar navbar-inverse">
	<div class="navbar-inner">
		<div class="container-fluid">
			<div style="float:left; width:70px; margin: 12px 5px 0 0;"><img src="${basePath}/letv_common/images/logo.png"></div>
			<a class="brand" style="padding:2px 20px 0;">
				<h2 style="margin: 0; font-size: 0.9em;">全球版权系统</h2>
			</a>

			<ul class="nav ace-nav pull-right" style="margin-right: -20px;">
				<li class="no-border margin-1 white-pink" style="background: transparent; margin-right:10px;">
					<div class="header-topp" style="background: url(/letv-gcr-webapp/letv_common/images/iconfont-rili.png) no-repeat; color: white; width: 70px;height: 24px;line-height: 28px;margin: 10px 0 0 0;padding-left: 30px;-webkit-background-size: contain; -moz-background-size: contain; background-size: contain;">
						<fmt:formatDate value="${curDate }" pattern="yyyy-MM-dd" />
					</div>
				</li>
			
				<li class="light-blue user-profile">
					<a class="user-menu dropdown-toggle" href="javascript:;" data-toggle="dropdown"> 
						<img alt="FH" src="${basePath}/letv_common/static/avatars/user.jpg" class="nav-user-photo" /> <span id="user_info"> </span> <i class="icon-caret-down"></i>
					</a>
					<ul id="user_menu" class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
						<li><a onclick="editUserH();" style="cursor: pointer;"><i class="icon-user"></i> 修改资料</a></li>
						<li id="systemset"><a onclick="editSys();" style="cursor: pointer;"><i class="icon-cog"></i> 系统设置</a></li>
						<li class="divider"></li>
						<li><a href="${basePath}/systemAction/j_spring_security_logout"><i class="icon-off"></i> 退出</a></li>
					</ul></li>
			</ul>
			<!--/.ace-nav-->
		</div>
		<!--/.container-fluid-->
	</div>
	<!--/.navbar-inner-->
</div>
<!--/.navbar-->


<!--引入属于此页面的js -->
<script type="text/javascript" src="${basePath}/letv_common/main/js/top.js"></script>
