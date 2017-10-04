ROOF.Utils.ns("com.letv");
com.letv.errorpacement = function errorpacement(error, element) {
	element.attr("title", "");
	//element.closest(".col-xs-10").addClass("eeeee").attr("title");
	var name = element.attr("name");
	var errorpace = $("div[errorpace='" + name + "']");
	if (errorpace.length > 0 && element.attr("type") == "checkbox") {
		errorpace.addClass("errordivTooltipClass");
		errorpace.tooltip({
			tooltipClass : "errorTooltipClass",
			position : {
				my : "left top",
				at : "right+5 top-5"
			},
			content : error.html()
		});
	} else {
		element.tooltip({
			tooltipClass : "errorTooltipClass",
			position : {
				my : "left top",
				at : "right+5 top-5"
			},
			content : error.html()
		});
	}

}

com.letv.successpacement = function successpacement(error, element) {
	element.attr("title", "");
	//element.closest(".col-xs-10").addClass("eeeee").attr("title");
	var name = element.attr("name");
	var errorpace = $("div[errorpace='" + name + "']");
	if (errorpace.length > 0 && element.attr("type") == "checkbox") {
		errorpace.addClass("errordivTooltipClass");
		errorpace.tooltip({
			tooltipClass : "errorTooltipClass",
			position : {
				my : "left top",
				at : "right+5 top-5"
			},
			content : error.html()
		});
	} else {
		element.tooltip({
			tooltipClass : "errorTooltipClass",
			position : {
				my : "left top",
				at : "right+5 top-5"
			},
			content : error.html()
		});
	}

}