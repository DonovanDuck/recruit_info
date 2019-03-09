<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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




<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/g2.min.js"
	type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap-table.min.css" />

<script>
	$(document).ready(function() {
		$("#dataTables-example").dataTable();
	});
</script>
</head>
<body>
	<div class="alert alert-info" role="alert">

		<h3>
			当前报名总人数：<strong>${numAll }</strong>人
		</h3>
		其中博士<strong>${numDoctor }</strong>人、硕士<strong>${numMaster }</strong>人、学士<strong>${numBachelor }</strong>人，疆内户籍<strong>${numInSide }</strong>人，双一流院校<strong>0</strong>人，双一流学科<strong>0</strong>人。
		<h3>
			当日报名<strong>${numAllToday }</strong>人
		</h3>
		今日报名博士<strong>${numDoctorToday }</strong>人、硕士<strong>${numMasterToday }</strong>人、学士<strong>${numBachelorToday }</strong>人，疆内户籍<strong>${numBachelorToday }</strong>人，双一流院校<strong>0</strong>人，双一流学科<strong>0</strong>人。

	</div>

	<table  class="table   table-hover"
									id="dataTables-example" style="margin-top: -1%">
		<thead>
			<tr>
				<th>#</th>
				<th>报名号</th>
				<th>报名人</th>
				<th>报名职业</th>
				<th>手机号码</th>
				<th>学历</th>
				<th>E-mail</th>
				<th>工作经历</th>
				<th>身份证</th>
			</tr>
		</thead>
		<c:forEach items="${applList }" var="apply" varStatus="status">
			<tbody>

				<tr>
					<th scope="row">${requestScope.offset+status.index+1}</th>
					<td>${apply.applyId }</td>
					<td>${apply.applyUserName }</td>
					<td>${apply.occupationApplicant }</td>
					<td>${apply.telephone }</td>
					<td>${apply.education }</td>
					<td>${apply.eMail }</td>
					<td>${apply.workExperience }</td>
					<td>${apply.identityNum }</td>
				</tr>

			</tbody>
		</c:forEach>
	</table>

</body>
</html>