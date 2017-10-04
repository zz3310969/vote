<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- <input type="hidden" name="limit" value="2" /> -->
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage }" />
<script>
function search(){
	$("#currentPage").val(1);
	$("form").submit();
}
</script>