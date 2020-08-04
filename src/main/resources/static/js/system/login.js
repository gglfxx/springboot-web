var isRember = false; //未记住密码
var ifPassImgCode = false; //是否跳过验证码登录
window.onload = function () {
	var username = getCookie("username");
	var remember = getCookie("remember");
	var password = getCookie("password");
	if(remember){
		$("input[name='account']").val(username);
		$("input[name='password']").val(password);
		$("#rememberPass").click();
	}
}
document.onkeydown = function(e){
    if(e.keyCode == 13){
    	login();
    }
}
//重置按钮  功能 账号密码清空
$(".reset").click(function () {
	$(".loginForm input").val("");
});
//记住密码
$("#rememberPass").click(function () {
    if (!isRember) {
        $(this).find("i").css("background", "url(../images/q_gou.png) no-repeat center");
    } else {
        $(this).find("i").css("background", "none");
    }
    isRember = !isRember;
});
//存入cookie
var setMessage = function (username,password) {
    var d = new Date();
    d.setTime(d.getTime() + (90 * 24 * 60 * 60 * 1000));
    document.cookie = "username=" + username + "; expires=" + d.toGMTString() + "";
    document.cookie = "remember=" + true + "; expires=" + d.toGMTString() + "";
};
//删除cookie
var removeMessage = function () {
	var expires = -1;
    document.cookie = "username=; expires=" + expires + "";
    document.cookie = "remember=; expires=" + expires + "";
    //document.cookie = "password=; expires=" + d.toGMTString() + "";
};

//读取cookie
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)"); //正则匹配
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else {
        return "";
    }
}

/*
 * 登录
 * 账号密码登录
 */
function login() {
    var account = $("input[name='username']").val();
    var password = $("input[name='password']").val();
    $.ajax({
        type:"post",
        url: '/user/login',
        data: {username:account,
        	   password:password,
        	},
        dataType:"json",
        async: false,
        success:function(res){
        	console.log(res);
        	if(res.code==0){
        		/*if(isRember){
        			setMessage(account,password);
        		}else{
        			removeMessage();
        		}*/
        		location.href ='/index';
        	}else{
        		
        	}
        }
    }); 
   
}


//硬处理ie6 bug
//兼容性 如果是ie6
var browerIESix = function () {
    if ($.browser.msie) {
        if ($.browser.version == 6) {
            return "ie6";
        } else if ($.browser.version == 7 || $.browser.version == 8) { //兼容性 如果是ie8以下
            return "ig8";
        }
    } else {
        return "normal";
    }
};
