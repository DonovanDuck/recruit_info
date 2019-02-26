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

<title>添加用户信息</title>
<style>
	.addUserInfo_head{
		height: 70px;
   		width: 190px;
   		position: relative;
    left: 27%;
	}
	.addUserInfo_main{
		margin: 60px;
		
	}
	.addUserInfo_form{
		width: 500px;
    margin-left: 26%;
	}
	.form-group{
		margin-bottom: 40px;
	}
	.confirm{
		width: 50px;
		position: relative;
		left: 10px;
		height: 40px;
		margin-right: 10px;
	}
</style>

</head>
<body>
	<div class="addUserInfo_main">
		<div class="addUserInfo_head">
			<h2>添加用户信息</h2>
		</div>
		<div class="addUserInfo_form">
			<form class="form-horizontal" action="">
				<div class="form-group">
				    <label for="organization" class="col-sm-2 control-label" style="padding-right: 28px;">单位</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="organization" name="organization"  placeholder="单位">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="principal" class="col-sm-2 control-label">负责人</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="principal" name="userName" placeholder="负责人">
				    </div>
  				</div>
  				<div class="form-group">
				    <label for="WeChat" class="col-sm-2 control-label">微信号</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" name="weChat" id="WeChat" placeholder="微信号">
				    </div>
  				</div>
  				<div class="form-group">
				    <div class="col-sm-10" style="margin-left: 15px;">
				    	<input class="btn btn-default" type="button" value="重置密码" style="float:left;margin-right: 28px;">
				    	<input type="text" class="form-control" disabled="disabled" value="原始密码：123456" style="width: 300px;">
				    </div>
  				</div>
  				<div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10 confirm" style="float: left">
				      <button type="submit" class="btn btn-success">确定</button>
				    </div>
				    <div class="col-sm-offset-2 col-sm-10 confirm">
				      <button type="text" class="btn btn-default">取消</button>
				    </div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>