<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title></title>
	<link rel="stylesheet" href="${rootPath }css/bootstrap.css">
	<link rel="stylesheet" href="${rootPath }css/smoothness/jquery.ui.css" type="text/css" />
	<link rel="stylesheet" href="${rootPath }css/manage-income.css">
	<script language="javascript">
    function del() { 
		 var msg = "您真的确定要删除吗？\n\n请确认！"; 
		 if (confirm(msg)==true){ 
		  return true; 
		 }else{ 
		  return false; 
		 } 
	}
	function meg() {
		var mana = "${managerMsg }";
		var upda = "${updateCostMsg }";
		var dele = "${deleteCostMsg }"; 
		if (mana != '' && mana != null)
			alert(mana);
		else if (upda != '' && upda != null)
			alert(upda);
		else if (dele != '' && dele != null)
			alert(dele);
	}
	
</script>
</head>
<body>

<body onload="meg();">
	<div class="container">
		<!-- 头部 -->
		<div class="row">
			<div
				class="col-lg-1 col-lg-offset-1 col-md-2  col-xs-2 col-xs-offset-1">
				 <a href="${rootPath }cost/CostManager_export"> <button class="btn btn-primary btn1" type="button">
				 <img src="${rootPath }img/logo.png" width="25" height="25">&nbsp;&nbsp;备份数据</button></a>
			</div>
			<p class="p">当前位置：首页>>查看支出</p>
		</div>
	</div>
	<!-- 头部 -->
	<div id="body-one">
		<div class="table-responsive">
			<form action="${rootPath}manage/CostManager_Inquiry" method="post">
				<!-- 上面的提交表单 -->
				<div class="row">
					<!-- 输入下拉单一行 -->
					<div
						class="col-lg-5 col-lg-offset-3 col-md-5 col-md-offset-2 col-xs-5 col-xs-offset-2 left">
						<div class="col-lg-2 col-md-2  col-xs-2">
							<span>工会：</span>
						</div>
						<div class="col-lg-8 col-md-8  col-xs-8">
							<select name="union" class="form-control">
								<option value="">--请选择--</option>
								<c:forEach items="${allLabour}" var="union">
									<option value="${union }">${union }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="col-lg-5 col-md-5  col-xs-5 list">
						<div class="col-lg-2 col-md-2  col-xs-2">
							<span>条目：</span>
						</div>
						<div class="col-lg-8 col-md-8  col-xs-8">
							<select name="entry" class="form-control">
								<option value="">--请选择--</option>
								<c:forEach items="${allEntry}" var="entry">
								<c:if test="${entry.en_type eq '1'}">
									<option value="${entry.en_name}">${entry.en_name}</option>
								</c:if>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<!-- 输入下拉单一行 -->

				<div class="row">
					<!-- 输入时间框一行 -->
					<div
						class="col-lg-3 col-lg-offset-3 col-md-3 col-md-offset-2 col-xs-3 col-xs-offset-2 left-time">
						<input type="text" name="starttime" placeholder="请输入开始的时间"
							value="" class="form-control text" id="date">
					</div>
					<div
						class="col-lg-3 col-lg-offset-3 col-md-3 col-md-offset-2 col-xs-3 col-xs-offset-2 left">
						<input type="text" name="endtime" placeholder="请输入截止的时间" value=""
							class="form-control text" id="date2">
					</div>
					<input type="submit" name="" class="btn btn-primary btn2">
				</div>
				<!-- 输入时间框一行 -->
			</form>
			<!-- 上面的提交表单 -->
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr class="two">
						<th>序号</th>
						<td>支出时间</td>
						<td>支出条目</td>
						<td>金额</td>
			            <td>经手人</td>
			            <td>报账人</td>
			            <td>支出工会</td>
			            <td>备注</td>
					</tr>
				</thead>
				<tbody>
				
				<c:forEach items="${allCost.data}" var="pageCut" varStatus="status">
							<tr>	
								<td>${status.index + 1}</td>
								<td>${pageCut.co_time}</td>
								<td>${pageCut.co_entry}</td>
								<td>${pageCut.co_money}</td>
								<td>${pageCut.co_operator}</td>
								<td>${pageCut.co_fortor}</td>
								<td>${pageCut.co_union}</td>
								<td>${pageCut.co_remark}</td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="container-two">
			<!-- container-two 底部-->
			<div class="row">
				<!-- 小字信息一行 -->
				<em>页次${allCost.currentPage }/${allCost.pageNum }</em>
				<div
					class="col-lg-5 col-lg-offset-5 col-md-5 col-md-offset-4 col-xs-5 col-xs-offset-4">
					<span>本页面支出总额 <strong>${presum }</strong>元
					</span> <span>本次支出总额<strong>${sum }</strong>元
					</span>
				</div>
			</div>
			<!-- 小字信息一行 -->
			<div class="row">
				<!-- 分页一行 -->
				<div
					class="col-lg-5 col-lg-offset-5 col-md-6 col-md-offset-4 col-xs-6 col-xs-offset-4">
					<nav>
						<ul class="pagination">
					 	<li><a href="${rootPath }manage/CostManager_${adss}?page=1">首页</a></li>
					 	<li><a href="${rootPath }manage/CostManager_${adss}?page=${allCost.prePage}">上一页</a></li>
						<c:forEach var="i" begin="${allCost.currentPage-3>0?allCost.currentPage-3:1 }"
		 					end="${allCost.currentPage+3>allCost.pageNum?allCost.pageNum:allCost.currentPage+3  }">
							<c:choose>
								<c:when test="${i>0 && i == allCost.currentPage}">
									<li class="active"><a href="${rootPath }manage/CostManager_${adss}?page=${i }">${i}</a></li>
								</c:when>
								<c:when test="${i>0 && i != allCost.currentPage}">
									<li><a href="${rootPath }manage/CostManager_${adss}?page=${i }">${i}</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<li><a href="${rootPath }manage/CostManager_${adss}?page=${allCost.nextPage}">下一页</a></li>
						<li><a href="${rootPath }manage/CostManager_${adss}?page=${allCost.pageNum }">尾页</a></li>
					 </ul>
					</nav>
				</div>
			</div>
			<!-- 分页一行 -->
		</div>
		<!-- container-two 底部-->
	</div>
<script src="${rootPath }js/jquery-2.1.4.js"></script>
<script src="${rootPath }js/bootstrap.js"></script>
<script type="text/javascript" src="${rootPath }js/jquery.js"></script>
<script type="text/javascript" src="${rootPath }js/jquery.ui.js"></script>
<script type="text/javascript" src="${rootPath }js/datetime.js"></script>
	<!-- 时间选择器的js -->
</html>
