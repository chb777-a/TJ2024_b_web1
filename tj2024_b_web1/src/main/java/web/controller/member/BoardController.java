package web.controller.member;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import web.model.dao.BoardDao;
import web.model.dao.MemberDao;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

@WebServlet("/board")
public class BoardController extends HttpServlet{
	// 게시물 쓰기
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("board test OK");
		ObjectMapper mapper = new ObjectMapper(); // JSON 문자열 : 특정한 자바 객체 타입으로 변환 기능을 제공하는 라이브러리 객체 생성
		BoardDto boardDto = mapper.readValue(req.getReader(), web.model.dto.BoardDto.class); // HTTP의 request Body 로 부터 JSON 문자열을 읽어와서 BoardDto 타입으로 변환하는 readValue 함수 실행
			// *** 현재 로그인된 회원번호를 세션에서 찾아서 boardDto에 넣어주기
		HttpSession session = req.getSession(); // 세션 객체 가져오기(호출)
		// 세션 객체내 특정한 속성(로그인된 회원번호)의 값 꺼내기 , .getAttribute("속성명"); , 모든 세션 객체내 속성의 타입은 Object이다.
		Object object = session.getAttribute("loginMno"); // 세션객체내 지정한 속성 값 가져오기
			// 만약에 세션객체내 지정한 속성값이 존재하면 로그인회원번호를 타입변환
		if(object != null) {
			int loginMno = (Integer)object; // Object 타입 --> int/Integer 타입으로 강제 타입 변환
			// boardDto 에 로그인된 회원번호 담아주기 , 게시물 작성자 = 로그인된 회원
			boardDto.setMno(loginMno);
		}
		boolean result = BoardDao.getInstance().write(boardDto); // 읽어온 자료를 dao에게 전달받고 결과를 받는다.
		//HTTP로부터 response
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}

	// 게시물 전체조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("board test OK");
		// Dao에게 전체 게시물 요청하고 결과 받기
		ArrayList<BoardDto> result = BoardDao.getInstance().findAll();
		// 받은 전체 게시물을 JSON 형식의 문자열로 변환하기
			ObjectMapper mapper = new ObjectMapper();
			String jsonR = mapper.writeValueAsString(result);
		// http response
			resp.setContentType("application/json");
			resp.getWriter().print(jsonR);
	}
	
	//게시물 수정
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("board test OK");
		// queryString : getParameter 씀
		// body : application/json(getReader) 씀
		ObjectMapper mapper = new ObjectMapper(); // JSON 문자열 : 특정한 자바 객체 타입으로 변환 기능을 제공하는 라이브러리 객체 생성
		// BoardDto 타입으로 변환하는 readValue 함수 실행
		BoardDto boardDto = mapper.readValue(req.getReader(), BoardDto.class);
		boolean result = BoardDao.getInstance().update(boardDto); // Dao에게 전달 후 결과 받기
		// response 응답
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
	
	// 게시물 삭제
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("board test OK");
		// HTTP queryString 매개변수를 가져오기 , URL?=bno-1 , req.getParameter("매개변수명") : 변환타입 문자열이므로 타입변환 필요할 수 있음
			// Integer.parseInt("문자열") : 문자열 타입 --> 정수 타입으로 변환 함수
		int bno = Integer.parseInt(req.getParameter("bno"));
		// Dao에게 삭제할 번호를 전달하고 결과 받기
		boolean result = BoardDao.getInstance().delete(bno);
		// HTTp response 응답
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
}


//1.[HTTP 요청의 header body 자료(JSON)를 자바(DTO)로 받는다.]
//2.[ 데이터 유효성검사 ]
//3.[ DAO 에게 데이터 전달 하고 응답 받기 ]
//4.[ 자료(DTO/자바)타입을 JS(JSON)타입으로 변환한다.]
//5.[ HTTP 응답의 header body 로 application/json 으로 응답/반환하기]