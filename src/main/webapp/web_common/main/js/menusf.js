$(function() {
	// 默认菜单展开
	$.cookie('menusf', 'ok');
	$("#sidebar").attr("class","");
	
	// 菜单展开状态从cookie中读取
//	if(typeof($.cookie('menusf')) == "undefined"){
//		$("#sidebar").attr("class","menu-min");
//	}else{
//		$("#sidebar").attr("class","");
//	}
});

//保存缩放菜单状态
function menusf(){
	if(document.getElementsByName('menusf')[0].checked){
		$.cookie('menusf', '', { expires: -1 });
		$("#sidebar").attr("class","menu-min");
	}else{
		$.cookie('menusf', 'ok');
		$("#sidebar").attr("class","");
	}
}
