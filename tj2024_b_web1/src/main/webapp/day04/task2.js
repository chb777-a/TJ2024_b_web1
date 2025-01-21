// 대기명단 등록
const add = () => {
	// input 가져오기
	let telInput = document.querySelector(".tell");
	let countInput = document.querySelector(".count")
	
	// 입력받은 값 가져오기
	let tel = telInput.value;
	let count = countInput.value;
	
	// 객체화
	let obj = {'tel' : tel, 'count' : count};
	
	// 4) fetch 함수를 통해 서블릿에 처리 요청
	const opt = {
		method : `POST`,
		headers : {'Content-Type' : "application/json"},
		body : JSON.stringify(obj)	
	}
	fetch(`/tj2024_b_web1/day03/waiting2`, opt)
		.then(r => r.json())	// 응답받은 자료 JSON 타입으로 변환
		.then(data => {
			if(data == true){		// 응답이 true 라면 성공
				alert("등록 성공")
				findAll()			// 출력 메소드 새로 고침(출력)
			}else{					// 응답이 false 라면 실패
				alert("등록 실패")
			}
		})
		.catch(e => {console.log(e)})
	
}
// 대기명단 출력
const findAll = () => {
	//출력 위치
	let tbody = document.querySelector("tbody");
	let html = ``;
	
	//fetch 함수를 이용해 서블릿에게 처리 요청
	const opt = {method : `GET`}
	fetch(`/tj2024_b_web1/day03/waiting2`, opt)
		.then(r => r.json())
		.then(data => {
			data.forEach(obj => {
				
				html += `<tr>
							<td>${obj.num}</td>
							<td>${obj.tel}</td>
							<td>${obj.count}</td>
							<td>
								<button onclick="updatePrint(${obj.num})">수정</button>
								<button onclick="deletePrint(${obj.num})">삭제</button>
							</td>
						</tr>`
			})
		
		// 지정한 위치에 출력	
		tbody.innerHTML = html;
		})
		.catch(e => {console.log(e)})
}
findAll(); // 출력함수 실행

// 대기명단 수정
const updatePrint = (num) => {	// 수정할 PK 키 매개변수로 받기
	// 수정할 값 받기
	let newTel = prompt("전화번호 수정 :")
	let newCount = prompt("인원 수정 :")
	
	// 객체화
	let obj = {num : num, tel : newTel, count : newCount}
	
	// fectch 함수를 이용하여 서블릿에게 처리 요청
	const opt = {
		method : `PUT`,
		headers : {'Content-Type' : "application/json"},
		body : JSON.stringify(obj)	// BODY 에게 보낼 데이터를 문자열 타입으로 변환
	}
	fetch(`/tj2024_b_web1/day03/waiting2`, opt)
		.then(r => r.json()) 	// 응답받은 데이터 JSON 타입으로 변환 	
		.then(data => {
			if(data == true){   	
				alert("수정 성공")
				findAll();		    // 출력 새로 고침
			}else{				   
				alert("수정 실패")
			}
		})
		.catch(e => {console.log(e)}) // 예외
}
// 대기명단 삭제
const deletePrint = (num) => {	// 삭제할 PK 키 매개변수로 받기
	// fetch 함수를 이용하여 서블릿에게 처리 요청 -> 쿼리스트링 통신
	const opt = {method : `DELETE`}
	fetch(`/tj2024_b_web1/day03/waiting2?num=${num}`, opt)
		.then(r => r.json())	// 응답받은 데이터를 JSON 타입으로 변환
		.then(data => {
			if(data == true){
				alert("삭제 성공")
				findAll()		// 출력 새로 고침
			}else{			
				alert("삭제 실패")
			}
		})
		.catch(e => {console.log(e)})	
}