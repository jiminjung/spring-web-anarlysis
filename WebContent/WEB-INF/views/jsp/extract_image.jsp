
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko">
<head>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page
	import="java.util.*,java.io.*,kr.ac.jbnu.se.awp.gitplay4.core.FileManager"%>
<%@ page import="java.sql.*"%>

<%
//String fullpath = FileManager.getRecentChartFile((String) session.getAttribute("id")).getPath();
%>


<title>Chart Generated!</title>
<link rel="stylesheet" href="css/chart.css">
</head>
<body>
	<div id="image">
		<img src="images/anarlysis.jpg" height=50px; width=auto;>
	</div>

	<div id="container">

		<div id="blank"></div>

		<div id="chart">
			<img src="images/generatedChart" width=512 height=384></img>
		</div>

		<div id="download">
			<form method="get" action="download/<%=session.getAttribute("id")%>">
				<button type="submit">�ٿ�ε�</button>
			</form>
		</div>

		<div id="additional">
			�ٸ� ��Ʈ�� �ʿ��ϽŰ���? <br> <a href="UploadServlet">��Ʈ �ٽ� �����Ϸ� ����
					></a> <br> <a href="./upload">���ο� ���� ���ε��Ϸ� ���� ></a>
		</div>

	</div>
</body>
</html>