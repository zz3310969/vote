<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="navbar navbar-inverse" style="position:relative; padding-top:30px;">
	<!-- 头部 -->
	<div class="header-topp" style="z-index:9999; position:absolute;top:0;left:0; background:#333;">
		<div class="row">
			<div class="col-md-12">
				<div class="quit">
					<em class="icon-quit"></em><a id="log_out"
						href="javascript:void(0)" lang="${basePath}/systemAction/j_spring_security_logout"><spring:message code="Log_Out" /><!-- 安全退出 --></a>
				</div>
				<div class="welcome">
					<em class="icon-user"></em><spring:message code="Welocome" /><!-- 欢迎您 -->，${user.name }！
				</div>
				<div class="time">
					<em class="icon-calendar"></em>
					<fmt:formatDate value="${curDate }" pattern="yyyy-MM-dd" />
				</div>
				<div class="version">
					<c:if test="${locale.language eq 'zh' }">
						<a href="${basePath }/mainAction/main.action?locale=en_US">English</a>
					</c:if>
					<c:if test="${locale.language eq 'en' }">
					<a href="${basePath }/mainAction/main.action?locale=zh_CN">中文</a> 
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<div class="row logo">
		<div class="col-md-12">
			<div class="icon-logo">
				<c:if test="${locale.language eq 'zh' }">
					<h2 style="margin: 10px 0 0 30px;"><spring:message code="iot" /></h2>
				</c:if>
				<c:if test="${locale.language eq 'en' }">
					<h2 style="margin: 10px 0 0 30px;width: 400px;"><spring:message code="iot" /></h2>
				</c:if>
			</div>
		</div>
	</div>
</div>
<input type="hidden" id="no_need_close_tabs" value="Drafts,我的草稿箱">
<!--引入属于此页面的js -->
<script type="text/javascript" src="${basePath}/web_common/main/js/top.js"></script>
<script type="text/javascript" src="${basePath}/web_common/main/js/logout.js"></script>
