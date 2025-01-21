package day03.task4;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/day03/waiting2")
public class WaitingController extends HttpServlet{
	// 대기 등록
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. HTTP로부터 요청 받은 HEADER BODY(본문)를 DTO 변환
		ObjectMapper mapper = new ObjectMapper();
		WaitingDto waitingDto = mapper.readValue(req.getReader(), WaitingDto.class);
		
		// 2. Dao 처리
		boolean result = WaitingDao.getInstance().waitRg(waitingDto);
				
		// 3. Dao 결과를 HTTP HEADER BODY(본문)으로 응답 보내기
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
	} // post end
	
	// 대기 조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Dao 처리
		ArrayList<WaitingDto> result = WaitingDao.getInstance().findsom();
		
		// 응답
			// 자바객체 --> 제이슨 타입으로 변환
		ObjectMapper mapper = new ObjectMapper();
		String jsonR = mapper.writeValueAsString(result);
		resp.setContentType("application/json");
		resp.getWriter().print(jsonR);
	}
	
	// 대기 개별 수정
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		WaitingDto waitingDto = mapper.readValue(req.getReader(), WaitingDto.class);
		
		boolean result = WaitingDao.getInstance().update(waitingDto);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
	
	// 대기 개별 삭제
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		
		boolean result = WaitingDao.getInstance().delete(num);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
}
