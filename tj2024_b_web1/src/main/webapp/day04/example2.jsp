<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
	<h3> 클릭 이벤트로 람다식 함수 호출</h3>
	<button onclick="func1()"> 람다식 호출 </button>

	<h5> fetch 함수 이용한 HTTP 통신</h5>
	<button onclick="func2()"> fetch 호출 1 GET </button>
	<button onclick="func3()"> fetch 호출 2 POST </button>
	<button onclick="func4()"> fetch 호출 3 PUT </button>
	<button onclick="func5()"> fetch 호출 4 DELETE </button>

	<h5> fetch 함수 이용한 HTTP queryString</h5>
	<button onclick="func6()"> fetch 호출 5 GET </button>
	<button onclick="func7()"> fetch 호출 6 POST </button>
	<button onclick="func8()"> fetch 호출 7 PUT </button>
	<button onclick="func9()"> fetch 호출 8 DELETE</button>
	
	<h5> fetch 함수 이용한 HTTP HEADER BODY 통신</h5>
	<button onclick="func10()"> fetch 호출 9 POST </button>
	<button onclick="func11()"> fetch 호출 10 PUT </button>
	<span> ***GET / DELETE method는 BODY 비권장*** </span>
	
	<h5> fetch 함수 이용한 HTTP HEADER BODY 응답 통신</h5>
	<button onclick="func12()"> fetch 호출 11 </button>
	<button onclick="func13()"> fetch 호출 12</button>
	<button onclick="func14()"> fetch 호출 13</button>
	<button onclick="func15()"> fetch 호출 14</button>
</div>

 <script src="example2.js"></script>
</body>
</html>