<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<nav style="padding: 0 15px;">
	<ul class="pagination pagination-sm" style="float: right;">

		<li><a href="javascript:void(0)" name="page_first_page"><!-- 首页 --><spring:message code="First_Page" /></a></li>

		<c:choose>
			<c:when test="${page.currentPage > 1 }">
				<li><a id="pre_page_li" href="javascript:void(0)"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:when>
			<c:otherwise>
				<li class="disabled"><a href="javascript:void(0)"
					aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
				</a></li>
			</c:otherwise>
		</c:choose>

	<c:forEach var="a" begin="${pageStart }" end="${pageEnd }">
		<c:choose>
			<c:when test="${a== page.currentPage}">
				<li class="active"><a href="javascript:void(0)"
					name="cur_page_li">${a }</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="javascript:void(0)" name="page_li">${a }</a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>

		<c:choose>
			<c:when test="${page.currentPage < page.pageCount }">
				<li><a id="next_page_li" href="javascript:void(0)"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:when>
			<c:otherwise>
				<li class="disabled"><a href="javascript:void(0)"
					aria-label="Next"> <span aria-hidden="true">&raquo;</span>
				</a></li>
			</c:otherwise>
		</c:choose>

	<li><a href="javascript:void(0)" name="page_end_page" data = "${page.pageCount }" ><!-- 尾页 --><spring:message code="Last_Page" /></a></li>
	
	</ul>
</nav>