<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

<head>

<meta charset="utf-8">
<title>Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- CSS -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/supersized.css">
<link rel="stylesheet" href="css/style.css">

</head>

<body oncontextmenu="return false">

	<div class="page-container">
		<h1>Register</h1>
		<form action="register" method="post">
<div class="registerForm" style="display: block">
			<div>
				<input type="text" name="firstName" placeholder="firstName" />
			</div>

			<div>
				<input type="text" name="lastName" placeholder="lastName"/>
			</div>
			
			<div>
                <input type="text" name="email" placeholder="email"/>
            </div>

			<div>
				<input type="password" name="pwd" placeholder="password">
			</div>

			<div>
				<input type="password" name="pwd1" placeholder="confirm your password">
			</div>

			<button id="next1" type="button">next</button>
</div>
			<div class="form1" style="display: none">
				<h2>关于你...</h2>

				<div>
					<!-- <input type="text" name="sex" placeholder="W/M"> -->
					<input type="radio" name="sex" value="0"/>Man
                    <input type="radio" name="sex" value="1" checked="checked" />Lady
				</div>

				<div>
					<input type="text" name="birthDay" placeholder="出生日期">
				</div>

				<div>
					<input type="text" name="place" placeholder="你从哪来">
				</div>

				<div>
					<input type="text" name="job" placeholder="你是做什么的">
				</div>
				
				<div>
                    <input type="text" name="linkedIn" placeholder="LinkedIn host page">
                </div>
                
				<button id="submit" type="submit">Sign in</button>
			</div>
		</form>

	</div>

	<div class="alert" style="display: none">
		<h2>消息</h2>
		<div class="alert_con">
			<p id="ts"></p>
			<p style="line-height: 70px">
				<a class="btn">确定</a>
			</p>
		</div>
	</div>

	
	<script src="http://apps.bdimg.com/libs/jquery/1.6.4/jquery.min.js"
		type="text/javascript" type="text/javascript"></script>
	<script src="supersized.3.2.7.min.js"></script>
	<script src="supersized-init.js"></script>
	<script>
		$(".btn").click(function() {
			is_hide();
		})
		var u = $("input[name=pwd]");
		var p = $("input[name=pwd1]");
		$("#next1").live('click', function() {
			if (u.val() == '' || p.val() == '') {
				$("#ts").html("密码不能为空~");
				is_show();
				return false;
			} else {
				if (u.val() != p.val()) {
					$("#ts").html("两次输入密码不一致");
					is_show();
					return false;}
					else {
						$(".registerForm").hide({
			                "right" : "500px"
			            }, 600)
			            $(".form1").show({
			                "right" : "500px"
			            }, 600)
					}
				}
			}
			
			
		);
		
		function is_hide() {
			$(".alert").animate({
				"top" : "-40%"
			}, 300)
		}
		function is_show() {
			$(".alert").show().animate({
				"top" : "45%"
			}, 300)
		}
	</script>
</body>
</html>

