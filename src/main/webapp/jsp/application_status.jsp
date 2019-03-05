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
	
	
	
	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<script src="${pageContext.request.contextPath}/js/g2.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/bootstrap-table.min.css" />

<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap-table.min.js" type="text/javascript"
	charset="utf-8"></script>

				
		
	
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
					<li class="text-left"><a
						href="${pageContext.request.contextPath}/user/toUserInfo"
						class="waves-effect waves-dark">用户管理</a></li>
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
					<!-- Advanced Tables -->
					<div class="card">
						<!-- 内容start -->
						<div class="main">
							<!-- 页头 -->
							<div class="main">
			<!-- 页头 -->
			<div class="titleMain">
				<h1 style="text-align:center">太原工业学院招聘信息</h1>
				<h3><strong> 招聘单位:</strong><strong> 太员工业学院招生办</strong></h3>
				<c:forEach items="${occupationApplicantLsit }" var ="occupationApplicant">
					<a <c:if test="${requestScope.offset+status.index}==0"> class="active"</c:if> target="myclass" href="${pageContext.request.contextPath}/user/toStatistics?positonName=${occupationApplicant.positonName }" class="btn btn-primary btn-lg " role="button">${occupationApplicant.positonName }</a>
				</c:forEach>
				
			</div>
			<div class="context_signIn">
				<iframe name="myclass" onload="this.height=this.contentWindow.document.body.scrollHeight" src="${pageContext.request.contextPath}/user/toStatistics?positonName=${occupationApplicantLsit[0].positonName }"
				 width="100%" frameborder="0" border="0"></iframe>
			</div>

		</div>
		<script>
		$('a').on('click', function() {
			$(this).removeClass('active');
			$(this).addClass('active');
		});
			var iframes = document.getElementsByTagName('iframe');
			
			for (var i = 0, j = iframes.length; i < j; ++i) {
				// 放在闭包中，防止iframe触发load事件的时候下标不匹配
				(function(_i) {
					iframes[_i].onload = function() {
						this.style.visibility = 'hidden';
						// this.style.display = 'none';
			
						// 提前还原高度
						this.setAttribute('height', 'auto'); // 或设为''
			
						// 再在下一轮事件循环中设置新高度
						setTimeout(function() {
							iframes[_i].setAttribute('height', iframes[_i].contentWindow.document.body.scrollHeight);
			
							iframes[_i].style.visibility = 'visible';
							// iframes[_i].style.display = 'block';
						}, 0);
					}
				})(i);
			}
		</script>
							
							
							
							<div class="jumbotron">
								<h1>Hello, world!</h1>
								<p>...</p>
								<p>
									<a class="btn btn-primary btn-lg" href="#" role="button">Learn
										more</a>
								</p>
							</div>
							<!-- 汇总信息 -->
						
						<!-- 内容end -->
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal" id="Edit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑教师信息</h4>
				</div>
				<div class="modal-body">
					<div class="modal-body">
						<form role="form" id="updateInfo"
							action="${pageContext.request.contextPath}/.."
							onsubmit="return checkTwo()">
							<div class="form-group">
								<label for="teacherId" class="control-label">教师工号</label> <input
									type="text" class="form-control" id="teacherId"
									name="teacherId"> <input type="text"
									style="display: none" id="teacherIdB" name="teacherIdB" />
							</div>
							<div class="form-group">
								<label for="teacherName" class="control-label">教师姓名</label> <input
									type="text" class="form-control" id="teacherName"
									name="teacherName">
							</div>
							<div class="form-group">
								<label class="control-label">教师性别</label> <select
									class="form-control" id="select" name="select">
									<option>男</option>
									<option>女</option>
								</select>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" style="margin-left: 2%">关闭</button>
								<button type="submit" class="btn btn-primary" id="submitButton">提交</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</body>

</html>
