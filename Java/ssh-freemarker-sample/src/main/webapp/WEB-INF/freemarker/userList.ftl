<!DOCTYPE html>
<html>
<head><title>用户列表</title></head>
<body>
<h2>用户列表</h2>
<table border="1">
<tr><th>ID</th><th>姓名</th><th>邮箱</th></tr>
<#list users as user>
<tr>
    <td>${user.id}</td>
    <td>${user.name}</td>
    <td>${user.email}</td>
</tr>
</#list>
</table>
<a href="addUser.ftl">添加用户</a>
</body>
</html>