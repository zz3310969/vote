ROOF.Utils.ns("com.letv");
/**
 * 可编辑单元格
 */
com.letv.DateCell = ROOF.Class(com.letv.EditableCell, {
	"type" : "date",// 类型
	initialize : function(options) {
		$.extend(this, options);
		this.form = $("<form />");
		this.form.append(this.getComponent());
	},
	// public
	// 获取控件
	getComponent : function() {
		if (!this.component) {
			var rs = $("<input type='text'/>");
			rs.attr("name", this.name);
			this.component = rs;
			this.component.attr("readonly", "readonly");
		}
		ROOF.Utils.datepicker(this.component);
		return this.component;
	}
});