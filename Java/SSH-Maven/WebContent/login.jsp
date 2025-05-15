<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>login page</title>
  </head>
  <body>
  	<h3>登录</h3>
  	<span style="color:red"><s:actionerror/></span>
  	<form method="post" action="<%=path%>/loginAction.action">
	    <span>用户名：</span><input type="text" name="username" value="${username}"/><br/>
	    <span>密码&nbsp：</span><input type="password" name="password" value="${password}"/><br/>
	    <input type="submit" style="height:25px;width:237px" value="登录" />
    </form>
    <a href="<%=path%>/register.jsp">注册页面</a>
  </body>
</html>
