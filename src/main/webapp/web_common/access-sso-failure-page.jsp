<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="images/favicon-new.ico" mce_href="images/favicon-new.ico" rel="icon">
<link rel="shortcut icon" type="image/x-icon" href="${basePath}/ems_common/images/favicon-new.ico" media="screen" />
<style type="text/css">
	.no-data {
	    min-height: 190px;
	    padding: 50px;
	    border: 1px solid #E6E9ED;
	    background: #fff;
	    color: #656D78;
	    font-size: 14px;
	    line-height: 2;
	    text-align: center;
		margin: 0 auto;
	}
	.no-data img {
	    width: 97px;
	    height: 97px;
	    display: block;
	    margin: auto;
	}
</style>
<title><spring:message code="access_denied" /><!-- 访问被拒绝 --></title>
<script type="text/javascript" src="<%=basePath %>/ems_common/static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=basePath %>/ems_common/main/js/logout.js"></script>
</head>
<body>

	<div class="no-data">
		<img src="../common/images/no-data.png" alt="…">
		<p><spring:message code="login_no_permission" /><!-- 对不起,您无权登录系统 --> &nbsp; <a id="log_out" href="javascript:void(0)" lang="<%=basePath %>/systemAction/j_spring_security_logout"><spring:message code="Return" /><!-- 返回 --></a></p>
	</div>
</body>
</html>