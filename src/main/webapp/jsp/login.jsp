<%@page import="java.security.SecureRandom"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Title</title>
<%
	SecureRandom random = new SecureRandom();
	random.setSeed(8738);
	double _csrf = random.nextDouble();
	session.setAttribute("_csrf", _csrf);
%>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script>
	$(document).ready(function() {

		var entrance = document.getElementById('entrance');
		var entrance_content = document.getElementById('entrance_content');
		entrance.onclick = function() {
			entrance_content.style.display = 'inline';
			entrance.style.display = 'none';
		}
	});
	function gradeChange() {
		if ($('#select option:selected').val() == 'teacher') {
			var newUrl = '${pageContext.request.contextPath}/teacher/teacherLogin'; //设置新提交地址
			$("#myform").attr('action', newUrl); //通过jquery为action属性赋值
		}
		if ($('#select option:selected').val() == 'student') {
			var newUrl = '${pageContext.request.contextPath}/student/LoginStudent'; //设置新提交地址
			$("#myform").attr('action', newUrl); //通过jquery为action属性赋值
		}
		if ($('#select option:selected').val() == 'manager') {
			var newUrl = '${pageContext.request.contextPath}/admin/LoginAdmin'; //设置新提交地址
			$("#myform").attr('action', newUrl); //通过jquery为action属性赋值

		}
	}
	function CheckPost() {
		if (addForm.employeeNum.value == "") {
			alert("请填写用户名！");
			addForm.username.focus();
			return false;
		}
		if (addForm.password.value == "") {
			alert("请填写密码！");
			addForm.username.focus();
			return false;
		}
		return true;
	}
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/login.css" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Addmin/bootstrap.css"
	type="text/css">

</head>
<body id="body">
	<div class="Card">
		<div class="card_top">
			<div class="icon_banner">新疆&nbsp;&nbsp;&nbsp;招聘信息系统</div>
		</div>
		<div class="card_bottom">
			<div class="login_content">
				<div class="login_form_area">
					<form id="myform"
						action="${pageContext.request.contextPath}/user/userLogin"
						name="addForm" class="login_form" onsubmit="return CheckPost();">
						<div class="login-account">
							<div class="id_select">
								<select id="select" onchange="gradeChange()">
									<option value="teacher">用户</option>
									<option value="student">管理员</option>
								</select> <span class="select_icon"
									style="display: inline-flex; align-items: center;">&#8203;
									<svg class="Zi Zi--Select Select-arrow" fill="currentColor"
										viewBox="0 0 24 24" width="24" height="24">
                                        <path
											d="M12 16.183l2.716-2.966a.757.757 0 0 1 1.064.001.738.738 0 0 1 0 1.052l-3.247 3.512a.758.758 0 0 1-1.064 0L8.22 14.27a.738.738 0 0 1 0-1.052.758.758 0 0 1 1.063 0L12 16.183zm0-9.365L9.284 9.782a.758.758 0 0 1-1.064 0 .738.738 0 0 1 0-1.052l3.248-3.512a.758.758 0 0 1 1.065 0L15.78 8.73a.738.738 0 0 1 0 1.052.757.757 0 0 1-1.063.001L12 6.818z"
											fill-rule="evenodd">
                                        </path>
                                    </svg>
								</span>
							</div>
							<span class="login-accountSeperator"></span>
							<div class="account_input">
								<input name="employeeNum" id="employeeNum"
									placeholder="使用工号、手机号或邮箱登录" />
							</div>
						</div>
						<div class="password_content">
							<div class="password_input">
								<div class="password_content_input">
									<input name="password" placeholder="输入密码" type="password"
										id="password">
								</div>
							</div>
							<div class="otherMethorToLogin">
								<a href="">忘记密码</a>
							</div>
						</div>
						<button type="submit" class="submit">登录</button>
						<input type="hidden" name="_csrf" value="<%=_csrf%>" />
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>