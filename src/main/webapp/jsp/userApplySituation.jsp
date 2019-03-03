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
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>用户信息</title>
<style>
	.apply_main{
		margin: 60px;
		
	}
	.apply_head{
		height: 70px;
   		width: 200px;
   		position: relative;
    left: 42%;	
	}
	.apply_addInfo{
		height: 50px;
    width: 58px;
    position: relative;
    left: 1045px;
	}
	.apply_TD{
		width: 331px;
    	padding-right: 51px;
	}
	.apply_table{
		width: 55%;
    position: relative;
    left: 23%;
	}
	.school{
		width: 18%;
	}
	.time{
		width: 15%;
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
	<div class="apply_main">
		<div class="apply_head">
			<h2>${apply.applyUserName }的报名情况</h2>
		</div>
		
		<div class="apply_table">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>表项</th>
						<th>内容</th>
						
					</tr>
				</thead>
				<tbody>
				<form action="">
					<tr>
						<td style="width: 106px;">姓名</td>
						<td class="apply_TD">${apply.applyUserName }</td>
					</tr>
					<tr>
						<td style="width: 106px;">性别</td>
						<td class="apply_TD">${apply.gender }</td>
					</tr>
					<tr>
						<td style="width: 106px;">民族</td>
						<td class="apply_TD">${apply.nation }</td>
					</tr>
					<tr>
						<td style="width: 106px;">政治面貌</td>
						<td class="apply_TD">${apply.politicsStatus }</td>
					</tr>
					<tr>
						<td style="width: 106px;">籍贯</td>
						<td class="apply_TD">${apply.nativePlace }</td>
					</tr>
					<tr>
						<td style="width: 106px;">身份证号</td>
						<td class="apply_TD">${apply.identityNum }</td>
					</tr>
					<tr>
						<td style="width: 106px;">婚否</td>
						<td class="apply_TD">${apply.isMarry }</td>
					</tr>
					<tr>
						<td style="width: 106px;">特长</td>
						<td class="apply_TD">${apply.speciality }</td>
					</tr>
					<tr>
						<td style="width: 106px;">联系方式</td>
						<td class="apply_TD">${apply.telephone }</td>
					</tr>
					<tr>
						<td style="width: 106px;">毕业院校及专业</td>
						<td class="apply_TD" style="padding: 0;">
							<table class="table table-bordered" style="margin-bottom: 0">
								<tr>
									<td class="school">本科</td>
									<td>${apply.bachelorDegreeAndMajor }</td>
									<td class="time">毕业时间</td>
									<td>${apply.undergraduateGraduationTime }</td>
								</tr>
								<tr>
									<td class="school">硕士研究生</td>
									<td>${apply.graduateSchoolAndMajor }</td>
									<td class="time">毕业时间</td>
									<td>${apply.graduateTime }</td>
								</tr>
								<tr>
									<td class="school">博士研究生</td>
									<td>${apply.doctoralDegreeAndMajor }</td>
									<td class="time">毕业时间</td>
									<td>${apply.doctoralGraduationTime }</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td style="width: 106px;">工作单位及职务</td>
						<td class="apply_TD">${apply.workOrganization } : ${apply.position }</td>
					</tr>
					<tr>
						<td style="width: 106px;">工作单位联系方式</td>
						<td class="apply_TD">${apply.telephoneOriganization }</td>
					</tr>
					<tr>
						<td style="width: 106px;">专业技术资格</td>
						<td class="apply_TD">${apply.professionalAndTechnicalQualification }</td>
					</tr>
					<tr>
						<td style="width: 106px;">执业资格</td>
						<td class="apply_TD">${apply.practicingRequirements }</td>
					</tr>
					<tr>
						<td style="width: 106px;">通讯地址及邮编</td>
						<td class="apply_TD">${apply.mailingAddress } : ${apply.postalAddress }</td>
					</tr>
					<tr>
						<td style="width: 106px;">E-mail</td>
						<td class="apply_TD">${apply.eMail }</td>
					</tr>
					<tr>
						<td style="width: 106px;">报名单位及专业</td>
						<td class="apply_TD">${apply.applicationOrganization } : ${apply.majorApplicant }</td>
					</tr>
					<tr>
						<td style="width: 106px;">学习和工作经历(由高中填起)</td>
						<td class="apply_TD">${apply.workExperience }</td>
					</tr>
				</form>
				</tbody>
			</table>
		</div>
	</div>
	</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>