<%
	String pathl = request.getContextPath();
	String basePathl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathl+"/";
%>
<!-- 本页面涉及的js函数，都在top.jsp页面中     -->
<div id="sidebar" class="menu-min">
	<ul class="nav nav-list">
		<li class="active" id="fhindex">
		  <a href="<%=basePath%>mainAction/main.action"><i class="icon-dashboard"></i><span><spring:message code="Homepage" /><!-- 后台首页 --></span></a>
		</li>
		<c:forEach items="${menus}" var="menu">
			<c:if test="${not empty menu.children}">
			<li id="lm${menu.id }">
				<a style="cursor:pointer;" class="dropdown-toggle" >
					<i class="${menu.icon }"></i>
					<span>${menu.name }</span>
					<b class="arrow icon-angle-down"></b>
				</a>
				<ul class="submenu">
					<c:forEach items="${menu.children}" var="sub">
						<c:choose>
							<c:when test="${not empty sub.module.path}">
								<li id="z${sub.id }">
									<a style="cursor:pointer;" target="mainFrame"  onclick="siMenu('z${sub.id }','lm${menu.id }','${sub.name }','${sub.module.path }')">
										<i class="icon-double-angle-right"></i>
										<span>${sub.name }</span>
									</a>
								</li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:void(0);"><i class="icon-double-angle-right"></i><span>${sub.name }</span></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			  	</ul>
			</li>
			</c:if>
		</c:forEach>
	</ul>
	<!--/.nav-list-->

	<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>
</div><!--/#sidebar-->