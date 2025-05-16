<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息</title>
	<link rel="stylesheet" href="${rootPath }css/bootstrap.css">
	<link rel="stylesheet" href="${rootPath }css/ma-four.css">
	<script type="text/javascript">
		function su(){
		//用class名字得到input组件
			var aTd = document.getElementsByClassName('form-control');
		//confirm打印
			if(confirm("请再次确认提交信息\n\n"+
			"密码:"+aTd[0].value+"\n"+
			"姓名:"+aTd[1].value+"\n"+
			"手机号码:"+aTd[2].value)){
				return true;
			}else
				return false;
			
	}
	function meg(){
		var mana="${updateUserMsg  }";
		if(mana != '' && mana != null)
			alert(mana);
	}
	</script>
</head>
<body onload="meg();">
<form action="${rootPath}manage/UserManager_updateUser" method="post" onSubmit="return su();">
<div class="container">
  <div class="row">
        <div>
            <p>当前位置：首页>>修改个人信息</p>
        </div>
  </div>
</div>
<div id="body-one">
 <div class="container-one">
   <div class="row">
   	<input type="hidden" name="user.id" value="${user.id}">
   	<input type="hidden" name="user.account" value="${user.account}">
   	<input type="hidden" name="user.identity" value="${user.identity}">
   	<input type="hidden" name="user.company" value="${user.company}">
      <div class="col-lg-4 col-lg-offset-3 col-md-4 col-md-offset-2 col-xs-4 col-xs-offset-2" style="margin-left:18%;">
        <div class="col-lg-4 col-md-6  col-xs-6">
            <span>密码：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
			<input name="user.password" type="text" value="${user.password}" required class="form-control">
         </div>
      </div>
      <div class="col-lg-4 col-md-4  col-xs-4">
        <div class="col-lg-4 col-md-6  col-xs-6">
            <span>姓名：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input name="user.name" type="text" value="${user.name}" required class="form-control">
         </div>
      </div>
   </div>
   <div class="row">
      <div class="col-lg-4 col-lg-offset-3 col-md-4 col-md-offset-2 col-xs-4 col-xs-offset-2" style="margin-left:18%;">
        <div class="col-lg-4 col-md-6  col-xs-6">
            <span>手机号：</span>
        </div>
        <div class="col-lg-6 col-md-6  col-xs-6">
           <input type="tel" name="user.number" class="form-control" pattern="^1[3-9]\d{9}$" required 
   			 		oninvalid="setCustomValidity('请输入11位手机号');" oninput="setCustomValidity('');"
   			 		placeholder="请正确输入11位手机号码" value="${user.number}"/>
         </div>
      </div>
   </div>
     <div class="row">
      <div class="col-lg-1 col-lg-offset-9 col-md-1 col-md-offset-9 col-xs-1 col-xs-offset-9">
         <button class="btn btn-primary" type="submit">提&nbsp;&nbsp;交</button>
      </div>
   </div>
 </div>
</div>
</form>
</body>
<script src="${rootPath }js/jquery-2.1.4.js"></script>
<script src="${rootPath }js/bootstrap.js"></script>
</html>