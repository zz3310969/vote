ROOF.Utils.ns("com.letv");
/**
 * 可编辑表格行
 */
com.letv.EditableRow = ROOF.Class({
	cells : null,
	"index" : 0, // 表格中的行
	"target" : null,// 父容器
	cellDefs : null, // [ { // 单元格定义 "index" : 2, // 行中排的第几列 "type" :
	// "input",// 类型 "name" : "name2", // 名称 "target" :
	// null// 父容器 }]
	form : null,
	initialize : function(options) {
		$.extend(this, options);
		if (this.cells == null) {
			this.cells = [];
			this.initCells();
		}
	},
	initCells : function() {
		for (var i = 0; i < this.cellDefs.length; i++) {
			this.initCell(this.cellDefs[i]);
		}
	},
	// private
	initCell : function(cellDef) {
		if (!this.target) {
			return;
		}
		cellDef.target = this.target.find("td:eq(" + cellDef.index + ")");
		if (cellDef.type == "input") {
			this.cells.push(new com.letv.InputCell(cellDef));
		}
		if (cellDef.type == "date") {
			this.cells.push(new com.letv.InputCell(new com.letv.DateCell(
					cellDef)));
		}

	},
	// public
	// 可编辑
	enableEdit : function() {
		for (var i = 0; i < this.cells.length; i++) {
			var n = this.cells[i];
			n.enableEdit();
		}
	},
	// public
	// 取消编辑
	disableEdit : function() {
		var valid = true;
		$.each(this.cells, function(i, n) {
			if (!n.valid()) {
				valid = false;
			}
		});
		if (valid) {
			$.each(this.cells, function(i, n) {
				n.disableEdit();
			});
		}
		return valid;
	},
	valid : function() {
		var valid = true;
		$.each(this.cells, function(i, n) {
			if (!n.valid()) {
				valid = false;
			}
		});
		return valid;
	},
	// public
	// 获取序列化对象
	serializer : function() {
		var rs = {};
		$.each(this.cells, function(i, n) {
			$.extend(rs, n.serializer());
		});
		return rs;
	},
	serializerStr : function() {
		var rs = new Array();
		$.each(this.cells, function(i, n) {
			rs.push(n.serializerStr());
		});
		return rs.join("&");
	}
});