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

	input{
		font-size: 21px;
	}
	.userInfo_main{
		margin: 60px;
		
	}
	.userInfo_head{
		height: 70px;
   		width: 135px;	
   		position: relative;
    left: 248px;
    top: 29px
	}
	.userInfo_addInfo{
		height: 50px;
    width: 58px;
    position: relative;
    left: 90%;
	}
	.userInfo_TD{
		width: 331px;
    	padding-right: 51px;
	}
	.au_admin{
		    color: #000;
    font-size: 18px;
    position: relative;
    left: 77px;
    top: 12px;
	}
</style>
<script>
	function open1(ob) {
		var id = $(ob).attr("name");
		//alert(id);
		var userId = $("#" + id).val();
		//alert(userId);
		$.ajax({
			async : false,
			cache : false,
			url : "${pageContext.request.contextPath}/user/ajaxRePassword",
			data : {
				'userId' : userId
			},
			type : "POST",
			dataType : "text",
			success : function(result) {
				//alert(eval(result));
				if (eval(result) == "OK") {
					alert("密码重置成功！");
				} else {
					alert("密码重置失败！");
				}
			}
		});

	}
</script>

<script>
	$(function() {

		//当页面加载完成的时候，自动调用该方法
		window.onload = function() {
			alert(fff);
			/* if(${status} == "OK")
				alert("修改成功！");
			if(${status} == "ERROR")
				alert("修改失败！"); */
		};
	});
</script>
<script>
	$(document).ready(function() {
		$("#dataTables-example").dataTable();
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
		<div class="userInfo_main">
		<div class="userInfo_head">
			<h2>用户信息</h2>
		</div>
		<div class="userInfo_addInfo">
			<a href="${pageContext.request.contextPath}/jsp/addUserInfo.jsp"><button class="btn btn-success" type="button">添加用户</button></a>
		</div>
		<div class="userInfo_table">
			<table class="table " >
				<thead>
					<tr>
						<th>编号</th>
						<th>单位</th>
						<th>负责人</th>
						<th style="position: relative;padding-left: 91px;">权限</th>
						<th style="    padding-left: 42px;">修改</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${userList }" var="user" varStatus="status">
				<form action="${pageContext.request.contextPath}/user/modifyUser">
					<tr>
						<td style="width: 62px;text-align: center;padding-top: 21px;">${requestScope.offset+status.index+1}
							<input class="form-control" type="hidden" name="userId" id="${requestScope.offset+status.index+1}" value="${user.userId }">
						</td>
						<!-- <td style="width: 62px;">1</td> -->
						<td class="userInfo_TD"><input class="form-control" type="text" name="organizationName" value="${user.organizationName }"></td>
						<td class="userInfo_TD"><input class="form-control" type="text" name="userName" value="${user.userName }"></td>
						<c:if test="${user.authority == 1 }">
						<td class="userInfo_TD"><input class="form-control" type="checkbox" name="authority" checked="${user.authority }" /></td>
						</c:if>
						<c:if test="${user.authority == 0 }">
						<td class="userInfo_TD"><input class="form-control" type="checkbox" name="authority" /></td>
						</c:if>
						<c:if test="${user.authority == 2 }">
						<td class="userInfo_TD"><label class="au_admin">管理员</label></td>
						</c:if>
						<td class="userInfo_TD">
							<input class="btn btn-default" type="submit" value="修改" style="margin-left: 26px;margin-right: 27px;">
					    	<input class="btn btn-default" name="${requestScope.offset+status.index+1}" onclick="open1(this)" type="button" value="重置密码" style="margin-right: 0xp;">
					    	<label style="margin-left: 113px;">原始密码：123456</label>
						</td>
					</tr>
				</form>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$("#dataTables-example").dataTable();
		});
	</script>
</body>
</html>