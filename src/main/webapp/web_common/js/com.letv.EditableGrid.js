ROOF.Utils.ns("com.letv");
/**
 * 可编辑表格
 */
com.letv.EditableGrid = ROOF.Class({
	target : null,
	updateRows : [],
	results : [],
	cellDefs : null, // [ { // 单元格定义 "index" : 2, // 行中排的第几列 "type" :
	// "input",// 类型 "name" : "name2", // 名称 "target" :
	// null// 父容器 }]
	// form : null,
	initialize : function(options) {
		$.extend(this, options);
		this.initRow();
		return this;
	},
	initRow : function() {
		if (!this.target) {
			return;
		}
		var trs = this.target.find("tbody tr");
		for (var i = 0; i < trs.length; i++) {
			var tr = trs[i];
			this.createRow($(tr), {});
		}
	},
	enableEdit : function(row) {
		row.enableEdit();
		this.updateRows.push(row);
	},
	createRow : function(tr, args) {
		var row = new com.letv.EditableRow({
			"target" : tr,
			"cellDefs" : this.cellDefs,
			"args" : args
		});
		tr.data("row", row);
		var self = this;
		tr.dblclick(function() {
			self.enableEdit(row);
			$(self).trigger('editableGrid.rowdblclick', [ $(this) ]); // 发布行双击事件
		});
		return row;
	},
	disableEdit : function() {
		$.each(this.updateRows, function(i, n) {
			n.disableEdit();
		});
	},
	create : function(tr, args) {
		this.target.find("tbody").prepend(tr);
		var row = this.createRow(tr, args);
		this.enableEdit(row);
	},
	submit : function(url, callback) {
		var valid = true;
		if(this.updateRows.length == 0) {
			return;
		}
		$.each(this.updateRows, function(i, n) {
			if (!n.valid()) {
				valid = false;
			}
		});
		if (valid == false) {
			return;
		}
		ROOF.Utils.showBlock();
		$.each(this.updateRows, $.proxy(function(i, n) {
			this.submitRow(url, n, callback);
		}, this));
	},
	submitRow : function(url, row, callback) {
		if (!row.disableEdit()) {
			return;
		}
		var args = {};
		$.extend(args, row.serializer());
		$.extend(args, row.args);
		$.post(url, args, $.proxy(function(d) {
			this.results.push(d);
			if (this.results.length == this.updateRows.length) {
				ROOF.Utils.hideBlock();
				callback.call(this, this.results);
			}
		}, this), "json");
	}
});