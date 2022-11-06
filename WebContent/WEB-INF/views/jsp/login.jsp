<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div id="image">
		<img src="images/anarlysis.jpg" height=50px; width=auto;>
	</div>
	<div id="container">

		<div id="title">AnaRlysis 로그인</div>

		<div id="subtitle">
			계정이 없으신가요? <a href="./registration">새 계정 만들기 ></a>
		</div>

		<div id="form">
			<form action="./login" modelAttribute="login" method="post">

				<div class="idForm">
					<input type="text" name="id" class="id" placeholder="아이디">
				</div>
				<div class="passForm">
					<input type="text" name="password" class="pw" placeholder="패스워드"
						autocomplete="off">
				</div>
				<div id="message">${message}</div>
				<%
				session.setAttribute("message", "");
				%>

				<div id="submitbox">
					<input type="submit" id="submit_button" value="로그인" />
				</div>

			</form>
		</div>
	</div>

</body>
</html>