<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="javax.servlet.http.HttpServletRequest, java.io.File, kr.ac.jbnu.se.awp.gitplay4.core.*, kr.ac.jbnu.se.awp.gitplay4.model.ChartType"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<title>dynamic form</title>
<link rel="stylesheet" href="css/dynamic.css">
</head>
<%
File csvFile = FileManager.getRecentCsv((String) session.getAttribute("id"));
CsvAnalyzer analyzer = new CsvAnalyzer(csvFile.getPath(), true);

String[] givenColName;
givenColName = analyzer.getColumnNames();
int givenColNum;


%>
<body>
	<div id="image">
		<img src="images/anarlysis.jpg" height=50px; width=auto;>
	</div>

	<div id="container">
		<div id="title">어떤 차트가 필요하세요?</div>
		<div id="subtitle">차트 생성을 위해 필요한 옵션을 입력해주세요.</div>

		<div id="form">
			<form method="post" action="generate">

				<div class="chartNameForm">
					<input type="text" name="chartName" class="id" placeholder="차트 이름">
				</div>
				<%ChartType type = (ChartType)session.getAttribute("chartType");%>
				<%if(type.toString().equals("LINE")||type.toString().equals("BOX")){ %>

				<div class="selectbox">
					<label for="xAxis">x축</label> <select name="xAxis">
						<%
						for (String name : givenColName) {
						%>
						<option><%=name%>
						</option>
						<%
						}
						%>
					</select>
				</div>
				<%}%>
				<div class="selectbox">
					<label for=yAxis>y축</label> <select name="yAxis">
						<%
						for (String name : givenColName) {
						%>
						<option><%=name%>
						</option>
						<%
						}
						%>
					</select>
				</div>

				<div class="rangeForm">
					<input type="text" name="ymin" class="range" placeholder="최솟값">
					~ <input type="text" name="ymax" class="range" placeholder="최댓값">
				</div>

				<div id="submitbox">
					<input type="submit" id="submit_button" value="제출" />
				</div>
			</form>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			var selectTarget = $('.selectbox select');
			selectTarget.change(function() {
				var select_name = $(this).children('option:selected').text();
				const length = 25;
				if(select_name.length > length){
					select_name = select_name.substring(0,length) + '...';
				}
				$(this).siblings('label').text(select_name);
			});
		});
	</script>
</body>
</html>