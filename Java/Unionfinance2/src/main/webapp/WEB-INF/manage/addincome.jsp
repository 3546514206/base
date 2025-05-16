<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="stylesheet" href="${rootPath }css/bootstrap.css">
<link rel="stylesheet" href="${rootPath }css/smoothness/jquery.ui.css" type="text/css" />
<link rel="stylesheet" href="${rootPath }css/style.css" type="text/css" />
<link rel="stylesheet" href="${rootPath }css/resume.css">
<link rel="shortcut icon" type="image/x-icon" href="${rootPath }img/favicon.ico" />
<script type="text/javascript">
	function su(){
		//用class名字得到input组件
			var aTd = document.getElementsByClassName('form-control');
		//confirm打印
			if(confirm("请再次确认提交信息\n\n"+
			"拨款时间:"+aTd[0].value+"\n"+
			"拨款条目:"+aTd[1].value+"\n"+
			"拨款金额:"+aTd[2].value+"\n"+
			"拨款工会:"+aTd[3].value+"\n"+
			"备注:"+aTd[4].value+"\n")){
				return true;
			}else
				return false;
	}
	
	function meg(){
		var mana="${addIncomeMsg }";
		var bal="${bal }";
		if(mana != '' && mana != null)
			alert(mana+"\n"+bal);
	}
</script>
</head>

<body onload="meg();">
<form action="${rootPath}manage/IncomeManager_addIncome" method="post" onsubmit="return su();">
	<div class="container">
		<div class="row">
			<p class="current">当前位置：首页>>拨款</p>
		</div>
	</div>
	<div id="body-one">
		<div class="container-one">
			<input type="hidden" name="income.in_operator" value="${manager.name }">
			<div class="row">
				<div
					class="col-lg-4 col-lg-offset-1 col-md-4 col-md-offset-1 col-xs-4 col-xs-offset-2"
					style="margin-left:18%;">
					<div class="col-lg-6 col-md-6  col-xs-6">
						<span>拨款时间：</span>
					</div>
					<div class="col-lg-6 col-md-6  col-xs-6">
						<input type="text" name="income.in_time" class="form-control text" id="date" required="required"/>
					</div>
				</div>
				<div class="col-lg-4 col-md-4  col-xs-4">
					<div class="col-lg-6 col-md-6  col-xs-6">
						<span>拨款条目：</span>
					</div>
					<div class="col-lg-6 col-md-6  col-xs-6" style="margin-left:-18%;">
						<select name="income.in_entry" class="sel2 form-control">
							<c:forEach items="${allEntry}" var="entry">
								<c:if test="${entry.en_type eq '0'}">
									<option value="${entry.en_name}">${entry.en_name}</option>
								</c:if>
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div
					class="col-lg-4 col-lg-offset-1 col-md-4 col-md-offset-1 col-xs-4 col-xs-offset-2"
					style="margin-left:18%;">
					<div class="col-lg-6 col-md-6  col-xs-6">
						<span>拨款金额:</span>
					</div>
					<div class="col-lg-6 col-md-6  col-xs-6">
						<input type="tel" name="income.in_money"
							pattern="^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$"
							required oninvalid="setCustomValidity('请输入正确的数字');"
							oninput="setCustomValidity('');" class="form-control" />
					</div>
				</div>
				<div class="col-lg-4 col-md-4  col-xs-4">
					<div class="col-lg-6 col-md-6  col-xs-6">
						<span>拨款工会:</span>
					</div>
					<div class="col-lg-6 col-md-6  col-xs-6" style="margin-left:-18%;">
						<select name="income.in_union" class="form-control">
							<c:forEach items = "${allLabour}" var="union">
							<option value="${union }">${union }</option>			
							</c:forEach>
						</select>
					</div>
				</div>
			</div>
			<div class="row">
				<div
					class="col-lg-8 col-lg-offset-3 col-md-8 col-md-offset-2 col-xs-8 col-xs-offset-2"
					style="margin-left:18%;">
					<div class="col-lg-2 col-md-2  col-xs-2">
						<span>备注：</span>
					</div>
					<div class="col-lg-6 col-md-6  col-xs-6" style="margin-left:-4%;">
						<textarea class="form-control" rows="5" cols="29"
							name="income.in_remark"></textarea>
					</div>

				</div>
			</div>
			<div class="row">
				<div
					class="col-lg-1 col-lg-offset-9 col-md-1 col-md-offset-9 col-xs-1 col-xs-offset-9"
					style="margin-top:-50px;">
					<button class="btn btn-primary" type="submit">提&nbsp;&nbsp;交</button>
				</div>
			</div>
		</div>
	</div>
</form>

<script src="${rootPath }js/jquery-2.1.4.js"></script>
<script src="${rootPath }js/bootstrap.js"></script>
<script type="text/javascript" src="${rootPath }js/jquery.js"></script>
<script type="text/javascript" src="${rootPath }js/jquery.ui.js"></script>
<script type="text/javascript" src="${rootPath }js/datetime.js"></script>
</body>
</html>