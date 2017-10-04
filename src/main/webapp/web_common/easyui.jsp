<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Basic Droppable - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/ems_common/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath}/ems_common/easyui/themes/icon.css">
<script type="text/javascript" src="${basePath}/ems_common/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/ems_common/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<h2>test bigscreen</h2>
	<div style="margin: 20px 0;"></div>
	<div style="float: left; width: 200px; margin-right: 20px;">
		<div class="title">Source</div>
		<div>
			<div class="dragitem" >
			<input type="hidden" value="${basePath}/ems_common/1.jpg" id="img" name="img" />
			<img src="${basePath}/ems_common/1.jpg" width="100px" height="100px"/>
			</div>
			<div class="dragitem"><img src="${basePath}/ems_common/2.jpg" width="100px" height="100px"/></div>
			<div class="dragitem"><img src="${basePath}/ems_common/3.jpg" width="100px" height="100px"/></div>
		</div>
	</div>
	<div style="float: left; width: 200px;">
		<div class="title">报表1</div>
		<div class="easyui-droppable targetarea"
			data-options="
    					accept: '.dragitem',
    					onDragEnter:function(e,source){
    					},
    					onDragLeave: function(e,source){
    					},
    					onDrop: function(e,source){
    						console.log($(this));
    						console.log($(this).attr('height'));
    						var target = $(source).clone();
    						$(target).children('img').attr('height','150px');
    						$(target).children('img').attr('width','200px');
     						$(this).html($(target).html()); 
    					}
    				">
		</div>
		<div class="title">报表2</div>
		<div class="easyui-droppable targetarea"
			data-options="
    					accept: '.dragitem',
    					onDragEnter:function(e,source){
    					},
    					onDragLeave: function(e,source){
    					},
    					onDrop: function(e,source){
    						var target = $(source).clone();
    						$(target).children('img').attr('height','150px');
    						$(target).children('img').attr('width','200px');
     						$(this).html($(target).html()); 
    					}
    				">
		</div>	
		<div class="title">报表3</div>
		<div class="easyui-droppable targetarea"
			data-options="
    					accept: '.dragitem',
    					onDragEnter:function(e,source){
    					},
    					onDragLeave: function(e,source){
    					},
    					onDrop: function(e,source){
    						var target = $(source).clone();
    						$(target).children('img').attr('height','150px');
    						$(target).children('img').attr('width','200px');
     						$(this).html($(target).html()); 
    					}
    				">
		</div>
	</div>
	<div style="float: left; width: 400px;">
		<div class="title">地图</div>
		<div class="easyui-droppable targetarea" style="height: 515px;"
			data-options="
    					accept: '.dragitem',
    					onDragEnter:function(e,source){
    					},
    					onDragLeave: function(e,source){
    					},
    					onDrop: function(e,source){
    						var target = $(source).clone();
    						$(target).children('img').attr('height','515px');
    						$(target).children('img').attr('width','400px');
     						$(this).html($(target).html()); 
    					}
    				">
		</div>
	</div>
	<div style="float: left; width: 200px;">
		<div class="title">报表4</div>
		<div class="easyui-droppable targetarea"
			data-options="
    					accept: '.dragitem',
    					onDragEnter:function(e,source){
    					},
    					onDragLeave: function(e,source){
    					},
    					onDrop: function(e,source){
    						var target = $(source).clone();
    						$(target).children('img').attr('height','150px');
    						$(target).children('img').attr('width','200px');
     						$(this).html($(target).html()); 
    					}
    				">
		</div>
		<div class="title">报表5</div>
		<div class="easyui-droppable targetarea"
			data-options="
    					accept: '.dragitem',
    					onDragEnter:function(e,source){
    					},
    					onDragLeave: function(e,source){
    					},
    					onDrop: function(e,source){
    						var target = $(source).clone();
    						$(target).children('img').attr('height','150px');
    						$(target).children('img').attr('width','200px');
     						$(this).html($(target).html()); 
    					}
    				">
		</div>	
		<div class="title">报表6</div>
		<div class="easyui-droppable targetarea"
			data-options="
    					accept: '.dragitem',
    					onDragEnter:function(e,source){
    					},
    					onDragLeave: function(e,source){
    					},
    					onDrop: function(e,source){
    						var target = $(source).clone();
    						$(target).children('img').attr('height','150px');
    						$(target).children('img').attr('width','200px');
     						$(this).html($(target).html()); 
    					}
    				">
		</div>
	</div>
	<div style="float: left; width: 200px;">
		
	</div>
	<div style="clear: both"></div>
	<style type="text/css">
.title {
	margin-bottom: 10px;
}

.dragitem {
	border: 1px solid #ccc;
	width: 100px;
	height: 100px;
	margin-bottom: 60px;
}

.targetarea {
	border: 1px solid red;
	height: 150px;
}

.proxy {
	border: 1px solid #ccc;
	width: 80px;
	background: #fafafa;
}
</style>
	<script>
		$(function() {
			$('.dragitem').draggable({
				revert : true,
				deltaX : 10,
				deltaY : 10,
				proxy : function(source) {
					var n = $('<div class="proxy"></div>');
					n.html($(source).html()).appendTo('body');
					return n;
				}
			});
		});
	</script>
</body>
</html>