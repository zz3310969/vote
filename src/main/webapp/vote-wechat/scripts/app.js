(function() {
  if (!this.require) {
    var modules = {}, cache = {};

    var require = function(name, root) {
      var path = expand(root, name), indexPath = expand(path, './index'), module, fn;
      module   = cache[path] || cache[indexPath];
      if (module) {
        return module;
      } else if (fn = modules[path] || modules[path = indexPath]) {
        module = {id: path, exports: {}};
        cache[path] = module.exports;
        fn(module.exports, function(name) {
          return require(name, dirname(path));
        }, module);
        return cache[path] = module.exports;
      } else {
        throw 'module ' + name + ' not found';
      }
    };

    var expand = function(root, name) {
      var results = [], parts, part;
      // If path is relative
      if (/^\.\.?(\/|$)/.test(name)) {
        parts = [root, name].join('/').split('/');
      } else {
        parts = name.split('/');
      }
      for (var i = 0, length = parts.length; i < length; i++) {
        part = parts[i];
        if (part == '..') {
          results.pop();
        } else if (part != '.' && part != '') {
          results.push(part);
        }
      }
      return results.join('/');
    };

    var dirname = function(path) {
      return path.split('/').slice(0, -1).join('/');
    };

    this.require = function(name) {
      return require(name, '');
    };

    this.require.define = function(bundle) {
      for (var key in bundle) {
        modules[key] = bundle[key];
      }
    };

    this.require.modules = modules;
    this.require.cache   = cache;
  }

  return this.require;
}).call(this);this.require.define({"helper":function(exports, require, module){(function() {
  var indexOf;

  Handlebars.registerHelper("lt", function(a, b, options) {
    if (a < b) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("eq", function(a, b, options) {
    if (a === b) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("equals", function(a, b, options) {
    if (a === b) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("json", function(jsonObj, options) {
    return JSON.stringify(jsonObj);
  });

  Handlebars.registerHelper("is", function(a, test, options) {
    if (a + "" === test + "") {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("isnt", function(a, test, options) {
    if (a !== test) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("notin", function(a, arrayStr, options) {
    var array, item, k, len;
    array = arrayStr.split(",");
    for (k = 0, len = array.length; k < len; k++) {
      item = array[k];
      if (a === item) {
        return options.inverse(this);
      }
    }
    return options.fn(this);
  });

  Handlebars.registerHelper("in", function(a, arrayStr, options) {
    var array, item, k, len;
    array = (arrayStr || "").split(",") || [];
    for (k = 0, len = array.length; k < len; k++) {
      item = array[k];
      if (a === item) {
        return options.fn(this);
      }
    }
    return options.inverse(this);
  });

  Handlebars.registerHelper("nullDefault", function(a, b, options) {
    if (a) {
      return a;
    } else {
      return b;
    }
  });

  Handlebars.registerHelper("has", function(a, b, options) {
    if (a.indexOf(b) > -1) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("haslowwer", function(a, b, options) {
    a = a || "";
    a = a.toLowerCase();
    if (a.indexOf(b) > -1) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("even", function(a, options) {
    if (a % 2 === 0) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("odd", function(a, options) {
    if (a % 2 === 1) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("add", function(a, b, options) {
    return a + b;
  });

  Handlebars.registerHelper("multiply", function(a, b, options) {
    return a * b;
  });

  Handlebars.registerHelper("before", function(a, b, arr, options) {
    var i, j;
    i = indexOf(arr, a);
    j = indexOf(arr, b);
    if (j > i) {
      return options.inverse(this);
    } else {
      return options.fn(this);
    }
  });

  indexOf = function(array, value) {
    var a, i, k, len;
    for (i = k = 0, len = array.length; k < len; i = ++k) {
      a = array[i];
      if (a === value) {
        return i;
      }
    }
  };

  Handlebars.registerHelper("eachCount", function(a, options) {
    var i, k, ref, result;
    result = new Array();
    for (i = k = 1, ref = a; 1 <= ref ? k <= ref : k >= ref; i = 1 <= ref ? ++k : --k) {
      result.push(i);
    }
    return Handlebars.helpers["each"](result, options);
  });

  Handlebars.registerHelper("splitLast", function(a, options) {
    var arr;
    if (a === null) {
      return "";
    }
    arr = a.split(",");
    return arr[arr.length - 1];
  });

  Handlebars.registerHelper("gt", function(a, b, options) {
    if (parseFloat(a) > parseFloat(b)) {
      return options.fn(this);
    }
    return options.inverse(this);
  });

  Handlebars.registerHelper("getValueInMap", function(map, key, options) {
    var obj;
    map = map || {};
    for (obj in map) {
      if (obj === key) {
        return map[key];
      }
    }
    return "";
  });

  Handlebars.registerHelper("getValueInList", function(objArr, value, options) {
    var k, len, obj;
    objArr = objArr || [];
    for (k = 0, len = objArr.length; k < len; k++) {
      obj = objArr[k];
      if ((obj.name + "") === (value + "")) {
        return obj.value;
      }
    }
    return "";
  });

  Handlebars.registerHelper("getValueInListByKey", function(objArr, name, value, key, options) {
    var k, len, obj;
    objArr = objArr || [];
    for (k = 0, len = objArr.length; k < len; k++) {
      obj = objArr[k];
      if ((obj[name] + "") === (value + "")) {
        return obj[key];
      }
    }
    return "";
  });

  Handlebars.registerHelper("size", function(arr, options) {
    arr = arr || [];
    return arr.length;
  });

  Handlebars.registerHelper("order", function(index, start, options) {
    var num;
    num = parseInt(index + 1) + start;
    return num;
  });

  Handlebars.registerHelper("between", function(a, b, options) {
    var k, numbers, results;
    numbers = (function() {
      results = [];
      for (var k = a; a <= b ? k <= b : k >= b; a <= b ? k++ : k--){ results.push(k); }
      return results;
    }).apply(this);
    return Handlebars.helpers["each"](numbers, options);
  });

  Handlebars.registerHelper("equalsAddOne", function(a, b, options) {
    if (parseInt(a + 1) === parseInt(b)) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("equalsAdd", function(a, b, c, options) {
    if (a === b + c) {
      return options.fn(this);
    } else {
      return options.inverse(this);
    }
  });

  Handlebars.registerHelper("toLowwer", function(a, options) {
    a = a || "";
    return a.toLowerCase();
  });

}).call(this);
;}});
this.require.define({"openid":function(exports, require, module){var wxopenid=getcookie('wxopenid');  
var key=getcookie('key');  
if (key==''){  
    var access_code=GetQueryString('code');  
    if (wxopenid==""){  
        if (access_code==null)  
        {     
            var fromurl=location.href;  
            var url='https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx4871de30bcfb36bc&redirect_uri='+encodeURIComponent(fromurl)+'&response_type=code&scope=snsapi_base&state=STATE%23wechat_redirect&connect_redirect=1#wechat_redirect';  
            location.href=url;  
        }  
        else  
        {     
            $.ajax({  
                type:'get',  
                url:ApiUrl+'/index.php?act=payment&op=getopenid',   
                async:false,  
                cache:false,  
                data:{code:access_code},  
                dataType:'json',  
                success:function(result){                   
                        if (result!=null && result.hasOwnProperty('openid') && result.openid!=""){  
                           addcookie('wxopenid',result.openid,360000);                             
                           getlogininfo(result.openid);  
                        }   
                        else  
                        {  
                          alert('微信身份识别失败 \n '+result);  
                          location.href=fromurl;  
                        }  
                    }  
                });      
        }  
    }else{  
       if (key=='' && wxopenid!='')  
           getlogininfo(wxopenid);    
    }  

    function getlogininfo(wxopenid){         
        $.ajax({  
           type:'get',  
           url: ApiUrl + '/index.php?act=login&op=autologininfo',  
           data: { wxopenid:wxopenid},  
           dataType:'json',  
           async:false,  
           cache:false,                 
           success: function (result) {                     
                   if (result.return_code=='OK'){  
                       addcookie('key',result.memberinfo.key);  
                       addcookie('username',result.memberinfo.username);  
                   }else{  
                       alert(result.return_msg);  
                       location.href=WapSiteUrl+'/tmpl/member/login.html';  
                   }  
           }  
        });  
    }  
}  



function addcookie(name,value,expireHours){  
    var cookieString=name+"="+escape(value)+"; path=/";  
    //判断是否设置过期时间  
    if(expireHours>0){  
        var date=new Date();  
        date.setTime(date.getTime+expireHours*3600*1000);  
        cookieString=cookieString+"; expire="+date.toGMTString();  
    }  
    document.cookie=cookieString;  
}  
  
function getcookie(name){  
    var strcookie=document.cookie;  
    var arrcookie=strcookie.split("; ");  
    for(var i=0;i<arrcookie.length;i++){  
    var arr=arrcookie[i].split("=");  
    if(arr[0]==name)return decodeURIComponent(arr[1]); //增加对特殊字符的解析  
    }  
    return "";  
}  
  
function delCookie(name){//删除cookie  
    var exp = new Date();  
    exp.setTime(exp.getTime() - 1);  
    var cval=getcookie(name);  
    if(cval!=null) document.cookie= name + "="+cval+"; path=/;expires="+exp.toGMTString();  
}  ;}});
