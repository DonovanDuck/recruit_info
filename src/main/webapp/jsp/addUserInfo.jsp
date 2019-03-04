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
	
<link href="${pageContext.request.contextPath}/css/Admin/bootstrap.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/Admin/custom-styles.css"
	rel="stylesheet" />

<title>添加用户信息</title>
<style>
	.addUserInfo_head{
		height: 70px;
   		width: 190px;
   		position: relative;
    left: 27%;
	}
	.addUserInfo_main{
		margin: 60px;
		
	}
	.addUserInfo_form{
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
						class="waves-effect waves-dark" style="font-size: 20px">招聘信息</a></li>
					<li class="text-left"><a
						href="${pageContext.request.contextPath}/user/toUserInfo"
						class="waves-effect waves-dark" style="font-size: 20px">用户管理</a></li>
					<li class="text-left"><a
						href="${pageContext.request.contextPath}/user/toPersonalInfo"
						class="waves-effect waves-dark" style="font-size: 20px">个人信息</a></li>
				</ul>
			</div>
		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="col-md-12">
					<div class="card">
						<div class="card-content " style="padding-top: 0%">
	<div class="addUserInfo_main">
		<div class="addUserInfo_head">
			<h2>添加用户信息</h2>
		</div>
		<div class="addUserInfo_form">
			<form class="form-horizontal" action="${pageContext.request.contextPath}/user/addUser">
				<div class="form-group">
				    <label for="organization" class="col-sm-2 control-label" style="padding-right: 28px;">单位</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="organization" name="organization"  placeholder="单位">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="principal" class="col-sm-2 control-label">负责人</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="principal" name="userName" placeholder="负责人">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="WeChat" class="col-sm-2 control-label">微信号</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="weChat" id="WeChat" placeholder="微信号">
				    </div>
  				</div>
  				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10" style="position: relative;left: -95px;">
				    <label for="WeChat" class="col-sm-2 control-label" style="margin-right: 30px;">权限</label>
				      <div class="checkbox">
				        <label>
				          <input type="checkbox" name="authority"> 可发布招聘信息
				        </label>
				      </div>
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