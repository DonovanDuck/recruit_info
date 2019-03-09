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
<link
	href="${pageContext.request.contextPath}/css/Admin/bootstrap-datetimepicker.css"
	rel="stylesheet" />
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css' />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/Admin/css/cssCharts.css">

<!-- js -->
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/Admin/custom-scripts.js"></script>
<script
	src="${pageContext.request.contextPath}/js/Admin/bootstrap-datetimepicker.js"></script>
<script
	src="${pageContext.request.contextPath}/js/Admin/bootstrap-datetimepicker.zh-CN.js"></script>
<script
	src="${pageContext.request.contextPath}/js/Admin/materialize.min.js"></script>
<script src="${pageContext.request.contextPath}/js/pdfobject.min.js"></script>
</head>
<body>
	<!-- 界面开始部分 -->
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
					<div class="card" style="height: 100%">
						<div class="col-md-12 text-center">
							<h2>浏览招聘信息</h2>
						</div>
						<form class="form-horizontal" action=""
							style="width: 50%; margin-left: 25%; padding-top: 5%;"
							name="recruitForm" id="recruitForm">
							<div class="form-group">
								<div class="col-md-4 text-right" style="margin-top: 1%">
									<h3>招聘标题:</h3>
								</div>
								<div class="col-md-7">
									<input type="text" id="organization" style='font-size: 18px;'
										value="${recruit.recruitInfo }" readOnly="true">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 text-right" style="margin-top: 1%">
									<h3>招聘单位:</h3>
								</div>
								<div class="col-md-7">
									<input style='font-size: 18px;' type="text"
										value="${sessionScope.User.organizationName }" readOnly="true">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 text-right">
									<h3>招聘信息:</h3>
								</div>
								<div class="col-md-7" style="padding: 0">
									<div class="col-md-12" style="padding: 0; visibility: hidden">
										<input type="button" style="width: 30%;"
											class="btn btn-success btn-xs btn-block  pull-right" />
									</div>
									<ul class="col-md-12"
										style="padding: 0; width: 120%; margin-left: -20%;"
										id="ulContent">
										<c:forEach items="${positionList }" var="list"
											varStatus="status">
											<li class="col-md-2 text-right"
												style="float: left; margin-top: 5%; visibility: hidden">
												<button type="button" class="btn btn-danger btn-sm">删除</button>
											</li>
											<li class="col-md-10"
												style="padding: 0; background-color: rgba(245, 246, 246, 0.8); margin-top: 4px; float: left">
												<div class="col-md-12" style="padding: 0;">
													<div class="col-md-8" style="padding: 0">
														<input style='font-size: 18px;' type="text"
															value="【职位】:${list.positonName }" readOnly="true">
													</div>
													<div class="col-md-4">
														<input type="text" value="【人数】:${list.positionNum }"
															style='font-size: 18px; padding-top: 7%' readOnly="true" />
													</div>
												</div>
												<div class="col-md-12" style="padding: 0">
													<div class="col-md-7" style="padding: 0;">
														<%-- 		<input value="【专业】:${list.professionalOrientation }"
															style='font-size: 18px;
															white-space:normal;word-wrap:break-word;word-break:break-all;'
															 type="text" readOnly="true"> --%>
														<span
															style="font-size: 20px; border-bottom: 1px solid gray; line-height: 40pt;">
															【专业】:${list.professionalOrientation } </span>
													</div>

													<div class="col-md-5">
														<%-- 	<input value="【岗位性质】:${list.compilationNature }"
															style='font-size: 18px;' type="text" readOnly="true"> --%>
														<span
															style="font-size: 20px; border-bottom: 1px solid gray; line-height: 40pt;">
															【岗位性质】:${list.compilationNature } </span>
													</div>
												</div>
											</li>
										</c:forEach>
									</ul>
								</div>
							</div>
							<div class="form-group" style="margin-top: 1%">
								<div class="col-md-4 text-right" style="padding-top: 1%;">
									<h3>招聘文件:</h3>
								</div>
								<div class="col-md-7" style="padding-top: 1%;">
									<input value="${recruit.accessory}" style='display: none'
										id="consultPDFPath" type="text"> <a
										href="${pageContext.request.contextPath}/user/resourceDownload?filePath=${recruit.accessory }">
										<button type="button" class="btn btn-info btn-sm">文件下载</button>
									</a>
								</div>
							</div>
							<div class="form-group" style="margin-top: 5%;">
								<div class="col-md-4 text-right" style="margin-top: 1%">
									<h3>开始时间:</h3>
								</div>
								<div class="col-md-7">
									<input type="text" style='font-size: 18px;'
										value="${recruit.startTime }" readOnly="true">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 text-right" style="margin-top: 1%">
									<h3>截止时间:</h3>
								</div>
								<div class="col-md-7">
									<input type="text" style='font-size: 18px;'
										value="${recruit.endTime }" readOnly="true">
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
