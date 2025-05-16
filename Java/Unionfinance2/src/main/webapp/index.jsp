<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
<meta name=”renderer” content=”Webkit”>
<meta charset="utf-8">
<meta name=”renderer” content=”webkit|ie-comp|ie-stand”>
<title>登录界面</title>
<link rel="stylesheet" href="${rootPath }css/bootstrap.css">
<link rel="stylesheet" href="${rootPath }css/index-one.css">
<link rel="icon" href="${rootPath }img/top.ico" type="image/x-icon"/>
<script type="text/javascript">
 		function reloadCode(){
 			var time=new Date().getTime();
			document.getElementById("imagecode").src="verifyCode?d="+time;
 	}
 		function relog(){
 		var meg = "${loginMeg}";
 			if(meg != '' && meg != null)
 				alert(meg);
 		}
</script>
</head>
<body onload="relog();">
<div class="container">
    <div class="header">
        <p>河南科技学院工会财务管理系统</p>
    </div>
    <div class="body">
        <div class="box">
            <div class="input-two">
            	
                <form class="form-inline" id="form" method="post" action="loginAction">
                    <div class="label-two">
                        <label for="account" >账&nbsp;&nbsp;&nbsp;&nbsp;号</label>
                        <input type="text" class="form-control"  placeholder="请输入用户名" name="user.account">
                    </div> 
                    <div class="label-two">
                        <label for="password" >密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
                        <input type="password" class="form-control"  placeholder="请输入密码" name="user.password">
                    </div>
                    <div class="label-two">
                        <label for="password">验证码</label>
                        <input type="text" class="form-control yan"  placeholder="输入验证码" name="randStr" style="width:30%">
                        <img border=0 src="verifyCode" id="imagecode" onclick="reloadCode()" class="image1">
                    </div>
                </form>
            </div>
            <div class="input-four">
                <button class="btn  btn-primary one" form="form" type="submit">登&nbsp;&nbsp;录</button>
            </div>
        </div>
    </div>
</div>

<script src="${rootPath }js/jquery-2.2.3.min.js"></script>
<script src="${rootPath }js/bootstrap.js"></script>
</body>
</html>
