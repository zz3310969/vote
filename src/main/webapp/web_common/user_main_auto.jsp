<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8"/>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"/>
<script type="text/javascript"
	src="${basePath}/common/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript"
	src="${basePath}/web_common/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${basePath}/web_common/bootstrap/css/bootstrap.min.css"/>
<script type="text/javascript">
	$(function() {
		var h = $(window).height() - 65;
		$("#_mainFrame").attr("height", h);
	});
</script>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<ul class="nav navbar-nav">
				<c:forEach var="m" items="${menus }">
					<c:choose>
						<c:when test="${empty m.children  }">
							<c:if test="${empty m.module.path }">
								<%-- 								<li><a target="${m.target }" href="javascript:void(0)">${m.name }</a></li>
 --%>
							</c:if>
							<c:if test="${!empty m.module.path }">
								<li><a target="${m.target }"
									href="${basePath }${m.module.path}.action">${m.name }</a></li>
							</c:if>
						</c:when>
						<c:otherwise>
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown" role="button" aria-haspopup="true"
								aria-expanded="false">${m.name }<span class="caret"></span></a>
								<ul class="dropdown-menu">
									<c:forEach var="sm" items="${m.children }">
										<c:if test="${empty sm.module.path }">
											<li><a target="${sm.target }" href="javascript:void(0)">${sm.name }</a></li>
										</c:if>
										<c:if test="${!empty sm.module.path }">
											<li><a target="${sm.target }"
												href="${basePath }${sm.module.path}.action">${sm.name }</a></li>
										</c:if>
									</c:forEach>
								</ul></li>
						</c:otherwise>
					</c:choose>

				</c:forEach>
				<li><a target="_top"
					href="${basePath}/systemAction/j_spring_security_logout">登出</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<iframe src="" width="100%" id="_mainFrame" name="_mainFrame"
		frameborder="0" scrolling="yes"
		style="padding-left: 0; padding-right: 0"></iframe>
</body>
</html>