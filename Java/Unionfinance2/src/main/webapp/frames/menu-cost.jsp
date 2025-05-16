<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>支出管理菜单</title>
	  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	  <link rel="stylesheet" type="text/css" href="${rootPath }css/layui.css">
	  <link rel="stylesheet" type="text/css" href="${rootPath }css/menu.css">
	  <script type="text/javascript">
	  	function exit() { 
			 var msg = "您真的确定要退出吗？\n\n请确认！"; 
			 if (confirm(msg)==true) 
			  return true; 
			 else 
			  return false;
	}
	  </script>
</head>
<body>
<div class="my-side">
    <ul class="layui-nav">
        <li><a href="${rootPath }cost/Change_addCost" target="ifr"><img src="${rootPath }img/logo_1.png">支出</a></li>
        <li><a href="${rootPath }cost/IncomeManager" target="ifr"><img src="${rootPath }img/logo_2.png">查看拨款</a></li>
        <li><a href="${rootPath }cost/CostManager" target="ifr"><img src="${rootPath }img/logo_3.png">查看支出</a></li>
        <li><a href="${rootPath }ViewBalance" target="ifr"><img src="${rootPath }img/logo_4.png">查看余额</a></li>
        <li><a href="${rootPath }Change_getMyself?identity=cost" target="ifr"><img src="${rootPath }img/logo_5.png">修改个人信息</a></li>
        <li><a href="${rootPath }Change_logOff?identity=cost" onclick="javascript:return exit()" target="_top"><img src="${rootPath }img/logo_6.png">退出</a></li>
    </ul>
</div>
</body>
</html>