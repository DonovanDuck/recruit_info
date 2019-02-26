<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>添加用户信息</title>
<style>
	.personalInfo_head{
		height: 70px;
   		width: 190px;
   		position: relative;
    left: 26%;
	}
	.personalInfo_main{
		margin: 60px;
		
	}
	.personalInfo_form{
		width: 500px;
    margin-left: 26%;
	}
	.form-group{
		margin-bottom: 40px;
	}
	.confirm{
		width: 50px;
		position: relative;
		left: 10px;
		height: 40px;
		margin-right: 10px;
	}
</style>
<script>
	$(function(){
		$("#confirmNewPassword").blur(function(){
			var newPassword = $("#newPassword").val();
			var confirmNewPassword = $("#confirmNewPassword").val();
			if(newPassword != confirmNewPassword){
				alert("两次密码输入不一致！");
			}
		});
	})
	
</script>

<script>
	$(function(){
		$("#password").blur(function(){
			var password = $("#password").val();
			$.ajax({
				async:false,
				cache:false,
				url:"${pageContext.request.contextPath}/admin/ajaxCheckPassword",
				data:{'password':password},
				type:"POST",
				dataType:"text",
				success:function(result) {
					//alert(eval(result));
					if(eval(result) == "ERROR"){
						alert("原密码输入错误！");
					}
				}
			}); 
		});
	})
	
</script>

</head>
<body>
	<div class="personalInfo_main">
		<div class="personalInfo_head">
			<h2>XX个人信息</h2>
		</div>
		<div class="personalInfo_form">
			<form class="form-horizontal">
				<div class="form-group">
				    <label for="password" class="col-sm-2 control-label" style="padding-right: 31px;">原密码</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="password" placeholder="原密码">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="newPassword" class="col-sm-2 control-label" style="padding-right: 31px;">新密码</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="新密码">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="confirmNewPassword" class="col-sm-2 control-label">确认密码</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="confirmNewPassword" placeholder="确认密码">
				    </div>
  				</div>
  				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10 confirm" style="float: left">
				      <button type="submit" class="btn btn-success">确定</button>
				    </div>
				    <div class="col-sm-offset-2 col-sm-10 confirm">
				      <button type="text" class="btn btn-default">取消</button>
				    </div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>