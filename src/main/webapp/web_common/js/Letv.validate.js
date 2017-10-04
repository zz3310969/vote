ROOF.Validate = ROOF.Class({
	target : null,
	validate_list : null,
	validate_ever : false,
	validationTarget: function( element,message ) {
		$(element).removeClass("errordivTooltipClass");
		$(element).attr("title", "");
		$(element).tooltip({
			tooltipClass : "errorTooltipClass",
			position : {
				my : "left top",
				at : "right+5 top-5"
			},
			content : message
		});
		if($(element).find(":checkbox:checked").length == 0){
			
			$(element).attr("title", message);
			$(element).addClass("errordivTooltipClass");
			
			$(element).tooltip( "enable" );
			return false;
		}else{
			$(element).tooltip( "disable" );
		}
		return true;
	},
	mouseover : function(e, element,message) {
		//console.log("mouseover"+this.validate_ever);
		if (this.validate_ever) {
			this.validationTarget(element,message);
		}
	},
	mouseout : function(e, element,message) {
		//console.log("mouseout"+this.validate_ever);
		if (this.validate_ever) {
			this.validationTarget(element,message);
		}
		
	},
	click : function(e, element,message) {
		//console.log("click"+this.validate_ever);
		if (this.validate_ever) {
			this.validationTarget(element,message);
		}
		
	},
	valid_ever : function() {
		if (this.validate_ever) {
			var _this = this;
			var result = true;
			if (this.validate_list) {
				$.each(this.validate_list,function(i,n){
					var $element = $("div[name="+n[0]+"]");
					result = _this.validationTarget($element,n[1]) && result;
				});
			}
		}
		
	},
	
	valid : function() {
		this.validate_ever = true;
		var _this = this;
		var result = true;
		//console.log(this.validate_list);
		if (this.validate_list) {
			$.each(this.validate_list,function(i,n){
				var $element = $("div[name="+n[0]+"]");
				result = _this.validationTarget($element,n[1]) && result;
			});
		}
		return result;
	},
	initialize : function(table, validate_list) {
		var _this = this;
		_this.target = table;
		table.submit(function(e){
			var re = _this.target.valid();
			var re1 = _this.valid();
			return re&&re1?true:false;
		  });
		if (validate_list) {
			this.validate_list = validate_list;
			$.each(validate_list,function(i,n){
				var $element = $("div[name="+n[0]+"]");
				$element.bind('mouseover', function(e){
					_this.mouseover.apply(_this, [e, this,n[1]]);
				});
				$element.bind('mouseout', function(e){
					_this.mouseout.apply(_this, [e, this,n[1]]);
				});
				$element.bind('click', function(e){
					_this.click.apply(_this, [e, this,n[1]]);
				});
			});
		}
		
	}/*
	initialize : function(element, message) {
		var _this = this;
		_this.target = element;
		if (message) {
			this.message = message;
		}
		element.bind('mouseover', function(e){
			_this.mouseover.apply(_this, [e]);
		});
		element.bind('mouseout', function(e){
			_this.mouseout.apply(_this, [e]);
		});
	},*/
	
});