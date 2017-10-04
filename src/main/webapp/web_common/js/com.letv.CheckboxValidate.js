ROOF.Utils.ns("com.letv");
/*com.letv.CheckboxValidate = function letvcheckboxValidate(list) {
	var flag = true; 
	if(list != null && list != ''){
		$.each(list,function(i,n){
			var $input = $("div[name="+n[0]+"]");
			if($input.length == 0){
				flag = false;
				alert(n[1]);
				//alert($("#product_our").attr("class"));
				$input.parent().toggleClass("eeeee");
				//$("#sssssss").toggleClass("eeeee");
				return false;
			}
			if($input.attr("type") == 'checkbox'){
				var checkboxflag = false;
				$.each($input, function(index, element) {
					if ($(element).attr("checked")) {
						checkboxflag = true;
						return false;// break;
					}
				});
				if(!checkboxflag){
					flag = false;
					alert(n[1]);
					return false;
				}
			}
		});
	}
	return flag;
}*/
com.letv.CheckboxValidate = function letvcheckboxValidate(list) {
	var flag = true; 
	if(list != null && list != ''){
		$.each(list,function(i,n){
			var $input = $("div[name="+n[0]+"]");
			if($input.length >= 0){
				$input.mouseover(function(){
					//alert(1);
				});
				$input.removeClass("eeeee");
				if($input.children(":checkbox:checked").length == 0){
					flag = false;
					$input.addClass("eeeee");
					$input.tooltip({
						tooltipClass : "errorTooltipClass",
						position : {
							my : "left top",
							at : "right+5 top-5"
						},
						content : n[1]
					});
				}
				
				return false;
				
			}
			
		});
	}
	return flag;
}