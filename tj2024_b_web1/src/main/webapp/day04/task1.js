//1. 등록 , 실행 조건 : 등록버튼 클릭 시 , write - js 내장 함수와 명이 동일하여 사용 불가
const visitwrite = () => {
	
	//1. HTML로부터 input dom객체 가져오기
		// - document.querySelector(선택자) : 선택자 마크업을 객체로 변환함수
	let contentInput = document.querySelector('.contentInput')
	let ageInput = document.querySelector('.ageInput')
	//2. 입력받은 값 가져오기
		// - .value : 마크업의 value 속성
	let content = contentInput.value;
	let age = ageInput.value;
	//3. 객체화
		// - {속성명 : 값, 속성명 : 값}
	let object = {'content' : content , 'age' : age};
	//4. fetch 통신
	let opt = {method : 'POST' , // HTTP METHOD 방법 선택
				headers : {'Contenet-Type' : 'application/json'}, // HTTP 요청 HEADERS CONTENT-TYPE
				body : JSON.stringify(object) // 본문에 보낼 자료를 문자열타입으로 변환
	}
	fetch(`/tj2024_b_web1/day03/visit2` , opt)
		.then(r => r.json()) // 응답 받은 body 자료를 json 타입으로 변환
		.then(data => { // 변환된 body 자료
			//5. 결과에 따른 화면 구현
			if(data == true){
				alert(`등록 성공`) // 만약에 받은 자료가 true 이면 성공
				visitFindAll(); // 등록했을 때 함수 실행
			}else{
				alert(`등록 실패`)
			}
			})
		.catch(e => {console.log(e);})
} // write end

//2. 조회(전체) // 실행 조건 : 1. JS열릴 때 , 2. 등록 성공했을 때
const visitFindAll = () =>{
	// 1. 어디에 : tbody
	let tbody = document.querySelector('tbody')
	
	// 2. 무엇을 , fetch 으로 받은 자료들
	let html = '';
		// 2_1. fetch 이용한 서블릿에게 자료(HTTP GET METHOD) 요청
		const opt = {method : 'GET'}
		// 2_2. fetch
		fetch('/tj2024_b_web1/day03/visit2')
			.then(r => r.json()) // 성공 시 json 문자열 타입으로 변환
			.then(data => {
				// 방법 1 .for(let i = 0; i<=data.length-1; i++){}
				// 방법 2.
					data.forEach(dto => {
						html += `<tr>
						<td>${dto.num}</td>
						<td>${dto.content}</td>
						<td>${dto.age}</td>
						<td>
							<button onclick="visitUpdate(${dto.num})"> 수정 </button>
							<button onclick="visitDelete(${dto.num})"> 삭제 </button>
						</td>
						</tr>`
					})
	//3. 출력
		//.innerHTML : 지정한 마크업에 html문자열 속성
			tbody.innerHTML = html;
			})
	
}
visitFindAll(); // JS 시작할 때 함수 실행

//3. 수정 , 누구를 삭제할 건지 = num[pk]
const visitUpdate = (num) => { // prompt()함수로 수정할 content/age 받기
	// 1. 수정할 식별자(num)
	
	// 2. 수정할 내용 content/age
	let newContent = prompt('new Content : ');
	let newAge = prompt('new Age : ');
	// 3. 객체화 , { 수정할 번호 , 수정할 내용 , 수정할 나이}
	let dataObj = {num : num , content : newContent , age : newAge}
	// 3. fetch 이용한 서블릿에게 HTTP PUT METHOD 처리 요청 , BODY
	const opt = {method : 'PUT',
				headers : {'Content-Type' : 'application/json'},
				body : JSON.stringify(dataObj) //== JSON.stringfy({num : num , content : newContent , age : newAge})
	}
		fetch(`/tj2024_b_web1/day03/visit2` , opt)
		.then(r => r.json())
		.then(data => {
			if(data == true){alert('수정 성공'); visitFindAll();}
			else(alert('수정 실패'))
			.catch(e => {console.log(e);})
		})
	
}
//4. 삭제 , 누구를 삭제할 건지 = num[pk]
const visitDelete = (num) => {
	// 1. 삭제할 식별자(num)
		const opt = {method : 'DELETE'}
		fetch(`/tj2024_b_web1/day03/visit2?num=${num}` , opt)
		.then(r => r.json()) // 응답 결과를 json타입으로 변환
		.then( data => {
			if(data == true){alert('삭제 성공'); visitFindAll();} // 만약 응답이 true이면 alert 안내 후 출력함수호출
			else{alert('삭제 실패')}
		})
		.catch(e => {console.log(e);})
	// 2. fetch 이용한 서블릿에게 HTTP delete Method 처리 요청
}