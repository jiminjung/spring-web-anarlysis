<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link rel="stylesheet" href="css/login.css">
<link
	rel="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
</head>
<body>
	<div id="image">
		<img src="images/anarlysis.jpg" height=50px; width=auto;>
	</div>
	<div id="container">

		<div id="title">AnaRlysis 가입</div>

		<div id="subtitle">
			이미 사용 중인 계정이 있으신가요? <a href="./login">AnaRlysis 계정으로 로그인하기 ></a>
		</div>

		<div id="form">
			<form action="registration" method="post">

				<div class="idForm">
					<input type="text" name="id" class="id" placeholder="아이디">
				</div>
				<div class="passForm">
					<input type="password" name="password" class="pw"
						placeholder="패스워드" autocomplete="off">
				</div>

				<div class="passForm">
					<input type="password" name="passwordConfirm" class="pw"
						placeholder="패스워드 확인" autocomplete="off">
				</div>

				<div id="submitbox">
					<input type="submit" id="submit_button" value="가입하기" />
				</div>

			</form>
		</div>
	</div>
</body>
</html>