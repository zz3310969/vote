(function() {
  var template = Handlebars.template, templates = Handlebars.templates = Handlebars.templates || {};
  templates["list/templates/list"] = template({"1":function(container,depth0,helpers,partials,data) {
    var helper;

  return "<li>\r\n    <a href=\"\" class=\"zwfbox\" data-id=\""
    + container.escapeExpression(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"id","hash":{},"data":data}) : helper)))
    + "\">\r\n      <img class=\"zwfimg\" src=\"/images/hcxz_zwf.png\"/>\r\n        <img class=\"pic\" data-src=\""
    + container.escapeExpression(((helper = (helper = helpers.img_src || (depth0 != null ? depth0.img_src : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"img_src","hash":{},"data":data}) : helper)))
    + "\"/>\r\n        <span class=\"number\">"
    + container.escapeExpression(((helper = (helper = helpers.vote_code || (depth0 != null ? depth0.vote_code : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"vote_code","hash":{},"data":data}) : helper)))
    + "</span>\r\n        <div class=\"tt\">"
    + container.escapeExpression(((helper = (helper = helpers.username || (depth0 != null ? depth0.username : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"username","hash":{},"data":data}) : helper)))
    + "</div>\r\n    </a>\r\n    <div class=\"name\"><span><i>"
    + container.escapeExpression(((helper = (helper = helpers.num || (depth0 != null ? depth0.num : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"num","hash":{},"data":data}) : helper)))
    + "</i>票</span><div class=\"pic_user\">\r\n        <img src=\"/images/tip.jpg\"/>"
    + container.escapeExpression(((helper = (helper = helpers.name || (depth0 != null ? depth0.name : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"name","hash":{},"data":data}) : helper)))
    + "</div></div>\r\n        <div class=\"btn\">\r\n        <input type=\"button\" datacode=\""
    + container.escapeExpression(((helper = (helper = helpers.vote_code || (depth0 != null ? depth0.vote_code : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"vote_code","hash":{},"data":data}) : helper)))
    + "\" class=\"btntp js_vote\" value=\"投票\"/>\r\n        <div class=\"numCon js_numsbox\"><button class=\"js_minus minus\">-</button>\r\n            <input type=\"text\" class=\"js_cart_addnum txt\" value=\"1\"  pattern=\"[0-9]*\"/><button class=\"js_plus plus\">+</button>\r\n        </div>\r\n    </div>\r\n</li>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});
templates["list/templates/order-li"] = template({"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    return "";
},"useData":true});
templates["templates/mywork_list"] = template({"1":function(container,depth0,helpers,partials,data) {
    var stack1, helper;

  return "<li data-id=\""
    + container.escapeExpression(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"id","hash":{},"data":data}) : helper)))
    + "\" data-status='"
    + container.escapeExpression(((helper = (helper = helpers.proStatusName || (depth0 != null ? depth0.proStatusName : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"proStatusName","hash":{},"data":data}) : helper)))
    + "'>\r\n    <a href=\"javascript:void(0);\" class=\"zwfbox js_detail_a\">\r\n      <img class=\"zwfimg\" src=\"/images/hcxz_zwf.png\"/>\r\n        <img class=\"pic\" data-src=\""
    + container.escapeExpression(((helper = (helper = helpers.img_src || (depth0 != null ? depth0.img_src : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"img_src","hash":{},"data":data}) : helper)))
    + "\"/>\r\n        <span class=\"number\">"
    + container.escapeExpression(((helper = (helper = helpers.vote_code || (depth0 != null ? depth0.vote_code : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"vote_code","hash":{},"data":data}) : helper)))
    + "</span>\r\n        <div class=\"tt\">"
    + container.escapeExpression(((helper = (helper = helpers.name || (depth0 != null ? depth0.name : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"name","hash":{},"data":data}) : helper)))
    + "</div>\r\n    </a>\r\n"
    + ((stack1 = (helpers.equals || (depth0 && depth0.equals) || helpers.helperMissing).call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.proStatusName : depth0),"审核通过",{"name":"equals","hash":{},"fn":container.program(2, data, 0),"inverse":container.program(9, data, 0),"data":data})) != null ? stack1 : "")
    + "</li>\r\n";
},"2":function(container,depth0,helpers,partials,data) {
    var stack1, helper;

  return "    <div class=\"vt_txt\"><span>票数"
    + container.escapeExpression(((helper = (helper = helpers.num || (depth0 != null ? depth0.num : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"num","hash":{},"data":data}) : helper)))
    + "："
    + ((stack1 = helpers["if"].call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.num : depth0),{"name":"if","hash":{},"fn":container.program(3, data, 0),"inverse":container.program(5, data, 0),"data":data})) != null ? stack1 : "")
    + "票</span></div>\r\n"
    + ((stack1 = helpers["if"].call(depth0 != null ? depth0 : {},(depth0 != null ? depth0.index : depth0),{"name":"if","hash":{},"fn":container.program(7, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"3":function(container,depth0,helpers,partials,data) {
    var helper;

  return container.escapeExpression(((helper = (helper = helpers.num || (depth0 != null ? depth0.num : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"num","hash":{},"data":data}) : helper)));
},"5":function(container,depth0,helpers,partials,data) {
    return "0";
},"7":function(container,depth0,helpers,partials,data) {
    var helper;

  return "    <div class=\"vt_txt\"><span>排名："
    + container.escapeExpression(((helper = (helper = helpers.index || (depth0 != null ? depth0.index : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"index","hash":{},"data":data}) : helper)))
    + "</span></div>\r\n";
},"9":function(container,depth0,helpers,partials,data) {
    var helper;

  return "     <div class=\"vt_txt\"><span>"
    + container.escapeExpression(((helper = (helper = helpers.proStatusName || (depth0 != null ? depth0.proStatusName : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"proStatusName","hash":{},"data":data}) : helper)))
    + "</span></div>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});
templates["templates/rank"] = template({"1":function(container,depth0,helpers,partials,data) {
    var stack1;

  return ((stack1 = (helpers.lt || (depth0 && depth0.lt) || helpers.helperMissing).call(depth0 != null ? depth0 : {},(data && data.index),3,{"name":"lt","hash":{},"fn":container.program(2, data, 0),"inverse":container.program(4, data, 0),"data":data})) != null ? stack1 : "");
},"2":function(container,depth0,helpers,partials,data) {
    var helper;

  return "  <li class=\"rank"
    + container.escapeExpression((helpers.add || (depth0 && depth0.add) || helpers.helperMissing).call(depth0 != null ? depth0 : {},(data && data.index),1,{"name":"add","hash":{},"data":data}))
    + "  js_list\">\r\n    <div class=\"mc\"><img src=\"/images/rank"
    + container.escapeExpression((helpers.add || (depth0 && depth0.add) || helpers.helperMissing).call(depth0 != null ? depth0 : {},(data && data.index),1,{"name":"add","hash":{},"data":data}))
    + ".png\"/></div>\r\n    <div class=\"name\"><img data-src=\""
    + container.escapeExpression(((helper = (helper = helpers.img_src || (depth0 != null ? depth0.img_src : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"img_src","hash":{},"data":data}) : helper)))
    + "\" alt=\"\" /><span>"
    + container.escapeExpression(((helper = (helper = helpers.name || (depth0 != null ? depth0.name : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"name","hash":{},"data":data}) : helper)))
    + "</span></div>\r\n    <div class=\"num\">"
    + container.escapeExpression(((helper = (helper = helpers.num || (depth0 != null ? depth0.num : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"num","hash":{},"data":data}) : helper)))
    + "票</div>\r\n  </li>\r\n";
},"4":function(container,depth0,helpers,partials,data) {
    var helper;

  return "  <li>\r\n    <div class=\"mc\">"
    + container.escapeExpression((helpers.add || (depth0 && depth0.add) || helpers.helperMissing).call(depth0 != null ? depth0 : {},(data && data.index),1,{"name":"add","hash":{},"data":data}))
    + "</div>\r\n    <div class=\"name\"><img data-src=\""
    + container.escapeExpression(((helper = (helper = helpers.img_src || (depth0 != null ? depth0.img_src : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"img_src","hash":{},"data":data}) : helper)))
    + "\" alt=\"\" /><span>"
    + container.escapeExpression(((helper = (helper = helpers.name || (depth0 != null ? depth0.name : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"name","hash":{},"data":data}) : helper)))
    + "</span></div>\r\n    <div class=\"num\">"
    + container.escapeExpression(((helper = (helper = helpers.num || (depth0 != null ? depth0.num : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0 != null ? depth0 : {},{"name":"num","hash":{},"data":data}) : helper)))
    + "票</div>\r\n  </li>\r\n";
},"compiler":[7,">= 4.0.0"],"main":function(container,depth0,helpers,partials,data) {
    var stack1;

  return "<li class=\"rank_tit\">\r\n<div class=\"col1\">排名</div>\r\n<div class=\"col2\">作品名</div>\r\n<div class=\"col3\">当前票数</div>\r\n</li>\r\n"
    + ((stack1 = helpers.each.call(depth0 != null ? depth0 : {},depth0,{"name":"each","hash":{},"fn":container.program(1, data, 0),"inverse":container.noop,"data":data})) != null ? stack1 : "");
},"useData":true});

})();
