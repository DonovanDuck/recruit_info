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
		padding-top: 30px;
		
	}
	.apply_head{
		height: 70px;
   		width: 100%;
    position: relative;
    left: 37%;
	}
	.apply_addInfo{
		height: 50px;
    width: 58px;
    position: relative;
    left: 1045px;
	}
	.apply_TD{
		width: 88px;
    	padding-right: 51px; 
	}
	.apply_table{
	width: 895px;
    position: relative;
    left: 13%;
	}
	.school{
		width: 18%;
	}
	.time{
		width: 15%;
	}
	.info_td{
		width: 100px;
	}
	.advice{
		width: 100%;
    height: 50%;
    position: relative;
    top: 82px;
    padding-left: 32px;
    padding-right: 40px;
	}
	.advice ul{
		width: 100%;
    height: 21px;
	}
	.advice ul li{
		float: left;
	}
</style>
<script type="text/javascript">
function doPrint() {

    bdhtml = window.document.body.innerHTML;
    sprnstr = "<!--startprint-->";
    eprnstr = "<!--endprint-->";
    prnhtml = bdhtml.substr(bdhtml.indexOf(sprnstr) + 17);
    prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));

    window.document.body.innerHTML =document.getElementById("dayin").innerHTML; 
    //window.print();

  //  window.document.body.innerHTML = prnhtml;
    window.print();
    window.document.body.innerHTML = bdhtml;
}


</script>

</head>

<body bgcolor='white' style='font-family:SimSun; height:100%;'
	screen_capture_injected='true' ryt11773='1'>
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
		<div id="page-wrapper" style="height: auto;min-height: 960px;">
			<div id="page-inner">
				<div class="">
					<div class="card">
						<div class="card-content " style="padding-top: 0%">
	<div class="apply_main">
		<div class="apply_head">
			<h2>${apply.applyUserName }的报名情况</h2>
		</div>
		<button  onclick="doPrint()" type="button" class="btn btn-primary" style="position: absolute;left: 172px; top: 41px;">打印</button>
		
		<div class="apply_table" id="dayin">
			<table class="table table-bordered">
				
				<tbody>
				<form action="">
					<tr>
						<td class="info_td">姓名</td>
						<td class="apply_TD">${apply.applyUserName }</td>
						<td class="info_td">性别</td>
						<td class="apply_TD">${apply.gender }</td>
						<td class="info_td">民族</td>
						<td class="apply_TD">${apply.nation }</td>
						<td style="width: 112px;" rowspan="4">
							<div style="height: 100%;width: 100%">
								<img src="${apply.face }" style="height: 100%;width: 100%"/>
							</div>
						</td>
					</tr>
					
					<tr>
						<td class="info_td">政治面貌</td>
						<td class="apply_TD" colspan="2">${apply.politicsStatus }</td>
						<td class="info_td">籍贯</td>
						<td class="apply_TD" colspan="2">${apply.nativePlace }</td>
						
					</tr>
					
					<tr>
						<td class="info_td">身份证号</td>
						<td class="apply_TD" colspan="2">${apply.identityNum }</td>
						<td class="info_td">婚否</td>
						<td class="apply_TD"colspan="2">${apply.isMarry }</td>
						
					</tr>
					
					<tr>
						<td class="info_td">特长</td>
						<td class="apply_TD" colspan="2">${apply.speciality }</td>
						<td class="info_td">联系方式</td>
						<td class="apply_TD" colspan="2">${apply.telephone }</td>
						
						
					</tr>
					
					
						<tr>
							<td class="info_td" rowspan="3">毕业院校及专业</td>
									<td class="school">本科</td>
									<td colspan="2">${apply.bachelorDegreeAndMajor }</td>
									<td class="time">毕业时间</td>
									<td colspan="2">${undergraduateGraduationTime }</td>
								</tr>
								<tr>
									<td class="school">硕士研究生</td>
									<td colspan="2">${apply.graduateSchoolAndMajor }</td>
									<td class="time">毕业时间</td>
									<td colspan="2">${graduateTime }</td>
								</tr>
								<tr>
									<td class="school">博士研究生</td>
									<td colspan="2">${apply.doctoralDegreeAndMajor }</td>
									<td class="time">毕业时间</td>
									<td colspan="2">${doctoralGraduationTime }</td>
								</tr>
					
					<tr>
						<td class="info_td">工作单位及职务</td>
						<td class="apply_TD" colspan="3">${apply.workOrganization }  ${apply.position }</td>
						<td class="info_td">工作单位联系方式</td>
						<td class="apply_TD" colspan="3">${apply.telephoneOriganization }</td>
					</tr>
					
					<tr>
						<td class="info_td">专业技术资格</td>
						<td class="apply_TD" colspan="3">${apply.professionalAndTechnicalQualification }</td>
						<td class="info_td">执业资格</td>
						<td class="apply_TD" colspan="3">${apply.practicingRequirements }</td>
					</tr>
					
					<tr>
						<td class="info_td">通讯地址及邮编</td>
						<td class="apply_TD" colspan="3">${apply.mailingAddress }  ${apply.postalAddress }</td>
						<td class="info_td">E-mail</td>
						<td class="apply_TD" colspan="3">${apply.eMail }</td>
					</tr>
					<tr>
						<td class="info_td">报名单位及专业</td>
						<td class="apply_TD" colspan="6">${apply.applicationOrganization }  ${apply.majorApplicant }</td>
					</tr>
					<tr>
						<td class="info_td">学习和工作经历(由高中填起)</td>
						<td class="apply_TD" colspan="6">${apply.workExperience }</td>
					</tr>
					<tr>
						<td class="info_td">工作业绩或参加社会实践情况</td>
						<td class="apply_TD" colspan="6">${apply.jobPerformance }</td>
					</tr>
					<tr>
						<td class="info_td" >论文及课题情况</td>
						<td class="apply_TD" colspan="6">${apply.paperTopicSituation }</td>
					</tr>
					<tr>
						<td class="info_td">奖惩情况</td>
						<td class="apply_TD" colspan="6">${apply.sanctionSituation }</td>
					</tr>
					<tr>
						<td>家庭成员及重要社会关系</td>
						<td>称谓</td>
						<td>姓名</td>
						<td>出生年月</td>
						<td>政治面貌</td>
						<td colspan="2">工作单位及职务</td>
					</tr>
					<c:forEach items="${apply.familyRelationship }" var="family" varStatus="status">
					<tr style="height: 33px;">
						<td>${requestScope.offset+status.index+1}</td>
						<td>${family.appellation }</td>
						<td>${family.name }</td>
						<td>${birthList[status.index] }</td>
						<td>${family.politicsStatus }</td>
						<td colspan="2">${family.organization }&emsp;&emsp;${family.position }</td>
					</tr>
					</c:forEach>
					<!-- <tr style="height: 33px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="2"></td>
					</tr>
					<tr style="height: 33px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="2"></td>
					</tr>
					<tr style="height: 33px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="2"></td>
					</tr>
					<tr style="height: 33px;">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td colspan="2"></td>
					</tr> -->
					<tr style="height: 123px;">
						<td class="info_td">招聘组意见</td>
						<td class="apply_TD" colspan="6">
							<div class="advice">
								<ul>
									<li style="list-style: none;">签字：</li>
									<li style="float: right;list-style: none;">年&emsp;&emsp;月&emsp;&emsp;日</li>
								</ul>
							</div>
						</td>
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