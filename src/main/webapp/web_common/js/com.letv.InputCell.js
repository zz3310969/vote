ROOF.Utils.ns("com.letv");
/**
 * 可编辑单元格
 */
com.letv.InputCell = ROOF.Class(com.letv.EditableCell, {
	initialize : function(options) {
		$.extend(this, options);
		this.form = $("<form />");
		this.form.append(this.getComponent());
	}
});