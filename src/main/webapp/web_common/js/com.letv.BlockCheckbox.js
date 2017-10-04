ROOF.Utils.ns("com.letv");
com.letv.BlockCheckbox = ROOF.Class({
	target : null,
	initialize : function(args) {
		this.target = args.target;
		return this;
	},
	// public
	/**
	 * 添加一个复选值
	 * 
	 * @param name
	 *            提交的属性名称
	 * @param val
	 *            提交的值
	 * @param text
	 *            显示的文本值
	 */
	add : function(name, val, text) {
		if (this.asSame(name, val)) {
			return;
		}
		var closeBtn = $('<em class="icon-close"></em>');
		var a = $('<span class="label label-info label-gray">' + text + '</span>');
		a.append(closeBtn);
		a.attr("name", name);
		var b = $('<div style="float: left; width: calc(100% - 50px);"></div>');
		b.append(a);
		this.target.append(b);
		this.addCheckBox(name, val);
		closeBtn.on("click", function() {
			b.remove();
			
			// 删除时：将div的线补完整
			$(".set_div_height").each(function() {
				var divHeight = 36;
				var max_add_height = 0;
				$(this).find(".col-xs-4").each(function(i) {
					var add_height = 0;
					$(this).find(".label-info").each(function(j) {
						if(j) {
							add_height = add_height + $(this).height() * 2.5;
						}
					});
					if(add_height > max_add_height) {
						max_add_height = add_height;
					}
				});
				divHeight = divHeight + max_add_height;
				$(this).css("height", divHeight+"px");
				$(this).find("[class='col-xs-2']").css("height", divHeight+"px");
				$(this).find("[class='col-xs-4']").css("height", divHeight+"px");
			});
		});
		closeBtn.on("click", $.proxy(function() {
			this.findCheckBox(name, val).remove();
		}, this));
		
		// 添加时：将div的线补完整
		$(".set_div_height").each(function() {
			var divHeight = 36;
			var max_add_height = 0;
			$(this).find(".col-xs-4").each(function(i) {
				var add_height = 0;
				$(this).find(".label-info").each(function(j) {
					if(j) {
						add_height = add_height + $(this).height() * 2.5;
					}
				});
				if(add_height > max_add_height) {
					max_add_height = add_height;
				}
			});
			divHeight = divHeight + max_add_height;
			$(this).css("height", divHeight+"px");
			$(this).find("[class='col-xs-2']").css("height", divHeight+"px");
			$(this).find("[class='col-xs-4']").css("height", divHeight+"px");
		});
	},
	//查询模块中的显示
	read : function(name, val, text) {
		if (this.asSame(name, val)) {
			return;
		}
		var closeBtn = $(' <em class="icon-close"></em>');
		var a = $('<span >' + text + '</span>');
		a.append(closeBtn);
		a.attr("name", name);
		this.target.append(a);
	},
	// private
	asSame : function(name, val) {
		var rs = this.findCheckBox(name, val);
		return rs.length > 0;
	},
	// private
	findCheckBox : function(name, val) {
		var rs = this.target.find(":checkbox[name='" + name + "'][value='"
				+ val + "']");
		return rs;
	},
	// private
	addCheckBox : function(name, val) {
		var chkbx = $("<input type='checkbox' />");
		chkbx.attr("name", name);
		chkbx.attr("value", val);
		chkbx.attr("checked", "checked");
		chkbx.hide();
		this.target.append(chkbx);
	}
});