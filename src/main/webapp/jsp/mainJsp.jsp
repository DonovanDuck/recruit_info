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
<script
	src="${pageContext.request.contextPath}/js/Admin/materialize.min.js"></script>
<script
	src="${pageContext.request.contextPath}/js/Admin/dataTables/jquery.dataTables.js"></script>
<script
	src="${pageContext.request.contextPath}/js/Admin/dataTables/dataTables.bootstrap.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js">
	
</script>
<script
	src="${pageContext.request.contextPath}/js/Admin/custom-scripts.js"></script>
<script
	src="${pageContext.request.contextPath}/js/Admin/moment-with-locales.min.js"></script>
<script>
	$(document).ready(function() {
		$('#dataTables-example').dataTable();
	});
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
							<div class="table-responsive" style="overflow-x: hidden;">
								<hr>
								<form action="${pageContext.request.contextPath}/user/searchRecruit" enctype="multipart/form-data">
									<div class="col-md-6">
										<div class="col-md-4">
											<input type="text" class="form-control" id="search"
												name="search" placeholder="单位名搜索">
										</div>
										<div class="col-md-2">
											<button type="submit" class="btn btn-primary btn-sm">搜索
											</button>
										</div>
									</div>
								</form>
								<div class="col-md-6 text-right">
									<a
										href="${pageContext.request.contextPath}/user/toPublishRcruitPage"
										style="font-size: 20px">
										<button type="button" class="btn btn-primary ">发布招聘信息
										</button>
									</a>
								</div>
								<table class="table table-bordered " style="margin-top: 5%">
									<thead>
										<tr>
											<th class="text-center">编号</th>
											<th class="text-center">单位</th>
											<th class="text-center">招聘信息</th>
											<th class="text-center">报名开始时间</th>
											<th class="text-center">报名截止时间</th>
											<th class="text-center">详细信息</th>
										</tr>
									</thead>
									<tbody id="asds">
										<c:forEach items="${list }" var="list" varStatus="status">
											<tr>
												<td class="text-center" style="padding-top:1%;">${requestScope.offset+status.index}</td>
												<td class="text-center" style="padding-top:1%;">${list.organization }</td>
												<td class="text-center" style="padding-top:1%;">${list.recruitInfo }</td>
												<td class="text-center" style="padding-top:1%;">${list.startTime }</td>
												<td class="text-center" style="padding-top:1%;">${list.endTime }</td>
												<td class="text-center"><a href="${pageContext.request.contextPath}/.."
													class="waves-effect waves-dark" style="font-size: 20px">
														<button type="button" class="btn btn-default btn-lg"
															style="padding-top: 4%;">
															<small>查看报名情况</small>
														</button>
												</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div class="col-md-12">
									<div class="col-md-4">
										<ul class="pagination">
											<li><a href="#" aria-label="Previous"> <span
													aria-hidden="true">&laquo;</span>
											</a></li>
											<li><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
											<li><a href="#">4</a></li>
											<li><a href="#">5</a></li>
											<li><a href="#" aria-label="Next"> <span
													aria-hidden="true">&raquo;</span>
											</a></li>
										</ul>
									</div>
									<div class="col-md-8"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
