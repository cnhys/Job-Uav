<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>首页</title>

<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.laber {
	font-size: 16px;
	color: #424e67;
	margin-right: 15px
}

input {
	background: #f1f2f4;
	width: 260px;
	height: 40px;
	border: none;
	border-radius: 8px;
	outline: none;
	padding-left: 20px
}

button {
	width: 260px;
	height: 40px;
	display: inline-block;
	border: none;
	background: #3b8cff;
	border-radius: 8px;
	color: #ffffff;
}
</style>
</head>
<body style="background: #ededed">
	<div class="container">
		<p></p>
		<div
			style="background: #eff4f8; height: 600px; display: flex; justify-content: center; align-items: center">
			<div style="width: 500px; height: 390px;">
				<p style="font-size: 30px; text-align: center; height: 60px;">中间件对应关系管理</p>

				<form id="login_form" action="<%=request.getContextPath()%>/sealLogin" method="post">
					<div style="width: 500px; height: 325px; border: solid 1px #ffffff; border-radius: 10px; box-shadow: 0px 0px 10px #cccccc; background: #ffffff">
						<div style="margin: 78px 0 20px 70px;">
							<span class="laber">账号:</span> <input id="account" name="account"
								type="text" value="admin" />
						</div>
						<div style="margin-left: 70px">
							<span class="laber">密码:</span> <input id="pwd" name="pwd"
								type="password" />
						</div>
						<div style="margin-left: 110px; margin-top: 20px">
							<span class="laber"></span>
							<button class="login_bt" onclick="checkUser();">登录</button>
							<br />
							<br />
							<p id="nameExistMsg" style="color: red; font-size: 18px;display: none;margin-bottom: 20px;">密码错误,请重新输入...</p>
						</div>
					</div>
				</form>

			</div>
		</div>


	</div>

	</div>



	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		function checkUser() {
			var account = document.getElementById("account").value;
			var pwd = document.getElementById("pwd").value;
			if (account == "") {
				alert("用户名不能为空");
				return false;
			}
			if (pwd == "") {
				alert("密码不能为空");
				return false;
			} else {
				var pwdIsTrue = checkPwd(account, pwd);
				if(!pwdIsTrue){
					$("#nameExistMsg").show();
					return false;
				}else{
					document.getElementById("login_form").submit();
				}
				
			}
		}
		
		function checkPwd(name,pwd) {
			var exist = false;
			if (!name || name == '') {
				return exist;
			}
			$.ajax({
				url: '<%=request.getContextPath()%>/checkPwd',
				async: false,
				data : {account: name, pwd: pwd},
				success: function (res) {
					if (res == 1 || res == '1') {
						exist = true;
					}
				}
			});
			return exist;
		}
	</script>
</body>
</html>