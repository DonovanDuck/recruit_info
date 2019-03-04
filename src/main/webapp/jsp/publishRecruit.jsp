﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type"
	content="multipart/form-data; charset=UTF-8">
<title>教师信息管理</title>
<link
	href="${pageContext.request.contextPath}/css/Admin/font-awesome.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/js/Admin/morris/morris-0.4.3.min.css"
	rel="stylesheet" />
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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Admin/bootstrap-datetimepicker.css">
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/Admin/custom-scripts.js"></script>
<script
	src="${pageContext.request.contextPath}/js/Admin/materialize.min.js"></script>
<script type="text/javascript">
	function submitButton() {
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var organization = $("organization").val();
		if (startTime == endTime) {
			alert("开始时间与截止时间相等,重新选择");
		} else if (startTime >= endTime) {
			alert("开始时间后于截止时间,重新选择");
		} else if (organization == null || organization == "") {
			alert("招聘单位为空");
		} else if (judge) {
			//recruitForm.submit();
		}
		return false;
	}
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
					<div class="card" style="height: 100%">
						<form class="form-horizontal"
							action="${pageContext.request.contextPath}/user/publishRcruit"
							method="post" enctype="multipart/form-data"
							style="width: 50%; margin-left: 25%; padding-top: 2%"
							name="recruitForm" id="recruitForm">
							<div class="form-group">
								<div class="col-md-4 text-right">
									<h3>招聘单位:</h3>
								</div>
								<div class="col-md-7">
									<input name="organization" id="organization" type="text"
										id="organization" placeholder="招聘单位">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 text-right">
									<h3>招聘信息:</h3>
								</div>
								<div class="col-md-7">
									<c:if test="${empty list!=null }">
										<select class="form-control">
											<c:forEach items="${list }" var="list" varStatus="status">
												<option>${list.positonName }</option>
											</c:forEach>
										</select>
										<input name="recruitInfo" id="recruitInfo" type="text"
											id="recruitInfo" placeholder="输入其他职位">
									</c:if>
									<c:if test="${empty list==null }">
										<input name="recruitInfo" id="recruitInfo" type="text"
											id="recruitInfo" placeholder="职位名称">
									</c:if>
									<input name="recruitInfo" id="recruitInfo" type="text"
										id="recruitInfo" placeholder="人数">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 text-right">
									<h3>招聘文件:</h3>
								</div>
								<div class="col-md-7" style="padding-top: 1%;">
									<input name="upFile" type="file" id="upFile"
										multiple="multiple">
								</div>
							</div>
							<div class="form-group" style="margin-top: 5%;">
								<div class="col-md-4 text-right">
									<h3>开始时间:</h3>
								</div>
								<div class="col-md-7">
									<input size="16" type="datetime-local"
										value="2016-01-11T16:00:00" id="startTime" name="startTime"
										class="form_datetime">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 text-right">
									<h3>截止时间:</h3>
								</div>
								<div class="col-md-7">
									<input size="16" type="datetime-local"
										value="2016-01-11T16:00:00" id="endTime" name="endTime"
										class="form_datetime">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3">
									<button type="button" class="btn btn-primary"
										onclick="submitButton()">发布</button>
									<button type="reset" class="btn btn-primary"
										style="margin-left: 47%">重置</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
