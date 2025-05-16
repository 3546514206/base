<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>分会主席菜单</title>
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
        <li><a href="${rootPath }ordinary/ViewIncome?union=${ordinary.company }" target="ifr"><img src="${rootPath }img/logo-1.png">查看拨款</a></li>
        <li><a href="${rootPath }ordinary/ViewCost?union=${ordinary.company }" target="ifr"><img src="${rootPath }img/logo-2.png">查看支出</a></li>
        <li><a href="${rootPath }ordinary/ViewBalance_Inquiry?union=${ordinary.company }" target="ifr"><img src="${rootPath }img/logo-3.png">查看余额</a></li>
        <li><a href="${rootPath }ordinary/Change_getMyself?identity=ordinary" target="ifr"><img src="${rootPath }img/logo-4.png">修改个人信息</a></li>
        <li><a href="${rootPath }Change_logOff?identity=ordinary" onclick="javascript:return exit()" target="_top"><img src="${rootPath }img/logo-5.png">退出</a></li>
    </ul>
</div>
</body>
</html>