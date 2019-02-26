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
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>用户信息</title>
<style>
	.userInfo_main{
		margin: 60px;
		
	}
	.userInfo_head{
		height: 70px;
   		width: 135px;	
	}
	.userInfo_addInfo{
		height: 50px;
    width: 58px;
    position: relative;
    left: 1045px;
	}
	.userInfo_TD{
		width: 331px;
    	padding-right: 51px;
	}
</style>

</head>

<body>
	<div class="userInfo_main">
		<div class="userInfo_head">
			<h2>用户信息</h2>
		</div>
		<div class="userInfo_addInfo">
			<button class="btn btn-success" type="submit">添加用户</button>
		</div>
		<div class="userInfo_table">
			<table class="table table-condensed">
				<thead>
					<tr>
						<th>编号</th>
						<th>单位</th>
						<th>负责人</th>
						<th>修改</th>
					</tr>
				</thead>
				<tbody>
				<form action="">
					<tr>
						<%-- <td>${requestScope.offset+status.index}</td> --%>
						<td style="width: 62px;">1</td>
						<td class="userInfo_TD"><input class="form-control" type="text" value="新疆人事局"></td>
						<td class="userInfo_TD"><input class="form-control" type="text" value="jkjkjkjkj"></td>
						<td class="userInfo_TD"><input class="btn btn-default" type="submit" value="修改"></td>
					</tr>
				</form>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>