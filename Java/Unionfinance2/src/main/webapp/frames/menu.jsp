<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>拨款管理菜单</title>
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
        <li><a href="${rootPath }manage/UserManager" target="ifr"><img src="${rootPath }img/logo-11.png">用户管理</a></li>
        <li><a href="${rootPath }manage/EntryManager" target="ifr"><img src="${rootPath }img/logo-12.png">条目管理</a></li>
        <li><a href="${rootPath }manage/Change_addIncome" target="ifr"><img src="${rootPath }img/logo-13.png">拨款</a></li>
        <li><a href="${rootPath }manage/IncomeManager" target="ifr"><img src="${rootPath }img/logo-14.png">查看拨款</a></li>
        <li><a href="${rootPath }manage/CostManager" target="ifr"><img src="${rootPath }img/logo-15.png">查看支出</a></li>
        <li><a href="${rootPath }manage/ViewBalance" target="ifr"><img src="${rootPath }img/logo-16.png">查看余额</a></li>
        <li><a href="${rootPath }manage/Change_getMyself?identity=manager" target="ifr"><img src="${rootPath }img/logo-17.png">修改个人信息</a></li>
        <li><a href="Change_logOff?identity=manager" onclick="javascript:return exit()" target="_top"><img src="${rootPath }img/logo-18.png">退出</a></li>
    </ul>
</div>
</body>
</html>