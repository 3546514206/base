<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>查询收入情况</title>
<link rel="stylesheet" href="${rootPath }css/bootstrap.css">
<link rel="stylesheet" href="${rootPath }css/smoothness/jquery.ui.css" type="text/css" />
<link rel="stylesheet" href="${rootPath }css/manage-income.css">
<script type="text/javascript">
	function meg() {
		var mana = "${managerMsg }";
		if (mana != '' && mana != null)
			alert(mana);
	}
</script>
</head>
<body onload="meg();">
<div class="container">
		<!-- 头部 -->
		<div class="row">
			<p class="p">当前位置：首页>>查看拨款</p>
		</div>
	</div>
	<!-- 头部 -->
	<div id="body-one">
		<div class="table-responsive">
			<form action="${rootPath}ordinary/ViewIncome_Inquiry?union=${ordinary.company }" method="post">
				<!-- 上面的提交表单 -->
				<div class="row">
					<!-- 输入下拉单一行 -->
					<div
						class="col-lg-5 col-lg-offset-3 col-md-5 col-md-offset-2 col-xs-5 col-xs-offset-2 left">
						<div class="col-lg-3 col-md-3  col-xs-3">
							<span>条目：</span>
						</div>
						<div class="col-lg-8 col-md-8  col-xs-8">
							<select name="entry" class="form-control">
								<option value="">--请选择--</option>
								<c:forEach items="${allEntry}" var="entry">
								<c:if test="${entry.en_type eq '0'}">
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
						<th>拨款时间</th>
						<th>拨款条目</th>
						<th>金额</th>
						<th>经手人</th>
						<th>拨款工会</th>
						<th>备注</th>
					</tr>
				</thead>
				<tbody>
				
				<c:forEach items="${allIncome.data}" var="pageCut" varStatus="status">
							<tr>	
								<td>${status.index + 1}</td>
								<td>${pageCut.in_time}</td>
								<td>${pageCut.in_entry}</td>
								<td>${pageCut.in_money}</td>
								<td>${pageCut.in_operator}</td>
								<td>${pageCut.in_union}</td>
								<td>${pageCut.in_remark}</td>		
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="container-two">
			<!-- container-two 底部-->
			<div class="row">
				<!-- 小字信息一行 -->
				<em>页次${allIncome.currentPage }/${allIncome.pageNum }</em>
				<div
					class="col-lg-5 col-lg-offset-5 col-md-5 col-md-offset-4 col-xs-5 col-xs-offset-4">
					<span>本页面拨款总额 <strong>${presum }</strong>元
					</span> <span>本次查询拨款总额<strong>${sum }</strong>元
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
						<li><a href="${rootPath }ordinary/ViewIncome_${adss}?page=1&union=${ordinary.company}">首页</a></li>
						<li><a
							href="${rootPath }ordinary/ViewIncome_${adss}?page=${allIncome.prePage}&union=${ordinary.company}">上一页</a></li>
						<c:forEach var="i"
							begin="${allIncome.currentPage-3>0?allIncome.currentPage-3:1 }"
							end="${allIncome.currentPage+3>allIncome.pageNum?allIncome.pageNum:allIncome.currentPage+3  }">
							<c:choose>								
								<c:when test="${i>0 && i == allIncome.currentPage}">
									<li class="active"><a href="${rootPath }ordinary/ViewIncome_${adss}?page=${i }&union=${ordinary.company}">${i}</a></li>
								</c:when>
								<c:when test="${i>0 && i != allIncome.currentPage}">
									<li><a href="${rootPath }ordinary/ViewIncome_${adss}?page=${i }&union=${ordinary.company}">${i}</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<li><a
							href="${rootPath }ordinary/ViewIncome_${adss}?page=${allIncome.nextPage}&union=${ordinary.company}">下一页</a></li>
						<li><a
							href="${rootPath }ordinary/ViewIncome_${adss}?page=${allIncome.pageNum }&union=${ordinary.company}">尾页</a></li>
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
</body>
</html>