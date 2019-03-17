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
    left: 434px;
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
    font-size: 14px;
    position: relative;
    left: 36px;
    top: 12px;
	}
	input{
		font-size:10px;
	  border: aliceblue;
	}
</style>
<script>
	function open1(ob) {
		var id = $(ob).attr("name");
		var username = $("#username" + id).val(); 
		if(confirm("您确定要重置"+username+"用户的密码吗？重置后密码的密码为：123456，提醒尽快修改密码，确保安全。")){
		var userId = $("#" + id).val();
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
	}
</script>

<script>
	$(function(){
		$("input[name='phoneNum']").blur(function(){
			var id = $(this).attr("id");
			var phoneNum = $("#"+id).val();
			if(phoneNum == ""){
				alert("电话不能为空！");
			}
			var reg = /^1[34578]\d{9}$/;
			if(!reg.test(phoneNum)){
				alert("请输入正确的电话格式！");
			}
		});
	})
	
</script>

<script type="text/javascript">
function submitButton(ob){
	var username = $("#username" + ob).val(); 
	if(confirm("您确定修改"+username+"用户的信息吗？")){
		 var juge = true;
			var phoneNum = $("#phone"+ob).val();
			if(phoneNum == ""){
				alert("电话不能为空！");
				juge = false;
			}
			var reg = /^1[34578]\d{9}$/;
			if(!reg.test(phoneNum)){
				alert("请输入正确的电话格式！");
				juge = false;
			}
			
			$.ajax({
				async : false,
				cache : false,
				url : "${pageContext.request.contextPath}/user/ajaxCheckPhone",
				data : {
					'phoneNum' : phoneNum
				},
				type : "POST",
				dataType : "text",
				success : function(result) {
					//alert(eval(result));
					if (eval(result) == "ERROR") {
						alert("此号码已经存在！请重新输入");
						juge = false;
					} 
				}
			});
			
			if(juge){
				$("#modifyForm"+ob).submit();
			} 
	}
}
</script>

<script type="text/javascript">
function deleteUser(ob){
	var username = $("#username" + ob).val(); 
	var userId = $("#" + ob).val();
	if(confirm("您确定删除"+username+"用户吗？")){
		$("#deleteForm").submit();
	}
}
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
				<c:if test="${sessionScope.User.authority == 0 || sessionScope.User.authority == 10 ||sessionScope.User.authority == 20 }">
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
			<a href="${pageContext.request.contextPath}/user/toAddUser"><button class="btn btn-success" type="button">添加用户</button></a>
		</div>
		<div class="userInfo_table">
			<table class="table " >
				<thead>
					<tr>
						<th style="width: 80px;">编号</th>
						<th>单位</th>
						<th>负责人</th>
						<th>手机号</th>
						<th style="position: relative;padding-left: 52px;">权限</th>
						<th style="    padding-left: 42px;">修改</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${userList }" var="user" varStatus="status">
					<tr>
					<form action="${pageContext.request.contextPath}/user/modifyUser" id="modifyForm${requestScope.offset+status.index+1}">
						<td style="width: 62px;text-align: center;padding-top: 21px;">${requestScope.offset+status.index+1}
							<input class="form-control" type="hidden" name="userId" id="${requestScope.offset+status.index+1}" value="${user.userId }">
						</td>
						<!-- <td style="width: 62px;">1</td> -->
						<td class="userInfo_TD"><input style="border: aliceblue;font-size: 14px;" class="form-control" type="text" id="orgaName${requestScope.offset+status.index+1 }" name="organizationName" value="${user.organizationName }"></td>
						<td class="userInfo_TD"><input style="border: aliceblue;font-size: 14px;" class="form-control" type="text" name="userName" id="username${requestScope.offset+status.index+1}" value="${user.userName }"></td>
						<td class="userInfo_TD"><input style="border: aliceblue;font-size: 14px;" class="form-control" type="text" id="phone${requestScope.offset+status.index+1 }"  name="phoneNum" value="${user.phoneNum }"></td>
						
						<c:if test="${sessionScope.User.authority == 0 }">
						<c:if test="${user.authority==10 || user.authority==20}">
						<td class="userInfo_TD"><input style="border: aliceblue;width: 26px; float: left;" class="form-control" type="checkbox" id="authority" name="authority" checked="${user.authority }" />
							<label for="authority" style="margin-left: 10px;margin-top: 10px;color: #000;">可以发布招聘信息</label></td>
						</c:if>
						<c:if test="${user.authority==11 || user.authority==21 }">
						<td class="userInfo_TD"><input style="border: aliceblue;width: 26px; float: left;" class="form-control" type="checkbox" id="authority2" name="authority" />
							<label for="authority2" style="margin-left: 10px;margin-top: 10px;color: #000;">可以发布招聘信息</label></td>
						</c:if>
						</c:if>
						
						<c:if test="${sessionScope.User.authority != 0 }">
						<c:if test="${user.authority==10 }">
						<td class="userInfo_TD"><label class="au_admin">一级管理</label></td>
						</c:if>
						<c:if test="${user.authority==20 }">
						<td class="userInfo_TD"><label class="au_admin">二级管理</label></td>
						</c:if>
						<c:if test="${user.authority==11 || user.authority==21 }">
						<td class="userInfo_TD"><label class="au_admin">员工</label></td>
						</c:if>
						</c:if>
						
						<c:if test="${user.authority == 0 }">
						<td class="userInfo_TD"><label class="au_admin">管理员</label></td>
						</c:if>
						<td class="userInfo_TD" style="width: 19%;">
							<input class="btn btn-default" type="button" onclick="submitButton(${requestScope.offset+status.index+1})" value="修改" style="margin-left: 26px;">
					    	<input class="btn btn-default" name="${requestScope.offset+status.index+1}" onclick="open1(this)" type="button" value="重置密码" style="margin-right: 0xp;">
						</td>
						</form>
						<td class="userInfo_TD" style="position: relative;left: -8px;padding-left: 0;">
							<form action="${pageContext.request.contextPath}/user/deleteUser" id="deleteForm">
					    		<input class="form-control" type="hidden" name="deleteUserId"  value="${user.userId }" />
					    		<input class="btn btn-danger" name="${requestScope.offset+status.index+1}" onclick="deleteUser(${requestScope.offset+status.index+1})" type="button" value="删除用户" style="margin-right: 0xp;">
							</form>
						</td>
					</tr>
				
				
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