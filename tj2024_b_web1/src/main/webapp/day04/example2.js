// 1. 람다식 함수 정의
// const func1 = () => {}
const func1 = () => {
	console.log(`func1 excute`)
	
}

// 2. 람다식 함수 정의 안에서 fetch 함수 활용
const func2 = () => {
	// *** fetch : HTTP 비동기 통신 제공하는 함수
	// day02의 example1 서블릿 클래스의 doget 메소드 매핑
	fetch(`http://localhost:8080/tj2024_b_web1/day02/example1`)
}

const func3 = () => {
	//POST 메소드 매핑
	fetch(`/tj2024_b_web1/day02/example1` , {method : `POST`})
	
}

const func4 = () => {
	//PUT 메소드 매핑
	fetch(`/tj2024_b_web1/day02/example1` , {method : `PUT`})

}

const func5 = () => {
	//DELETE 메소드 매핑
	fetch(`/tj2024_b_web1/day02/example1` , {method : `DELETE`})
	
}

const func6 = () => {
	let name = `유재석`;
	let age = 40;
	
	fetch(`/tj2024_b_web1/day02/example2?name=${name}&age=${age}`)
}

const func7 = () => {
	let name = `신동엽`;
	let age = 30;
	const opt = {method : `POST`}
	
	fetch(`/tj2024_b_web1/day02/example2?name=${name}&age=${age}` , opt)
}

const func8 = () => {
	let name = `서장훈`;
	let age = 32;
	const opt = {method : `PUT`}

	fetch(`/tj2024_b_web1/day02/example2?name=${name}&age=${age}` , opt)
}

const func9 = () => {
	let name = `민경훈`;
	let age = 37;
	const opt = {method : `DELETE`}

	fetch(`/tj2024_b_web1/day02/example2?name=${name}&age=${age}` , opt)
}

const func10 = () => {
	let object = {data1 : `유재석` , data2 : 50}
	const opt = {
		method : 'POST' ,
		header : {'Content-Type' : `application/json`} ,
		body : JSON.stringify( object ) // HTTP 통신은 문자열 자료만 전송이 가능		
	} // opt end
	fetch(`/tj2024_b_web1/day03/example3` , opt);
}

const func11 = () => {
	let object = {data1 : '서장훈' , data2 : 55}
	const opt = {
		method : `PUT` ,
		header : {'Content-Type' : `application/json`} ,
		body : JSON.stringify( object )
	}
	fetch(`/tj2024_b_web1/day03/example3` , opt);
}

const func12 = () => {
	const opt = {method : `GEt`}
	fetch(`/tj2024_b_web1/day03/example5` , opt)
	.then( response => response.json())
	.then(data => {console.log(data);})
}

const func13 = () => {
	const opt = {method : `POST`}
	fetch(`/tj2024_b_web1/day03/example5` , opt)
	.then(response = response.text())
	.then(data => {console.log(data);})
}

const func14 = () => {
	const opt = {method : `PUT`}
	fetch(`/tj2024_b_web1/day03/example5` , opt)
		.then( response => response.json()) // 통신 성공시
		.then(data => {console.log(data);})
		.catch(error => {console.log(error);}) // 통신 실패시
}

const func15 = () => {
	const opt = {method : `DELETE`}
	fetch(`/tj2024_b_web1/day03/example5` , opt)
		.then( r => r.json()) // 통신 성공시
		.then(data => {console.log(data);})
		.catch(e => {console.log(e);}) // 통신 실패시
}

/*
	fetch(`HTTP ULR` , {옵션})
	.then(response객체 = > response.타입())
	.then(타입변환자료 =>{실행문})
	
	URL
		1. 통신할 서블릿의 URL 주소
		2. 쿼리스트링
	
	옵션
		1. METHOD
			- GET : fetch(`HTTP ULR` , {method : `GET`})
			 -> GET METHOD는 기본값이므로 생략이 가능하다.
			 
			- POST : fetch(`HTTP ULR` , {method : `POST`})
			- PUT : fetch(`HTTP ULR` , {method : `PUT`})
			- DELETE : fetch(`HTTP ULR` , {method : `DELETE`})
			
		2. HEADER
			- {'Content-Type' : `application/json`}
		3. BODY
			- 전송할 데이터 자료
	
	.then()
		1. 응답객체 : 통신한 응답의 정보가 담긴 HTTP 응답 객체 변환
			.then ( 응답객체명 => 응답객체명.json() ) : response content-type : application/json 변환
			.then( 응답객체명 => 응답객체명.text() ) : response content-type : text/plain 변환
	then()
		1. 변환된 자료 객체
	catch()
		
	[참고 : 백틱]
	`` : 백틱 템플릿 : 문자열 사이에 변수/함수 호출과 연산식을 넣을 수 있는 템플릿
		`문자열 ${변수명} 문자열${함수명()} 문자열 ${연산}`
	
	[참고 : JSON 문자열 타입변환]
		1. JSON.parse()		:	문자열 타입 ---> JSON타입으로 변환 함수
		2. JSON.stringfy()	:	JSON타입 ---> 문자열 타입 변환 함수
	*/