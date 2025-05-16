<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>头部</title>
<link rel="stylesheet" type="text/css" href="${rootPath }css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${rootPath }css/head.css">
<script type="text/javascript">
	function showTime(){
		    nowtime=new Date();
		    year=nowtime.getFullYear();
		    month=nowtime.getMonth()+1;
		    date=nowtime.getDate();
		    document.getElementById("mytime").innerText=year+"年"+month+"月"+date+"日"+nowtime.toLocaleTimeString();
		}
		setInterval("showTime()",1000);
</script>
</head>
<body>
<div class="head"> 
<div class="header"><!-- 头部 -->
	<img src="${rootPath }img/header-logo.png" class="logo">
	<span class="logo-text">河南科技学院工会财务管理系统</span>
	<div class="header-content">
	   <span>今天是</span><span><span id="mytime"></span>,欢迎${user.name }登录</span>
	  </div><!-- header-content -->
   </div><!-- 头部 -->
</div>
</body>
</html>