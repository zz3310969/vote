ROOF.Utils.ns("com.letv");
com.letv.PageBar = ROOF.Class({
	form : null,
	table : null,
	rowNumber : 10,
	initialize : function(form, table, rowNumber) {
		this.form = form;
		if (table) {
			this.table = table;
		}
		if (rowNumber) {
			this.rowNumber = rowNumber;
		}
		if (this.table) {
			var currentRowNumber = this.table.find("tbody tr").length;
			var tdNumber = this.table.find("thead tr th").length;
			if (currentRowNumber < this.rowNumber) {
				for (var i = 0; i < this.rowNumber - currentRowNumber; i++) {
					var tr = $("<tr />");
					for (var j = 0; j < tdNumber; j++) {
						tr.append("<td>&nbsp;&nbsp;</td>");
					}
					table.find("tbody").append(tr);
				}
			}
		}
		$("#next_page_li").click($.proxy(function() {
			this.nextPage();
			return null;
		}, this));
		$("#pre_page_li").click($.proxy(function() {
			this.prePage();
			return null;
		}, this));
		var self = this;
		$("a[name='page_li']").click(function() {
			var pageNumber = parseInt($(this).html());
			self.gotoPage(pageNumber);
			return null;
		});
		$("a[name='page_end_page']").click(function() {
			var pageNumber = $(this).attr("data");
			self.gotoPage(pageNumber);
			return null;
		});
		$("a[name='page_first_page']").click(function() {
			var pageNumber = "1";
			self.gotoPage(pageNumber);
			return null;
		});
		$("#first_page_li").click($.proxy(function() {
			this.firstPage();
			return null;
		}, this));
		$("#last_page_li").click($.proxy(function() {
			this.gotoPage(parseInt($("#last_page_li").val()));
			return null;
		}, this));
		return this;
	},
	findNextPage : function() {
		return this.form.find("input[name='nextPage']");
	},
	findCurrentPage : function() {
		return this.form.find("input[name='currentPage']");
	},
	prePage : function() {
		var c = this.findCurrentPage();
		this.gotoPage(parseInt(c.val()) - 1);
	},
	nextPage : function() {
		var c = this.findCurrentPage();
		this.gotoPage(parseInt(c.val()) + 1);
	},
	firstPage : function() {
		this.gotoPage(1);
	},
	gotoPage : function(pageNumber) {
		var c = this.findNextPage();
		if (c.length == 0) {
			c = this.findCurrentPage();
		}
		c.val(pageNumber);
		this.form.submit();
	}
});