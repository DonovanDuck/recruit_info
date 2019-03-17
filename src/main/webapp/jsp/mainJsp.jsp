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
<title>招聘系统主页</title>
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
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
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
		$("#dataTables-example").dataTable();
	});
</script>
<script type="text/javascript">
$(function(){
	if(${sessionScope.User.authority }==10 || ${sessionScope.User.authority }==20)//如果报错，为正常，不影响功能
	{
	$("#addButton").css("display","block");
	};
})
</script>
<script type="text/javascript">
function CurentTime(time)
{ 
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var hh = now.getHours();            //时
    var mm = now.getMinutes();          //分
    var ss = now.getSeconds();           //秒   
    var clock = year + "-"; 
    if(month < 10)
        clock += "0";   
    clock += month + "-"; 
    if(day < 10)
        clock += "0";      
    clock += day + " ";   
    if(hh < 10)
        clock += "0";       
    clock += hh + ":";
    if (mm < 10) clock += '0'; 
    clock += mm + ":"; 
    if (ss < 10) clock += '0'; 
    clock += ss+".0"; 
   	if(time != clock)
   		{
   		alert("时间相等")
   		}
}
</script>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation"
			style="height: 60px;">
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
						<div style="height: 42px;width: 200px;position: relative;left: 24px;top: 10px;font-size: 18px;">
							<label style="color: #000;">欢迎：${sessionScope.User.userName }</label>
						</div>
						<div class="card-action text-center" style="padding-bottom: 0%">
							<h3>${sessionScope.User.organizationName }</h3>
						</div>
						<div class="card-content">
							<div class="table-responsive" style="overflow-x: hidden;">
								<div class="col-md-7" style="display: none" id="addButton"
									name="addButton">
									<a
										href="${pageContext.request.contextPath}/user/toPublishRcruitPage"
										style="font-size: 20px">
										<button type="button" class="btn btn-primary"
											style="margin-top: 3%;">发布招聘信息</button>
									</a>
								</div>
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example" style="margin-top: -1%">
									<thead>
										<tr>
											<th class="text-center" style="width: 50px;">编号</th>
											<th class="text-center" style="width: 144px;">招聘标题</th>
											<th class="text-center" style="width: 112px;">招聘单位</th>
											<th class="text-center" style="width: 80px;">报名开始时间</th>
											<th class="text-center" style="width: 80px;">报名截止时间</th>
											<th class="text-center" style="width: 80px;">发布时间</th>
											<th class="text-center" style="width: 80px;">发布人</th>
											<th class="text-center" style="width: 331px;height: 60px"></th>
										</tr>
									</thead>
									<tbody id="asds">
										<c:forEach items="${list }" var="list" varStatus="status">

											<tr>
												<td class="text-center" style="padding-top: 1%;">${requestScope.offset+status.index+1}</td>
												<td class="text-center" style="padding-top: 1%;">${list.recruitInfo }</td>
												<td class="text-center" style="padding-top: 1%;">${onameList[status.index] }</td>
												<td class="text-center" style="padding-top: 1%;">${list.startTime }</td>
												<td class="text-center" style="padding-top: 1%;">${list.endTime }</td>
												<td class="text-center" style="padding-top: 1%;">${list.endTime }</td>
												<td class="text-center" style="padding-top: 1%;">${list.publisherName }</td>
												<td class="text-center" style="width: 331px;height: 60px">
														<a
															href="${pageContext.request.contextPath}/user/toConsultRecuit?recuritId=${list.recruitId }"
															class="waves-effect waves-dark" style="font-size: 20px">
															<button type="button" class="btn btn-default btn-lg "
																id="consultRecurit" name="consultRecurit" onclick="CurentTime(${list.endTime })"
																style="padding-top: 4%;">
																<small>查看</small>
															</button>
														</a>

													 <c:if test="${((sessionScope.User.authority == 10 || sessionScope.User.authority == 20) &&  sessionScope.User.organizationId == list.organization) || sessionScope.User.authority == 0}">
													<c:if test="${systemTime < list.endTime }">
														<a
															href="${pageContext.request.contextPath}/user/toUpdateRecuit?recuritId=${list.recruitId }"
															class="waves-effect waves-dark" style="font-size: 20px">
															<button type="button" class="btn btn-success btn-lg"
																id="editRecurit" name="editRecurit"
																style="padding-top: 4%;">
																<small>编辑</small>
															</button>
														</a>
														</c:if>
														<c:if test="${systemTime >= list.endTime }">
														<a
															href="${pageContext.request.contextPath}/user/toConsultRecuit?recuritId=${list.recruitId }"
															class="waves-effect waves-dark" style="font-size: 20px">
															<button type="button" class="btn btn-default btn-lg"
																id="editRecurit" name="editRecurit"
																style="padding-top: 4%;">
																<small>查看</small>
															</button>
															</a>
														</c:if>
													</c:if> 
													<c:if test="${sessionScope.User.authority != 21 || sessionScope.User.organizationId == list.organization }">
													<a
													href="${pageContext.request.contextPath}/user/toSignInInfo?recruitId=${list.recruitId }"
													class="waves-effect waves-dark" style="font-size: 20px">
														<button type="button" class="btn btn-default btn-lg"
															style="padding-top: 4%;">
															<small>查看报名情况</small>
														</button>
												</a>
												</c:if>
												</td>
											</tr>
										</c:forEach>
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
