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
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> POST METHOD");
		
		ObjectMapper mapper = new ObjectMapper();
		WaitingDto waitingDto = mapper.readValue(req.getReader(), WaitingDto.class);
		
		boolean result = WaitingDao.getInstance().add(waitingDto);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> PUT METHOD");
		
		ArrayList<WaitingDto> result = WaitingDao.getInstance().read();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonResult = mapper.writeValueAsString(result);
		
		resp.setContentType("application/json");
		resp.getWriter().print(jsonResult);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> PUT METHOD");
		
		ObjectMapper mapper = new ObjectMapper();
		WaitingDto waitingDto = mapper.readValue(req.getReader(), WaitingDto.class);
		
		boolean result = WaitingDao.getInstance().update(waitingDto);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(">> DELETE METHOD");
		
		int num = Integer.parseInt(req.getParameter("num"));
		boolean result = WaitingDao.getInstance().delete(num);
		
		resp.setContentType("application/json");
		resp.getWriter().print(result);
		
	}
}