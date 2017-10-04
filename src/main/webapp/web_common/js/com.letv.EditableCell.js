ROOF.Utils.ns("com.letv");
/**
 * 可编辑单元格
 */
com.letv.EditableCell = ROOF.Class({
	editable : false,
	"index" : 0, // 行中排的第几列
	"type" : "input",// 类型
	"name" : "", // 名称
	"target" : null,
	component : null,
	form : null,
	initialize : function(options) {
		$.extend(this, options);
	},
	setVal : function(val) {
		if (this.editable) {
			this.getComponent().val(val);
		} else {
			this.target.html(val);
		}
	},
	getVal : function() {
		if (this.editable) {
			return this.getComponent().val();
		} else {
			return this.target.html();
		}
	},
	// public
	// 获取控件
	getComponent : function() {
		if (!this.component) {
			var rs = $("<input type='text'/>");
			rs.attr("name", this.name);
			this.component = rs;
		}
		this.component.width(parseInt(this.target.width()) * 0.5)
		return this.component;
	},
	// public
	// 可编辑
	enableEdit : function() {
		if (this.editable == true) {
			return;
		}
		if (!this.target) {
			return;
		}
		var val = this.getVal();
		this.editable = true;
		this.setVal(val);
		this.target.empty();
		this.target.append(this.form);
		this.form.validate({
			rules : {},
			errorPlacement : com.letv.errorpacement
		});
		if (this.rule) {
			this.getComponent().rules("add", this.rule);
		}
	},
	disableEdit : function() {
		if (!this.editable) {
			return;
		}
		if (!this.target) {
			return;
		}
		if (!this.valid()) {
			return;
		}
		var val = this.getVal();
		this.editable = false;
		this.target.empty();
		this.setVal(val);
	},
	// public
	// 验证合法性
	valid : function() {
		return this.form.valid();
	},
	// public
	// 获取序列化对象
	serializer : function() {
		var rs = {};
		rs[this.name] = this.getVal();
		return rs;
	},
	serializerStr : function() {
		return this.name + "=" + this.getVal();
	}
});