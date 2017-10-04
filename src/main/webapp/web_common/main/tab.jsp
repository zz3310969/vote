<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String m_tk = (String)request.getSession().getAttribute("m_tk");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<script type="text/javascript" src="${basePath}/web_common/static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${basePath}/web_common/static/js/jquery.cookie.js"></script>
		<script type="text/javascript" src="${basePath}/web_common/plugins/tab/js/framework.js"></script>
		<link href="${basePath}/web_common/plugins/tab/css/import_basic.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" id="skin" prePath="${basePath}/web_common/plugins/tab/" />
		<!--默认相对于根目录路径为../，可添加prePath属性自定义相对路径，如prePath="<%=request.getContextPath()%>"-->
		<script type="text/javascript" charset="utf-8" src="${basePath}/web_common/main/js/tab.js"></script>
	</head>
	<body>
		<div id="tab_menu" style="height: 30px;"></div>
		<div style="width: 100%;">
			<div id="page" style="width: 100%; height: 100%;"></div>
			<sec:authorize url="${basePath }/letv/gcr/copyright_hold_selectAction/list">
				<input type="hidden" id=index_en name=index_en_name value="<spring:message code="Index" />">
			</sec:authorize>
		</div>
	</body>
	<script type="text/javascript">
		var tab;
		function tabAddHandler(mid,mtitle,murl){
			var path = "<%=path%>";
			var end_ = ".action"
			if(murl.indexOf("http://")!=-1){
				path = "";
				end_ = "";
			}
			var url = path + murl + end_;
			url = buildUrl(url);
			tab.add({
				id: mid,
				title: mtitle,
				url: url,
				isClosed: true
			});
			tab.update({
				id: mid,
				title: mtitle,
				url: url,
				isClosed: true
			});
			tab.activate(mid);
		}

		//调用不带action的url
		function tabAddNoActionHandler(mid,mtitle,murl){
			murl = murl + "&have_back_button=NotHave";
			murl = buildUrl(murl);
			tab.add({
				id :mid,
				title :mtitle,
				url :"<%=path%>" + murl,
				isClosed :true
			});
			tab.update({
				id :mid,
				title :mtitle,
				url :"<%=path%>" + murl,
				isClosed :true
			});
			tab.activate(mid);
		}

		function buildUrl(url){
			if (url!=null && url.indexOf('{0}')!=-1) {
				url = url.replace('{0}', '${flow_base_url}');
			}
			if (url!=null && url.slice(0, 4)=='<%=request.getScheme()%>' && url.indexOf('<%=path%>')==-1) {
				var m_tk = '<%=m_tk%>';
				if (m_tk!=null && m_tk!='null' && m_tk!='') {
					if (url.indexOf('?') != -1) {
						url = url + '&m_tk=' + m_tk;
					} else {
						url = url + '?m_tk=' + m_tk;
					}
				}
			}
			return url;
		}

		$( function() {
			tab = new TabView({
				containerId : 'tab_menu',
				pageid : 'page',
				cid : 'tab1',
				position : "top"
			});
			//if (document.getElementById("index_en")) {
				var url = buildUrl('${flow_base_url}${flow_index_url}');
				var index_en_name = document.getElementById("index_en").value;
				tab.add({
					id : 'tab1_index1',
					title : index_en_name,
					url : url,
					isClosed : false
				});
			//}
			/**tab.add( {
				id :'tab1_index1',
				title :"主页",
				url :"/per/undoTask!gettwo",
				isClosed :false
			});**/
		});

		function cmainFrameT() {
			var hmainT = document.getElementById("page");
			var bheightT = document.documentElement.clientHeight;
			hmainT.style.width = '100%';
			hmainT.style.height = (bheightT - 51) + 'px';
		}
		cmainFrameT();
		window.onresize = function() {
			cmainFrameT();

			$("#mainFrame", parent.document).css("height", "800px");
		};
	</script>
</html>
