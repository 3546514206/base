<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>条目管理</title>
<link rel="stylesheet" href="${rootPath }css/bootstrap.css">
<link rel="stylesheet" href="${rootPath }css/table-four.css">
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="${rootPath }js/manage-entry.js"></script>
<script type="text/javascript">
	function meg(){
		var mana="${managerMsg }";
		var upda="${updateEntryMsg }";
		var dele="${deleteEntryMsg }";
		var add="${addEntryMsg}"
		if(mana != '' && mana != null)
			alert(mana);
		else if(upda != '' && upda != null)
			alert(upda);
		else if(dele != '' && dele != null)
			alert(dele);
		else if(add != '' && add != null)
			alert(add);
	}
</script>
</head>

<body onload="meg();">
<div class="container">
  <div class="row">
     <div class="col-lg-1 col-lg-offset-1 col-md-2  col-xs-2 col-xs-offset-1">
             <a href="javascript:void(0)" target="ifr"> <button class="btn btn-primary" type="button" onclick="press()"><img src="${rootPath }img/yonghuzengjia.png" width="25" height="25">增加条目</button></a>
        </div>
            <p>当前位置：首页>>条目管理</p>
  </div>
</div>
<div id="body-one">
 <div class="container-one">
  <div class="row">
   <form action="" method="post" name="form1" onsubmit="return check();">
   			<span class="type">类型：</span>
           <select class="form-control aa" name="pass">
				<option value="">--请选择--</option>
				<option value="0">拨款</option>
				<option value="1">支出</option>
			</select>
			<input type="text" name="replace" class="form-control bb" placeholder="请输入条目名称即可" value="">
           <button class="btn btn-primary" type="submit">提&nbsp;&nbsp;交</button> 
     </form>
      </div>
 </div>
  <div class="table-responsive">
    <table class="table table-bordered table-hover table-striped">
    <thead>
      <tr class="two">
         <th>序号</th>
         <th>名称</th>
         <th>类型</th>
         <th>编辑</th>
       </tr>
      </thead>
      <tbody>
        <c:forEach items="${allEntry}" var="pageCut" varStatus="status">
			<tr>
				<td>${status.index + 1}</td>
				<td class="hidd">${pageCut.en_id }</td>
				<td>${pageCut.en_name}</td>
				<td><c:choose>
					<c:when test="${pageCut.en_type == 0}">拨款</c:when>
					<c:when test="${pageCut.en_type == 1}">支出</c:when>
				</c:choose></td>
				<td class="four">
					<img src="${rootPath }img/bian.png"><a href="javascript:void(0)" target="ifr" class="input">修改</a>&nbsp; &nbsp; &nbsp;&nbsp;&nbsp;
					<img src="${rootPath }img/lajitong.png"><a href="${rootPath}manage/EntryManager_deleteEntry?entry.en_id=${pageCut.en_id}" onclick="javascript:return del()">删除</a>
				</td>
			</tr>
		</c:forEach>
      </tbody>
    </table>
       
    <!-- 增加div -->
    <div class="myaddform" id="myaddform" >
    	<form action="${rootPath}manage/EntryManager_addEntry" method="post" onSubmit="return su();">
    		<label class="form-inline">名称：
    		<input type="text" class="form-control name" size=30; name="entry.en_name" required/></label>
   			  <label class="form-inline">类型：
   			    <select name="entry.en_type" class="form-control typee">
    					<option value="1">支出</option>
    					<option value="0">拨款</option>
    		    </select>
    		</label>
			 <div class="row">
			 	<button class="btn btn-primary btnn" type="submit">提&nbsp;&nbsp;交</button>
			 <a href="${rootPath }manage/EntryManager" target="ifr">
			 <button class="btn btn-primary" type="button">返&nbsp;&nbsp;回</button></a>
			 </div>
    	</form>
    </div>
    
    <!-- 修改div -->
    <div class="myform" id="myform">
    	<form action="${rootPath}manage/EntryManager_updateEntry" method="post">
    		<input type="hidden" name="entry.en_id" class="id"/>
    		<label class="form-inline">名称：<input type="text" class="form-control name" size=30; name="entry.en_name"/></label>
    		<label class="form-inline">类型：<select name="entry.en_type" class="form-control">
    					<option class="type"></option>
    					<option value="支出">支出</option>
    					<option value="拨款">拨款</option>
    		</select>
    		</label>
    		<input type="submit" class="btn btn-info" value="确认修改"/>
    	</form>
    </div>
</div>
</div>

<script language="javascript">
		$('.input').click(function(){
			$('.myform').show();
			var id = $(this).parent().parent().find("td").eq(1).text();
			var name = $(this).parent().parent().find("td").eq(2).text();
			var type = $(this).parent().parent().find("td").eq(3).text();
			var t = type.trim();
			$('.id').val(id);
			$('.name').val(name);
			$('.type').text(t);
		})
 		Drag('myform');
		Drag('myaddform');
</script>	



</body>
</html>