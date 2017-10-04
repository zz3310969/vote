<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"   isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="shortcut icon" type="image/x-icon" href="${basePath}/web_common/images/favicon-new.ico" media="screen" />

<script type="text/javascript" src="${basePath}/common/js/jquery/jquery-1.7.2.min.js"></script>

<script type="text/javascript" src="${basePath }/common/js/jquery_ui/ui/jquery-ui.min.js"></script>


<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.validate.js"></script>

<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.validate.message_cn.js"></script>
<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.validate.rules.js"></script>
<script type="text/javascript" src="${basePath}/web_common/static/js/jquery.cookie.js"></script>


<script type="text/javascript" src="${basePath}/common/js/ROOF.Utils.js"></script>
<script type="text/javascript" src="${basePath}/common/js/ROOF.Class.js"></script>
<script type="text/javascript" src="${basePath }/web_common/js/com.letv.errorpacement.js"></script>
<script type="text/javascript" src="${basePath }/web_common/js/com.letv.PageBar.js"></script>
<script type="text/javascript" src="${basePath }/web_common/js/Letv.SelectableTable.js"></script>



<link rel="stylesheet" type="text/css" href="${basePath}/web_common/css/error.css" />
<link rel="stylesheet" href="${basePath }/web_common/css/style.css">




<script type="text/javascript">
$(function() {
	$(".btn_login").click( function () { 
		window.location.href =  "${basePath}/open/openUserAction/goLogin.action";
		});
	$(".btn_kfz").click( function () {
		window.location.href =  "${basePath}/developmentAction/main.action";
	});
});
</script>