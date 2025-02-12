//[1] 로그인 정보 요청 함수 
const getLoginInfo = ( ) => {
        const option = { method : 'GET' } 
        
        let loginmenu = document.querySelector('.loginmenu'); // (1)로그인 메뉴를 출력할 구역 가져오기 
        
        let html = ``; // (2) html 변수 선언 
        
        fetch( '/tj2024_b_web1/member/info' , option )
                .then( r => r.json() )
                .then( data =>{
                        console.log( data ); // 코드 변경후 서버가 자동 재실행이 되면 세션 초기화.
                        if( data == null ){console.log('비로그인상태');
                                // (3) 각 상태에 따라 로그인 메뉴 구성
                                html += `<li class="nav-item"> <a class="nav-link" href="/tj2024_b_web1/member/login.jsp">로그인</a> </li>
                                                <li class="nav-item"> <a class="nav-link" href="/tj2024_b_web1/member/signup.jsp">회원가입</a> </li>`
                        }
                        else{ console.log('로그인상태');
                                // (3) 각 상태에 따라 로그인 메뉴 구성
                                html += `<li class="nav-item dropdown">
                                                  <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                    <img class="header_profile" src="/tj2024_b_web1/upload/${ data.mimg }" /> ${ data.mid } 님 
                                                  </a>
                                                  <ul class="dropdown-menu">
												  	 <li class="nav-item"> <a class="nav-link" href="#"> ${ data.mpoint} POINT </a> </li>
                                                     <li class="nav-item"> <a class="nav-link" href="/tj2024_b_web1/member/info.jsp">마이페이지</a> </li>
                                                     <li class="nav-item"> <a class="nav-link" href="#" onclick="onLogOut()">로그아웃</a> </li>
                                                  </ul>
                                                </li> `
                        }
                        // (4) 구성한 메뉴들을 innerHTML 한다.
                        loginmenu.innerHTML = html;
                })
                .catch( e => {console.log(e); })
}; // f end 
getLoginInfo(); // JS가 열렸을때 최초 1번 실행

// [2] 로그아웃 요청 함수 
const onLogOut = ( ) => {
        const option = { method : 'DELETE' }
        fetch( '/tj2024_b_web1/member/login' , option )
                .then( response =>  response.json() )
                .then( data => {
                        if( data==true){ 
                          alert('로그아웃합니다.'); 
                          location.href="/tj2024_b_web1/member/login.jsp"; 
                        }
                })
                .catch( e => {console.log(e); })
} // f end 

// [3] 클라이언트 웹소켓의 객체를 생성하고 서버소켓으로부터 접속 연동 시도
const alarmSocket = new WebSocket('ws://localhost:8080/tj2024_b_web1/alarmsocket');
// [4] 클라이언트 웹소켓이 서버소켓으로부터 메세지를 받았을 때
alarmSocket.onmessage = ( msgEvent ) => {
        alert( msgEvent ); // 알람 메시지를 alert 띄우기
		// 부트스트랩 이용한 부트스랩의 토스트
		// 1. 어디에
		const alarmbox = document.querySelector('.alarmbox')
		// 2. 무엇을
		html += `<div class="toast show" role="alert" aria-live="assertive" aria-atomic="true">
				  <div class="toast-header">
				    <strong class="me-auto">${msgEvent.data}</strong>
				    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
				</div>
				</div>`;
		// 3. 출력
		alarmbox.innerHTML = html;
		
} // f end 
