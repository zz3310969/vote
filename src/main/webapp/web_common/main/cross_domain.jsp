<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String type = request.getParameter("type");
	String menuName = request.getParameter("menuName");
%>
<script type="text/javascript">
	if ('<%=type%>' == 'change_tab') {
		top.openTabByMenuName('<%=menuName%>', true);
	}
</script>