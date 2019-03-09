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
	<link
	href="${pageContext.request.contextPath}/css/Admin/font-awesome.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/Admin/morris/morris-0.4.3.min.css"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link
	href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Admin/css/materialize.min.css"
	media="screen,projection" />
<link href="${pageContext.request.contextPath}/css/Admin/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/Admin/custom-styles.css"
	rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Admin/css/cssCharts.css">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>添加用户信息</title>
<style>
	.personalInfo_head{
		height: 70px;
   		width: 300px;
   		position: relative;
    left: 26%;
	}
	.personalInfo_main{
		
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
				url:"${pageContext.request.contextPath}/user/ajaxCheckPassword",
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
<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
			<div class="text-center">
				<h2>新疆招聘信息系统</h2>
			</div>
		</nav>
		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
			<div class="sidebar-collapse">
				<ul class="nav" id="main-menu">
					<li class="text-left"><a
						href="${pageContext.request.contextPath}/user/toMainPage"
						class="waves-effect waves-dark">招聘信息</a></li>
					<c:if test="${sessionScope.User.authority == 2 }">
					<li class="text-left"><a
						href="${pageContext.request.contextPath}/user/toUserInfo"
						class="waves-effect waves-dark">用户管理</a></li>
					</c:if>
					<li class="text-left"><a
						href="${pageContext.request.contextPath}/user/toPersonalInfo"
						class="waves-effect waves-dark">个人信息</a></li>
				</ul>
			</div>
		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="col-md-12">
					<div class="card">
						<div class="card-content " style="padding-top: 0%">
	<div class="personalInfo_main">
		<div class="personalInfo_head">
			<h2>${sessionScope.User.userName }个人信息</h2>
		</div>
		<div class="personalInfo_form">
			<form class="form-horizontal" action="${pageContext.request.contextPath}/user/modifyPassword">
				<div class="form-group">
				    <label for="password" class="col-sm-2 control-label" style="padding-right: 31px;color: #000">原密码</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="password" placeholder="原密码">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="newPassword" class="col-sm-2 control-label" style="padding-right: 31px;color: #000">新密码</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="新密码">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="confirmNewPassword" class="col-sm-2 control-label" style="color: #000">确认密码</label>
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
	</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>