<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"   isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="shortcut icon" type="image/x-icon" href="${basePath}/web_common/images/favicon-new.ico" media="screen" />
<script type="text/javascript" src="${basePath}/common/js/jquery/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.validate.js"></script>
<c:if test="${locale.language eq 'zh' }">
<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.validate.message_cn.js"></script>
<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.validate.rules.js"></script>
</c:if>
<c:if test="${locale.language eq 'en' }">
<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.validate.message_us.js"></script> 
<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.validate.rules_us.js"></script>
</c:if>

<script type="text/javascript" src="${basePath}/common/js/jquery/jquery.blockUI.js"></script>
<script type="text/javascript" src="${basePath}/common/js/ROOF.Utils.js"></script>
<script type="text/javascript" src="${basePath}/common/js/ROOF.Class.js"></script>
<script type="text/javascript" src="${basePath }/web_common/js/Letv.SelectableTable.js"></script>
<script type="text/javascript" src="${basePath }/web_common/js/Letv.validate.js"></script>
<!-- <script type="text/javascript" src="http://7xjt6w.dl1.z0.glb.clouddn.com/sinopec-sc-webapp/js/open-modal.min.js"></script> -->
<script type="text/javascript" src="${basePath }/web_common/js/open-modal.min.js"></script>

<script type="text/javascript" src="${basePath }/common/js/jquery_ui/ui/jquery-ui.min.js"></script>
<link rel="stylesheet" href="${basePath}/common/js/jquery_ui/themes/cupertino/jquery-ui.min.css" />

<c:if test="${locale.language eq 'zh' }">
<script type="text/javascript" src="${basePath }/common/js/jquery_ui/ui/i18n/jquery.ui.datepicker-zh-CN.min.js"></script>
</c:if>
<script type="text/javascript" src="${basePath }/common/js/jquery_timepicker_addon/jquery-ui-timepicker-addon.min.js"></script>
<script type="text/javascript" src="${basePath }/common/js/jquery_timepicker_addon/jquery-ui-timepicker-zh-CN.js"></script>
<script type="text/javascript" src="${basePath }/common/js/jquery_timepicker_addon/jquery-ui-sliderAccess.js"></script>
<script type="text/javascript" src="${basePath }/web_common/js/com.letv.errorpacement.js"></script>
<script type="text/javascript" src="${basePath }/web_common/js/com.letv.PageBar.js"></script>
<script type="text/javascript" src="${basePath }/web_common/plugins/fileinput-master/js/fileinput.js" ></script>
<script type="text/javascript" src="${basePath }/web_common/plugins/fileinput-master/js/fileinput_locale_zh.js" ></script>

<script type="text/javascript" src="${basePath}/web_common/plugins/fullcalendar-2.2.7/lib/moment.min.js"></script>
<script type="text/javascript" src="${basePath}/web_common/plugins/fullcalendar-2.2.7/lib/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${basePath}/web_common/plugins/fullcalendar-2.2.7/fullcalendar.js"></script>
<c:if test="${locale.language eq 'zh' }">
<script type="text/javascript" src="${basePath}/web_common/plugins/fullcalendar-2.2.7/lang/zh-cn.js"></script>
</c:if>
<link href='${basePath}/web_common/plugins/fullcalendar-2.2.7/fullcalendar.css' rel='stylesheet' />
<link href='${basePath}/web_common/plugins/fullcalendar-2.2.7/fullcalendar.print.css' rel='stylesheet' media='print' />
<link rel="stylesheet" href="${basePath}/common/js/jquery_timepicker_addon/jquery-ui-timepicker-addon.min.css" />

<script type="text/javascript" src="${basePath }/web_common/js/com.letv.CheckboxValidate.js"></script>

<link rel="stylesheet" href="${basePath }/web_common/css/vendor.css">
<link rel="stylesheet" href="${basePath }/web_common/bootstrap/css/bootstrap-theme.css">
<link rel="stylesheet" href="${basePath }/web_common/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="${basePath }/web_common/css/app.css">
<link rel="stylesheet" href="${basePath }/web_common/plugins/fileinput-master/css/fileinput.css" media="all" rel="stylesheet" type="text/css">



<link rel="stylesheet" type="text/css" href="${basePath}/web_common/css/error.css" />
<link rel="stylesheet" href="${basePath}/web_common/css/hint/hint.css" />
<!--[if lt IE 7]>
  <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
  <![endif]-->

<link rel="stylesheet" href="${basePath}/web_common/plugins/uploadifive/uploadifive.css" type="text/css"></link>
<script type="text/javascript" src="${basePath}/web_common/plugins/uploadifive/jquery.uploadifive.js"></script>



<script type="text/javascript">
var basePathConst = "${basePath }";
function reloadFun(){
	//window.opener.location.reload();
	window.parent.location.reload();
	window.close();
}

</script>