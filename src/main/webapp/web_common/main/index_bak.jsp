<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">

	<!-- jsp文件头和头部 -->
	<%@ include file="head.jsp"%>
	<script type="text/javascript">
	$(document).ready(function() {
		$('iframe').height(jQuery(window).height() - 100);
	});
	
</script>
</head>
<body>
	<!-- 页面顶部¨ -->
	<%@ include file="top.jsp"%>
	<div id="websocket_button"></div>
	<div class="container-fluid" id="main-container">
		<a href="#" id="menu-toggler"><span></span></a>
		<!-- menu toggler -->

		<!-- 左侧菜单 -->
		<%@ include file="left.jsp"%>

		<div id="main-content" class="clearfix">
			<div>
				<iframe name="mainFrame" id="mainFrame" frameborder="0" src="<%=basePath%>mainAction/tab.action" style="margin:0 auto;width:100%;height:100%;"></iframe>
			</div>

			<!-- 换肤 -->
			<div id="ace-settings-container">
				<div class="btn btn-app btn-mini btn-warning" id="ace-settings-btn">
					<i class="icon-cog"></i>
				</div>
				<div id="ace-settings-box">
					<div>
						<div class="pull-left">
							<select id="skin-colorpicker" class="hidden">
								<option data-class="default" value="#438EB9">#438EB9</option>
								<option data-class="skin-1" value="#222A2D">#222A2D</option>
								<option data-class="skin-2" value="#C6487E">#C6487E</option>
								<option data-class="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; <!-- 选择皮肤 --><spring:message code="Select_skin" /></span>
					</div>
					<div>
						<label><input type='checkbox' name='menusf' id="menusf" onclick="menusf();" /><span class="lbl" style="padding-top: 5px;"><spring:message code="Menu_zoom" /><!-- 菜单缩放 --></span></label>
					</div>
				</div>
			</div>
			<!--/#ace-settings-container-->

		</div>
		<!-- #main-content -->
	</div>
	<!--/.fluid-container#main-container-->

	<!-- basic scripts -->
	<!-- 引入 -->
	<script type="text/javascript">window.jQuery || document.write("<script src='web_common/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
	<script src="${basePath}/web_common/static/js/bootstrap.min.js"></script>
	<script src="${basePath}/web_common/static/js/ace-elements.min.js"></script>
	<script src="${basePath}/web_common/static/js/ace.min.js"></script>
	<!-- 引入 -->

	<script type="text/javascript" src="${basePath}/web_common/static/js/jquery.cookie.js"></script>

	<!--引入属于此页面的js -->
	<script type="text/javascript" src="${basePath}/web_common/main/js/menusf.js"></script>
	<script type="text/javascript" src="${basePath}/web_common/main/js/index.js"></script>
	 <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>  
      <script type="text/javascript" src="${basePath}/common/js/ROOF.Utils.js"></script>
<%-- 		<script type="text/javascript" src="${basePath}/web_common/main/js/websocket.js"></script>
 --%>	
</body>
</html>
