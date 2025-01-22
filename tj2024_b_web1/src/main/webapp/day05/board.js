// [1] 게시물 등록 
const boardWrite = () => {
        // 1. input DOM 가져오기 
        let writerInput = document.querySelector('.writerInput')
        let pwdInput = document.querySelector('.pwdInput')
        let titleInput = document.querySelector('.titleInput')
        let contentInput = document.querySelector('.contentInput')
        // 2. DOM 에서 value 가져오기 
        let bwriter = writerInput.value;
        let bpwd = pwdInput.value;
        let btitle = titleInput.value;
        let bcontent = contentInput.value;
        // 3. 객체화
        let dataObj = {
                bwriter : bwriter , bpwd : bpwd ,
                btitle : btitle , bcontent : bcontent
        }
        // 4. fetch 옵션 
        const option = {
                method : 'POST' , 
                Headers : { 'Content-Type' : 'application/json'},
                body : JSON.stringify( dataObj )
        }
        // 5. fetch 
        fetch( '/tj2024b_web1/day05/board' , option )
                .then( response => response.json() )
                .then( data => {
                        if( data == true ){ alert('글쓰기성공'); location.href="/tj2024b_web1/day05/board.jsp";}
                        else{ alert('글쓰기실패');}
                })
                .catch( error =>{ alert('시스템오류 : 관리자에게 문의') })
                
} // f end 

 
 	

// [2] 전체 조회 
const boardFindAll = ( ) => {
        
        // 1. 어디에 
        let tbody = document.querySelector('tbody')
        // 2. 무엇을
        let html = '';
        
        // - fetch 옵션 
        const option = { method : 'GET'}
        // - fetch 사용 
        fetch( '/tj2024b_web1/day05/board' , option )
                .then( response => response.json() )
                .then( data => {
        
                        data.forEach( board => {
                                html += `<tr>
                                                <td> ${ board.bno } </td> 
                                                <td> 
                                                        <a href="view.jsp?bno=${ board.bno }"> 
                                                                ${ board.btitle } 
                                                        </a> 
                                                </td> 
                                                <td> ${ board.bdate } </td>
                                                <td> ${ board.bwriter } </td> 
                                                <td> ${ board.bview } </td>
                                        </tr>`
                        });
                        // 3. 출력
                        tbody.innerHTML = html;                
                })
                .catch( error => { console.log(error); })
        
};
boardFindAll(); // - 최초 1번은 실행 


// [3] 게시물 개별 조회
const boardView = ( ) => {
        // 1. 현재 URL의 쿼리스트링 매개변수 가져온다. day25 수업 참고.
                // * 내가 board.jsp 에서 클릭한 게시물 번호가 존재하면 쿼리스트링 
                // http://localhost:8080/tj2024b_web1/day05/view.jsp?bno=3
                // http://localhost:8080/tj2024b_web1/day05/view.jsp?bno=2
                // http://localhost:8080/tj2024b_web1/day05/view.jsp?bno=10
        let bno = new URL( location.href ).searchParams.get('bno');
        
        // 2. fetch 옵션 
        const option = { method : 'GET' }
        // 3. fetch 통신 
        fetch( `/tj2024b_web1/day05/board/view?bno=${ bno }` , option )
                .then( response => response.json() )
                .then( data => {
                        // 4. fetch 응답에 따른 화면 출력 
                        document.querySelector('.bdatebox').innerHTML = `${ data.bdate }`
                        document.querySelector('.bwriterbox').innerHTML = `${ data.bwriter }`
                        document.querySelector('.bviewbox').innerHTML = `${ data.bview }`
                        document.querySelector('.btitlebox').innerHTML = `${ data.btitle }`
                        document.querySelector('.bcontentbox').innerHTML = `${ data.bcontent }`
                        
                })
                .catch( error => { console.log(error) })
} // f end 
boardView() ; // JS가 열릴때 최초 실행
