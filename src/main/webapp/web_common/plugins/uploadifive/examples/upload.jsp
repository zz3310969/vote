<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/common/include.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/plugins/uploadifive-v1.2.2-standard/uploadifive.css">
<script type="text/javascript" src="${ctx}/plugins/uploadifive-v1.2.2-standard/jquery.uploadifive.js"></script>
<input class="full-width has-padding has-border" id="file_upload" type="file" name="files">
<a href="javascript:$('#file_upload').uploadifive('upload')">开始上传</a>&nbsp;
<script type="text/javascript">
$(function(){
	$('#file_upload').uploadifive({
		"auto": false,//接受true or false两个值，当为true时选择文件后会自动上传；为false时只会把选择的文件增加进队列但不会上传，这时只能使用upload的方法触发上传。不设置auto时默认为true
		"fileSizeLimit": 5242880,//设置上传文件的容量最大值。这个值可以是一个数字或者字符串。如果是字符串，接受一个单位（B,KB,MB,GB）。如果是数字则默认单位为KB。设置为0时表示不限制
		"uploadScript": '${ctx}/common/file/upFileTest.do',//上传文件接收地址
		"fileObjName": 'files',//后台接收参数名称
		"buttonText": '<div>选择文件</div>',//选择文件按钮名称
		"formData": {},//通过get或post上传文件时，此对象提供额外的数据。如果想动态设置这些值，必须在onUploadStartg事件中使用settings的方法设置
		"height": 30,//设置按钮的高度(单位px)，默认为30.(不要在值里写上单位，并且要求一个整数，width也一样)
		"width": 120,//设置按钮宽度(单位px)，默认120
		"method":"post",//提交上传文件的方法，接受post或get两个值，默认为post
		"multi" : true,//设置是否允许一次选择多个文件，true为允许，false不允许
		"removeCompleted": false,//是否移除掉队列中已经完成上传的文件。false为不移除
		"fileType" : '/*',//上传文件类型'application/pdf|application/doc|application/docx',["gif","jpeg","png"],'image/png|image/jpg','image/*',["image\/jpeg","image\/png"]
		"uploadLimit": 10, //一次可以上传的最大文件数
		"queueSizeLimit":10,//设置每一次上传队列中的文件数量。注意并不是限制总的上传文件数量（那是uploadLimit）.如果增加进队列中的文件数量超出这个值，将会触发onSelectError事件。默认值为999
		"simUploadLimit": 3,//同时上传文件的数量
		//"checkExisting": false,//接受一个文件路径。此文件检查正要上传的文件名是否已经存在目标目录中。存在时返回1，不存在时返回0(应该主要是作为后台的判断吧)，默认为false
		//"queueID": false,//进度条的显示位置
		//"overrideEvents": [],//重写事件，接受事件名称的数组作为参数。所设置的事件将可以被用户重写覆盖
		//"itemTemplate":"",//模板对象。给增加到上传队列中的每一项指定特殊的html模板。模板格式请看官网http://www.uploadify.com/documentation/uploadify/itemtemplate/
		"onUploadComplete" : function(file, data) {//文件上传成功后执行
			//var obj = JSON.parse(data);
			//if (obj.img == "500") {
				//alert("系统异常！");
			//} else {
				//$("#frontSide").val(obj.img);
				//document.getElementById("submit").disabled = false;
			//}
		},
		"onCancel" : function(file) {//文件被删除时触发
			//$("#frontSide").val("");
			/* 注意：取消后应重新设置uploadLimit */
			//$data	= $(this).data('uploadifive'),
			//settings = $data.settings;
			//settings.uploadLimit++;
			//alert(file.name + " 已取消上传~!");
		},
		"onUpload" : function(file) {//开始上传队列时触发
			//document.getElementById("submit").disabled = true;//当开始上传文件，要防止上传未完成而表单被提交
		},
		"onFallback" : function() {//HTML5 API不支持的浏览器触发
			//alert("该浏览器无法使用!");
		}
	});
});
</script>