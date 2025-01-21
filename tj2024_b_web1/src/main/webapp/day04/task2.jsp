<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3> 대기번호 발행 프로그램 </h3>

	<div>
		<h5> 대기등록 </h5>
		전화번호 : <input class="tell" /> <br/>
		인원수 : <input class="count"/> <br/>
		<button onclick="add()">등록</button>
	</div>
	
	<div>
		<table border="1">
			<thead>
				<tr>
					<th>num</th>
					<th>tel</th>
					<th>count</th>
					<th>etc</th>
				</tr>
			</thead>
			<tbody>
			
			</tbody>
		</table>
	</div>
	<script src="task2.js"></script>
</body>
</html>