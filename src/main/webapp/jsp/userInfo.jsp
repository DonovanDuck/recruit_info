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
<title>用户信息</title>
<style>
	.userInfo_main{
		margin: 60px;
		
	}
	.userInfo_head{
		height: 70px;
   		width: 135px;	
	}
	.userInfo_addInfo{
		height: 50px;
    width: 58px;
    position: relative;
    left: 1045px;
	}
	.userInfo_TD{
		width: 331px;
    	padding-right: 51px;
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
						href="${pageContext.request.contextPath}/admin/readTeacherInfo"
						class="waves-effect waves-dark" style="font-size: 20px">招聘信息</a></li>
					<li class="text-left"><a
						href="${pageContext.request.contextPath}/user/toUserInfo"
						class="waves-effect waves-dark" style="font-size: 20px">用户管理</a></li>
					<li class="text-left"><a
						href="${pageContext.request.contextPath}/admin/readCategories"
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
		<div class="userInfo_main">
		<div class="userInfo_head">
			<h2>用户信息</h2>
		</div>
		<div class="userInfo_addInfo">
			<button class="btn btn-success" type="submit">添加用户</button>
		</div>
		<div class="userInfo_table">
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>编号</th>
						<th>单位</th>
						<th>负责人</th>
						<th>修改</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${userList }" var="user">
				<form action="${pageContext.request.contextPath}/user/modifyUser">
					<tr>
						<td style="width: 62px;text-align: center;padding-top: 21px;">${requestScope.offset+status.index+1}
							<input class="form-control" type="hidden" name="userId" value="${user.userId }">
						</td>
						<!-- <td style="width: 62px;">1</td> -->
						<td class="userInfo_TD"><input class="form-control" type="text" name="organizationName" value="${user.organizationName }"></td>
						<td class="userInfo_TD"><input class="form-control" type="text" name="userName" value="${user.userName }"></td>
						<td class="userInfo_TD"><input class="btn btn-default" type="submit" value="修改"></td>
					</tr>
				</form>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>