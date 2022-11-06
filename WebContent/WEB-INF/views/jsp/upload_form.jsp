<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload</title>
<link rel="stylesheet" href="css/upload.css">
</head>
<body>
	<div id="image">
		<img src="images/anarlysis.jpg" height=50px; width=auto;>
	</div>
	<div id="container">
		<div id="title">차트 생성을 시작할까요?</div>
		<div id="subtitle">파일을 업로드하세요.</div>
		<form method="post" action="UploadServlet"
			enctype="multipart/form-data">
			<div id="upload">
				<div class="filebox">
					<input class="upload-name" value="첨부파일" placeholder="첨부파일">
					<label for="file"> 파일찾기 <input type="file" id="file"
						name="multiPartServlet" multiple="multiple" />
					</label>
				</div>

				<!-- 		파일선택: <input type="file" name="multiPartServlet" multiple="multiple"/>
    			<div id="submitbox">
        			<input type="submit" id="submit_button" value="Upload"/>
        		</div>-->
			</div>
			<div id="submitbox">
				<input type="submit" id="submit_button" value="Upload" />
			</div>

		</form>


	</div>

	<script>
		$("#file").on('change', function() {
			var fileName = $("#file").val();
			$(".upload-name").val(fileName);
		});
	</script>
</body>
</html>
