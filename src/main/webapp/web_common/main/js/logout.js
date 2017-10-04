
$(function() {
	$("#log_out").click( function() { 
		var outUrl = $(this).attr("lang");
		top.location = outUrl;
//		top.location = "http://test.lecommons.com:8080/portal/letv";
//		top.location = "http://lecommons.com:8080/portal/letv";

		
		//window.location.href = oururl;
		/*$.post(oururl,{
			
		}, function(d) {			
			top.location = "https://sso.test.lecommons.com/login.php?backurl=http://ips.test.lecommons.com:80/mainAction/main.action&site=cpMng";
			//top.location = "https://sso.test.lecommons.com/login.php?backurl=http://m.ips.test.lecommons.com:80/letv/mobile/indexAction/index.action&site=cpMng";
			//top.location = "https://sso.lecommons.com/login.php?backurl=http://ips.lecommons.com:80/mainAction/main.action&site=cpMng";
			//top.location = "https://sso.lecommons.com/login.php?backurl=http://m.ips.lecommons.com:80/letv/mobile/indexAction/index.action&site=cpMng";
		
			//window.location.href = "https://sso.test.lecommons.com/login.php?backurl=http://127.0.0.1:8080/letv-gcr-webapp/mainAction/main.action&site=cpMng";
		});*/
		
		
	});
});
