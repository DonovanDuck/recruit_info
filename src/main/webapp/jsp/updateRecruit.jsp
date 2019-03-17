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
<title>更新招聘信息</title>
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
<script type="text/javascript">
function submitButton() {
	var judge = true;
	var num = 0;//添加的职位数
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var titleName = $("#organizationTitle").val();
	var positionContent = "";
	var positonNum = "";
	var positionProfession = "";
	var positionCompilationNature = "";

	//根据编制信息获取总共的添加职位数
	$("input[name='professionalOrientation']").each(function() {
		num = num + 1;
	});
	/**先进行判空*/
	var i = 0;
	if (startTime == endTime) {
		alert("开始时间与截止时间相等,重新选择");
		judge = false;
	}
	if (startTime > endTime) {
		alert("开始时间后于截止时间,重新选择");
		judge = false;
	}
	if (titleName == null || titleName == "") {
		alert("招聘标题为空");
		judge = false;
	}
	$("input[name='position']").each(function() {
		i = i + 1;
		if ($(this).val() == "" && i != num) {
			alert("职位为空,请选择");
			judge = false;
		}
	});
	i = 0;
	$("input[name='positionNum']").each(function() {
		i = i + 1;
		if ($(this).val() == "" && i != num) {
			alert("人数为空,请选择");
			judge = false;
		}
	});
	i = 0;
	$("input[name='professionalOrientation']").each(function() {
		i = i + 1;
		if ($(this).val() == "" && i != num) {
			alert("专业及专业方向为空,请选择");
			judge = false;
		}
	})
	i = 0;
	/**数据整合*/
	$("input[name='position']").each(function() {
		positionContent = positionContent + $(this).val() + ",";
	});
	$("input[name='positionNum']").each(function() {
		positonNum = positonNum + $(this).val() + ",";
	});
	$("input[name='professionalOrientation']").each(function() {
		positionProfession = positionProfession + $(this).val() + ",";
	});
	$("select[name='compilationNatureS']").each(
			function() {
				positionCompilationNature = positionCompilationNature
						+ $(this).val() + ",";
			});
	$("#positionContent").val(positionContent);
	$("#positionNumber").val(positonNum);
	$("#positionProfession").val(positionProfession);
	$("#compilationNature").val(positionCompilationNature);
	if (judge) {
		$("#recruitForm").submit();
	}
}
</script>
<script type="text/javascript">
	$(function() {
		$(".form_datetime").datetimepicker({
			weekStart : 0, //一周从哪一天开始
			todayBtn : 1, //
			autoclose : 1,
			todayHighlight : 1,
			startView : 2,
			forceParse : 0,
			showMeridian : 1,
			language : "zh-CN",
		});
		$("#addPosition").click(function() {
			//div 的复制
			var div1 = document.getElementById("liContentOne");
			var div2 = document.getElementById("liContentTwo");
			var div3 = div1.cloneNode(true);
			var div4 = div2.cloneNode(true);
			$("#ulContent").append(div3);
			$("#ulContent").append(div4);
		});
	});	
	/**删除DIV*/
		function removeLi(element){
			if (window.confirm("是否确认要删除?")) {
				var node = element.parentNode.previousSibling;
				var node2 = element.parentNode;
				node.remove();
				node2.remove();
				} 
		}
		function removeContent(name){
			if (window.confirm("是否确认要删除?")) {
				$("li[name="+name+"]").remove();
				} 
		}
</script>
<script type="text/javascript">
function updateFile(element){
 	if (window.confirm("是否确认要删除旧文件并选择新文件?")) {
		var node = element.previousSibling;
		node.remove();
		var node = element.previousSibling;
		node.remove();//相同代码必须重写两次，原因未知。
		element.remove();
		 $("#upFile").css("display","block");
		return  $("#upFile").click();
		}  
}
</script>
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
					<div class="card" style="height: 100%">
						<div class="col-md-12 text-center">
							<h2>更新招聘信息</h2>
						</div>
						<form class="form-horizontal"
							action="${pageContext.request.contextPath}/user/updateRcruit?recruitId=${recruit.recruitId }"
							method="post" enctype="multipart/form-data"
							style="width: 50%; margin-left: 25%; padding-top: 5%;"
							name="recruitForm" id="recruitForm">
							<div class="form-group">
								<div class="col-md-4 text-right" style="margin-top: 1%">
									<h3>招聘标题:</h3>
								</div>
								<div class="col-md-7">
									<input name="organizationTitle" id="organizationTitle"
										type="text" id="organization" style='font-size: 18px;'
										value="${recruit.recruitInfo }" placeholder="招聘标题">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 text-right" style="margin-top: 1%">
									<h3>招聘单位:</h3>
								</div>
								<div class="col-md-7">
									<input name="organization" style='font-size: 18px;'
										id="organization" type="text" id="organization"
										value="${sessionScope.User.organizationName }" readOnly="true">
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 text-right">
									<h3>招聘信息:</h3>
								</div>
								<div class="col-md-7" style="padding: 0">
									<ul class="col-md-12"
										style="padding: 0; width: 100%; margin-left: 3%;"
										id="ulContent">
										<c:forEach items="${positionList }" var="list"
											varStatus="status">
											<li class="col-md-10"
												style="padding: 0; float: left; background-color: rgba(245, 246, 246, 0.8); margin-top: 4px"
												id="liContent" name="${requestScope.offset+status.index+1}">
												<div class="col-md-12" style="padding: 0;">
													<div class="col-md-8" style="padding: 0">
														<input id="position" style='font-size: 18px;'
															name="position" type="text" placeholder="职业"
															value="${list.positonName }" list="positionlist">
														<datalist id="positionlist">
															<c:forEach items="${positionName }" var="positionName">
																<option label="${positionName }"
																	value="${positionName }" />
															</c:forEach>
														</datalist>
													</div>
													<div class="col-md-4">
														<input type="number" value="${list.positionNum }"
															style='font-size: 18px; padding-top: 7%' placeholder="人数"
															name="positionNum" id="positionNum" min="0" />
													</div>
												</div>
												<div class="col-md-12" style="padding: 0">
													<div class="col-md-7" style="padding: 0">
														<input id="professionalOrientation"
															value="${list.professionalOrientation }"
															style='font-size: 18px;' name="professionalOrientation"
															type="text" placeholder="专业及专业方向">
													</div>
													<div class="col-md-5">
														<select class="form-control" id="compilationNatureS"
															name="compilationNatureS"
															style="margin-top: 6%; padding: 0">
															<option>行政岗</option>
															<option>事业岗</option>
															<option>其他岗</option>
														</select>
													</div>
												</div>
											</li>
											<li class="col-md-2 text-right"
												style="float: left; margin-top: 5%"
												name="${requestScope.offset+status.index+1}">
												<button type="button" class="btn btn-danger btn-sm"
													id="deleteDiv"
													onclick="removeContent(${requestScope.offset+status.index+1})">删除</button>
											</li>
										</c:forEach>
									</ul>
									<div class="col-md-12" style="padding: 0">
										<input type="button" style="width: 30%;" id="addPosition"
											class="btn btn-success btn-xs btn-block  pull-right"
											value="添加职位" />
									</div>
								</div>
							</div>
							<div class="form-group" style="margin-top: 1%">
								<div class="col-md-4 text-right" style="padding-top: 1%;">
									<h3>招聘文件:</h3>
								</div>
								<div class="col-md-7" style="padding-top: 1%;">
									<c:if test="${not empty recruit.accessory}">
										<a
											href="${pageContext.request.contextPath}/user/resourceDownload?filePath=${recruit.accessory }">
											<button type="button" class="btn btn-info btn-sm">文件下载</button>
										</a>
										<button type="button" class="btn btn-info btn-sm"
											onclick="updateFile(this)">文件更新</button>
										<input name="upFile" type="file" id="upFile"
											multiple="multiple" style="display: none">
									</c:if>
									<c:if test="${empty recruit.accessory}">
										<input name="upFile" type="file" id="upFile"
											multiple="multiple">
									</c:if>
								</div>
							</div>
							<div class="form-group" style="margin-top: 5%;">
								<div class="col-md-4 text-right" style="margin-top: 1%">
									<h3>开始时间:</h3>
								</div>
								<div
									class="input-group date form_datetime col-md-7 form_datetime"
									data-date="2019-01-01T00:00:00Z"
									data-date-format="yyyy-mm-dd hh:ii:ss"
									data-link-field="dtp_input1">
									<input class="form-control" style='font-size: 18px;' size="16"
										value="${recruit.startTime }" type="text" id="startTime"
										name="startTime" readonly="true"> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-th"></span></span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-4 text-right" style="margin-top: 1%">
									<h3>截止时间:</h3>
								</div>
								<div
									class="input-group date form_datetime col-md-7 form_datetime"
									data-date="2019-01-01T00:00:00Z"
									data-date-format="yyyy-mm-dd hh:ii:ss"
									data-link-field="dtp_input1">
									<input class="form-control" style='font-size: 18px;' size="16"
										type="text" value="${recruit.endTime }" id="endTime"
										name="endTime" readonly="true"> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-th"></span></span>
								</div>
							</div>
							<!-- 隐藏内容，向后台传值 -->
							<div style="display: none">
								<!-- 职位内容 -->
								<input name="positionContent" type="text" id="positionContent">
								<!-- 招聘人数 -->
								<input name="positionNumber" type="text" id="positionNumber">
								<!-- 专业及方向 -->
								<input name="positionProfession" type="text"
									id="positionProfession">
								<!-- 编制 -->
								<input name="compilationNature" type="text"
									id="compilationNature">
							</div>
						</form>
						<div class="form-group">
								<div class="col-sm-offset-3" style="margin-left: 38%;">
									<button type="button" class="btn btn-primary"
										onclick="submitButton()">提交</button>
									<button type="reset" class="btn btn-primary"
										onclick="history.back(-1)" style="margin-left: 24%">取消</button>
								</div>
							</div>
					</div>
				</div>
			</div>
			<ul class="col-md-12" style="padding: 0; display: none">
				<li class="col-md-10"
					style="padding: 0; background-color: rgba(245, 246, 246, 0.8);"
					id="liContentOne">
					<div class="col-md-12" style="padding: 0">
						<div class="col-md-8" style="padding: 0">
							<input id="position" name="position" type="text" placeholder="职业"
								style='font-size: 18px;' list="positionlist">
							<datalist id="positionlist">
								<c:forEach items="${positionName }" var="positionName">
									<option label="${positionName }" value="${positionName }" />
								</c:forEach>
							</datalist>
						</div>
						<div class="col-md-4">
							<input type="number" placeholder="人数" name="positionNum"
								style='font-size: 18px; padding-top: 7%' id="positionNum"
								min="0" style="margin-top: 4%;" />
						</div>
					</div>
					<div class="col-md-12" style="padding: 0">
						<div class="col-md-7" style="padding: 0">
							<input id="professionalOrientation" style='font-size: 18px;'
								name="professionalOrientation" type="text" placeholder="专业及专业方向">
						</div>
						<div class="col-md-5">
							<select class="form-control" id="compilationNatureS"
								name="compilationNatureS" style="margin-top: 6%; padding: 0">
								<option>行政岗</option>
								<option>事业岗</option>
								<option>其他岗</option>
							</select>
						</div>
					</div>
				</li>
				<li class="col-md-2 text-right" style="float: left; margin-top: 5%"
					id="liContentTwo">
					<button type="button" class="btn btn-danger btn-sm" id="deleteDiv"
						onclick="removeLi(this)">删除</button>
				</li>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			$('#datetimepicker1').datetimepicker({
				format : 'YYYY-MM-DD',
				locale : moment.locale('zh-cn')
			});
			$('#datetimepicker2').datetimepicker({
				format : 'YYYY-MM-DD hh:mm',
				locale : moment.locale('zh-cn')
			});

		});
	</script>
</body>
</html>
