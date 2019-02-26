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
<script type="text/javascript">
					$(function () {
		
		    //1.初始化Table
		    var oTable = new TableInit();
		    oTable.Init();
		
		    //2.初始化Button的点击事件
		    var oButtonInit = new ButtonInit();
		    oButtonInit.Init();
		
		});
		
		
		var TableInit = function () {
		    var oTableInit = new Object();
		    //初始化Table
		    oTableInit.Init = function () {
		        $('#tb_departments').bootstrapTable({
		            url: '/Home/GetDepartment',         //请求后台的URL（*）
		            method: 'get',                      //请求方式（*）
		            toolbar: '#toolbar',                //工具按钮用哪个容器
		            striped: true,                      //是否显示行间隔色
		            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		            pagination: true,                   //是否显示分页（*）
		            sortable: false,                     //是否启用排序
		            sortOrder: "asc",                   //排序方式
		            queryParams: oTableInit.queryParams,//传递参数（*）
		            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
		            pageNumber:1,                       //初始化加载第一页，默认第一页
		            pageSize: 10,                       //每页的记录行数（*）
		            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
		            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
		            strictSearch: true,
		            showColumns: true,                  //是否显示所有的列
		            showRefresh: true,                  //是否显示刷新按钮
		            minimumCountColumns: 2,             //最少允许的列数
		            clickToSelect: true,                //是否启用点击选中行
		            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
		            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
		            cardView: false,                    //是否显示详细视图
		            detailView: false,                   //是否显示父子表
		            columns: [{
		                checkbox: true
		            }, {
		                field: 'Name',
		                title: '报名号'
		            }, {
		                field: 'ParentName',
		                title: '报名人'
		            }, {
		                field: 'Desc',
		                title: '报名职位'
		            }, {
		                field: 'Level',
		                title: '政治面貌'
		            }, {
		                field: 'Desc',
		                title: '学历'
		            }, {
		                field: 'Desc',
		                title: '电话'
		            },{
		                field: 'Desc',
		                title: '邮箱'
		            }, {
		                field: 'Desc',
		                title: '学历'
		            }, {
		                field: 'Desc',
		                title: '工作经历'
		            }, {
		                field: 'Desc',
		                title: '学历'
		            },  ]
		        });
		    };
		
		    //得到查询的参数
		    oTableInit.queryParams = function (params) {
		        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
		            limit: params.limit,   //页面大小
		            offset: params.offset,  //页码
		            departmentname: $("#txt_search_departmentname").val(),
		            statu: $("#txt_search_statu").val(),
					search:params.search
					
		        };
		        return temp;
		    };
		    return oTableInit;
		};
		
		
		var ButtonInit = function () {
		    var oInit = new Object();
		    var postdata = {};
		
		    oInit.Init = function () {
		        //初始化页面上面的按钮事件
		    };
		
		    return oInit;
		};
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
						href="${pageContext.request.contextPath}/admin/readStudentInfo"
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
					<!-- Advanced Tables -->
					<div class="card">
						<!-- 内容start -->
						<div class="main">
							<!-- 页头 -->
							<div class="jumbotron">
								<h1>Hello, world!</h1>
								<p>...</p>
								<p>
									<a class="btn btn-primary btn-lg" href="#" role="button">Learn
										more</a>
								</p>
							</div>
							<!-- 汇总信息 -->
							<div class="alert alert-info" role="alert">

								<h3>
									当前报名总人数：<strong>1000</strong>人
								</h3>
								累计报名<strong>1000</strong>人，其中博士<strong>1000</strong>人、硕士<strong>1000</strong>人、学士<strong>1000</strong>人，疆内户籍<strong>1000</strong>人，双一流院校<strong>1000</strong>人，双一流学科<strong>1000</strong>人。
								<h3>
									当日报名<strong>100</strong>人
								</h3>
								今日报名博士<strong>100</strong>人、硕士<strong>100</strong>人、学士<strong>100</strong>人，疆内户籍<strong>100</strong>人，双一流院校<strong>100</strong>人，双一流学科<strong>100</strong>人。

							</div>

							<!-- 表格 -->
							<div class="panel-body" style="padding-bottom: 0px;">
								<div class="panel panel-default">
									<div class="panel-heading">查询条件</div>
									<div class="panel-body">
										<form id="formSearch" class="form-horizontal">
											<div class="form-group" style="margin-top: 15px">
												<label class="control-label col-sm-1"
													for="txt_search_departmentname">部门名称</label>
												<div class="col-sm-3">
													<input type="text" class="form-control"
														id="txt_search_departmentname">
												</div>
												<label class="control-label col-sm-1" for="txt_search_statu">状态</label>
												<div class="col-sm-3">
													<input type="text" class="form-control"
														id="txt_search_statu">
												</div>
												<div class="col-sm-4" style="text-align: left;">
													<button type="button" style="margin-left: 50px"
														id="btn_query" class="btn btn-primary">查询</button>
												</div>
											</div>
										</form>
									</div>
								</div>

								<div id="toolbar" class="btn-group">
									<button id="btn_add" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
									</button>
									<button id="btn_edit" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
									</button>
									<button id="btn_delete" type="button" class="btn btn-default">
										<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
									</button>
								</div>
								<table id="tb_departments"></table>
							</div>
						</div>

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
