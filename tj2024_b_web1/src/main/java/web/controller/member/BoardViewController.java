package web.controller.member;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;

@WebServlet("/board/view")
public class BoardViewController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("view test OK");
		// HTTP 요청 : 쿼리스트링(getParameter) 매개변수 가져온다
		int bno = Integer.parseInt(req.getParameter("bno"));
		// dao에게 조회할 게시물 번호를 전달하고 결과 응답 받기
		BoardDto boardDto = BoardDao.getInstance().findBy(bno);
		ObjectMapper mapper = new ObjectMapper();
		String jR = mapper.writeValueAsString(boardDto);
		// 응답받기
		resp.setContentType("application/json");
		resp.getWriter().print(jR);
		
	}
}
