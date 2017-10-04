<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta charset="utf-8" />
<title>${pd.SYSNAME}</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="favicon-new.ico" mce_href="favicon-new.ico" rel="icon">

<!-- basic styles -->
<link href="${basePath}/web_common/static/css/bootstrap.min.css" rel="stylesheet" />
<link href="${basePath}/web_common/static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${basePath}/web_common/static/css/font-awesome.min.css" />
<link rel="shortcut icon" type="image/x-icon" href="${basePath}/web_common/images/favicon-new.ico" media="screen" />

<!-- page specific plugin styles -->
<!-- 下拉框-->
<link rel="stylesheet" href="${basePath}/web_common/static/css/chosen.css" />

<!-- ace styles -->
<link rel="stylesheet" href="${basePath}/web_common/static/css/ace.min.css" />
<link rel="stylesheet" href="${basePath}/web_common/static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="${basePath}/web_common/static/css/ace-skins.min.css" />
<script type="text/javascript" src="${basePath}/web_common/static/js/jquery-1.7.2.js"></script>
<link rel="stylesheet" href="${basePath}/web_common/static/css/datepicker.css" /><!-- 日期框 -->

<!-- 引入弹窗组件start -->
<script type="text/javascript" src="${basePath}/web_common/plugins/attention/zDialog/zDrag.js"></script>
<script type="text/javascript" src="${basePath}/web_common/plugins/attention/zDialog/zDialog.js"></script>
<!-- 引入弹窗组件end -->

<script type="text/javascript" src="${basePath}/web_common/static/js/jquery.tips.js"></script>

<!--乐视TV样式-->
<link rel="stylesheet" href="${basePath }/web_common/css/app.css">
<script type="text/javascript">
document.title = '<spring:message code="iot" />';

</script>