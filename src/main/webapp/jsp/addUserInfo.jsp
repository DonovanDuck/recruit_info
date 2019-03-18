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
<title>添加用户信息</title>
<style>
	.addUserInfo_head{
		height: 70px;
   		width: 190px;
   		position: relative;
    left: 38%;
    top:-12px;
	}
	.addUserInfo_main{
		
		
	}
	.addUserInfo_form{
		width: 500px;
    margin-left: 26%;
	}
	.form-group{
		margin-bottom: 40px;
		color: #000;
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
		$("#phoneNum").blur(function(){
			
			var phoneNum = $("#phoneNum").val();
			if(phoneNum == ""){
				alert("电话不能为空！");
			}
			var reg = /(1[3-9]\d{9}$)/;
			if(!reg.test(phoneNum)){
				alert("请输入正确的电话格式！");
			}
		});
	})
	
</script>

<script type="text/javascript">
function submitButton(){
	 var juge = true;
	var phoneNum = $("#phoneNum").val();
	var reg = /(1[3-9]\d{9}$)/;
	if(phoneNum == ""){
		alert("电话不能为空！");
		juge = false;
	}
	
	if(!reg.test(phoneNum)){
		alert("请输入正确的电话格式！");
		juge = false;
	}
	var organization = $("#organization").val();
	if(organization==""){
		alert("单位不能为空！");
		juge = false;
	}
	var name = $("#name").val();
	if(name==""){
		alert("负责人不能为空！");
		juge = false;
	}
	if(juge){
		$("#addForm").submit();
	} 
}
</script>

</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
			<div class="text-center">
				<!-- <h2>新疆招聘信息系统</h2> -->
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
					<li class="text-left"><a
						href="${pageContext.request.contextPath}/user/exit"
						class="waves-effect waves-dark">安全退出</a></li>
				</ul>
			</div>
		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div id="page-inner">
				<div class="col-md-12">
					<div class="card">
						<div class="card-content " style="padding-top: 0%">
	<div class="addUserInfo_main" style="padding-top: 38px">
		<div class="addUserInfo_head">
			<h2>添加用户信息</h2>
		</div>
		<div class="addUserInfo_form">
			<form class="form-horizontal" action="${pageContext.request.contextPath}/user/addUser" id="addForm">
				<c:if test="${sessionScope.User.authority == 0 }">
				<div class="form-group">
				     <label for="organization" class="col-sm-2 control-label" style="padding-right: -1px;color: #000;">单位</label>
				    <div class="col-sm-10">
				      
													<input id="organization" style='font-size: 18px;'
														name="organization" type="text" placeholder="单位"
														list="positionlist">
													<datalist id="positionlist">
														<c:forEach items="${organizationList }" var="organizationName">
															<option label="${organizationName }" value="${organizationName }" />
														</c:forEach>
													</datalist>
						
				    </div> 
				    
  				</div>
  				</c:if>
  				<c:if test="${sessionScope.User.authority != 0 }">
  					<div class="form-group">
  						<label for="organization" class="col-sm-2 control-label" style="padding-right: -1px;color: #000;">单位</label>
  						<div class="col-sm-10">
  						<input type="text" class="form-control" id="organization" name="organization"  readonly="false" value="${organizationName }">
  						</div>
  					</div>
  				</c:if>
  				<div class="form-group">
				    <label for="principal" class="col-sm-2 control-label" style="color: #000;">员工</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control"  name="userName" id="name" placeholder="员工">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="WeChat" class="col-sm-2 control-label" style="color: #000;">微信号</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="weChat" id="WeChat" placeholder="微信号">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="WeChat" class="col-sm-2 control-label" style="color: #000;">联系电话</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="phoneNum" id="phoneNum" placeholder="联系电话">
				    </div>
  				</div>
  				<c:if test="${sessionScope.User.authority == 0 }">
  				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10" style="position: relative;left: -95px;">
				    <label for="WeChat" class="col-sm-2 control-label" style="margin-right: 30px;color: #000;">权限</label>
				      <div class="checkbox">
				        <label>
				          <input type="checkbox" name="authority"> 设为管理员
				        </label>
				      </div>
				    </div>
				  </div>
				  </c:if>
			</form>
			<div class="form-group" style="height: 12px;">
				    <div class="col-sm-offset-2 col-sm-10 confirm" style="float: left">
				      <button type="button" class="btn btn-success" onclick="submitButton()">确定</button>
				    </div>
				    <div class="col-sm-offset-2 col-sm-10 confirm">
				      <button type="text" onclick="history.back(-1)" class="btn btn-default">取消</button>
				    </div>
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