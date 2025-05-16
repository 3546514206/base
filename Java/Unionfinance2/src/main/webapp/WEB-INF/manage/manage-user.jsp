<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>牛5</title>
<link rel="stylesheet" href="${rootPath }css/bootstrap.css">
<link rel="stylesheet" href="${rootPath }css/table-five.css">
<script language="javascript">
    function del() { 
		 var msg = "您真的确定要删除吗？\n\n请确认！"; 
		 if (confirm(msg)==true){ 
		  return true; 
		 }else{ 
		  return false; 
		 } 
} 
	function meg(){
		var mana="${managerMsg }";
		var upda="${updateUserMsg }";
		var dele="${deleteUserMsg }";
		if(mana != '' && mana != null)
			alert(mana);
		else if(upda != '' && upda != null)
			alert(upda);
		else if(dele != '' && dele != null)
			alert(dele);
	}
	function check(){
	var a = document.getElementsByName('condition')[0];
	if(a.value == "" || a.value == null)
	document.form1.action="${rootPath }manage/UserManager"
	else
	document.form1.action="${rootPath}manage/UserManager_Inquiry"
	}
</script>
</head>

<body onload="meg();">
<div class="container">
  <div class="row">
     <div class="col-lg-1 col-lg-offset-1 col-md-2  col-xs-2 col-xs-offset-1">
             <a href="${rootPath }Change_addUser"" target="ifr"><button class="btn btn-primary" type="button"><img src="${rootPath }img/yonghuzengjia.png" width="25" height="25">增加用户</button></a>
        </div>
           <p>当前位置：首页>>用户管理</p>
  </div>
</div>
<div id="body-one">
  <div class="table-responsive">
  		<form action="" method="post" onSubmit="return check();" name="form1">
			<input type="text" name="condition" class="search" placeholder="输入账号、身份、姓名其中之一即可">
			<input type="submit" class="btn btn-primary" value="搜索">
		</form>
    <table class="table table-bordered table-hover table-striped">
    <thead>
      <tr class="two">
         <th>序号</th>
         <th>账号</th>
         <!-- <th>密码</th> -->
         <th>身份</th>
         <th>姓名</th>
         <th>手机</th>
         <th>所属分会</th>
         <th>编辑</th>
       </tr>
      </thead>
      <tbody>
          <c:forEach items="${allUser}" var="pageCut" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td>${pageCut.account}</td>
				<%-- <td>${pageCut.password}</td> --%>
				<td>
					<c:if test="${pageCut.identity eq 'manage' }">拨款管理</c:if>
					<c:if test="${pageCut.identity eq 'cost' }">支出管理</c:if>
					<c:if test="${pageCut.identity eq 'ordinary' }">分会主席</c:if>	
				</td>
				<td>${pageCut.name}</td>
				<td>${pageCut.number}</td>
				<td>${pageCut.company}</td>
				<td class="four">
				<c:choose>
					<c:when test="${pageCut.id == 1 }"><a href="${rootPath }Change_getMyself?identity=manager" ><img src="${rootPath }img/bian.png" style="color:red;">修改&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;</a></c:when>
					<c:otherwise><a href="${rootPath}manage/UserManager_toUpdateUser?userId=${pageCut.id}"><img src="${rootPath }img/bian.png" style="color:red;">修改&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;</a></c:otherwise>
				</c:choose>
					<a href="${rootPath}manage/UserManager_deleteUser?user.id=${pageCut.id}" onclick="javascript:return del()" style="color:#E11E05;"><img src="${rootPath }img/lajitong.png">删除</a>
				</td>
			</tr>
		</c:forEach> 
    	</tbody>
    </table>
</div>
<!-- <div class="container-two"> -->
<!--   <div class="row"> -->
<!--    <div class="col-lg-5 col-lg-offset-5 col-md-5 col-md-offset-4 col-xs-5 col-xs-offset-4"> -->
<%--            <p class="xixnxi">每页显示条，总记录数${allUser.count }条</p> --%>
<!--         </div> -->
<!--       </div> -->
<!--       <div class="row"> -->
<!--         <div class="col-lg-5 col-lg-offset-5 col-md-6 col-md-offset-4 col-xs-6 col-xs-offset-4"> -->
<!--            <nav> -->
<!--           <ul class="pagination"> -->
<%--             <li><a href="${rootPath }manage/UserManager_${adss}?page=1">首页</a></li> --%>
<%-- 			 	<li><a href="${rootPath }manage/UserManager_${adss}?page=${allUser.prePage}">上一页</a></li> --%>
<%-- 				<c:forEach var="i" begin="${allUser.currentPage-3>0?allUser.currentPage-3:1 }" --%>
<%--  					end="${allUser.currentPage+3>allUser.pageNum?allUser.pageNum:allUser.currentPage+3  }"> --%>
<%-- 					<c:choose> --%>
<%-- 						<c:when test="${i>0 && i == allUser.currentPage}"> --%>
<%-- 							<li><a href="${rootPath }manage/UserManager_${adss}?page=${i }">${i}</a></li> --%>
<%-- 						</c:when> --%>
<%-- 						<c:when test="${i>0 && i != allUser.currentPage}"> --%>
<%-- 							<li><a href="${rootPath }manage/UserManager_${adss}?page=${i }">${i}</a></li> --%>
<%-- 						</c:when> --%>
<%-- 					</c:choose> --%>
<%-- 				</c:forEach> --%>
<%-- 				<li><a id="prev" href="${rootPath }manage/UserManager_${adss}?page=${allUser.nextPage}">下一页</a></li> --%>
<%-- 				<li><a id="next" href="${rootPath }manage/UserManager_${adss}?page=${allUser.pageNum }">尾页</a></li> --%>
<%-- 				  <span class="cur">当前是第<em><c:choose> --%>
<%--      	 	<c:when test="${allUser.prePage == allUser.nextPage  }">${allUser.prePage }</c:when> --%>
<%--      	 	<c:when test="${allUser.prePage !=1}">${allUser.prePage+1 }</c:when> --%>
<%--      	 	<c:otherwise>${allUser.nextPage -1}</c:otherwise> --%>
<%--      	 </c:choose></em>页</span> --%>
<!--           </ul> -->
        
<!--         </nav> -->
<!--         </div> -->
<!--       </div> -->
<!-- </div> -->
</div>
</body>
</html>