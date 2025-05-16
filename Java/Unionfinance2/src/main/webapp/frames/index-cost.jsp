<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<meta name=”renderer” content=”webkit|ie-comp|ie-stand”>
	<title>支出管理后台</title>
<link rel="icon" href="${rootPath }img/top.ico" type="image/x-icon"/>
</head>
<frameset rows="20%,80%"  cols="100%" frameborder="0" border="0" framespacing="0" name="head">
	<frame  src="${rootPath }frames/head.jsp" scrolling="no" frameborder="0"></frame>
	<frameset cols="15%,85%"  frameborder="0"  framespacing="0" name="caidan">
		<frame src="${rootPath }frames/menu-cost.jsp" scrolling="no" frameborder="0"></frame>
    	<frame src="${rootPath }cost/Change_addCost"  frameborder="1" name="ifr"/></frame>
    </frameset>
  </frameset>
   <noframes>Sorry, your browser does not handle frames!</noframes>
</html>