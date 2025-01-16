package day02.task1;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/day02/waiting")
public class WaitingController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//1. 연락처
		String phone = req.getParameter("phone");
		//2. 인원수 문자열 --> int
		int with = Integer.parseInt(req.getParameter("with"));
		
		boolean result1 = WaitingDao.getInstance().waiting(phone, with);
		System.out.println(result1);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("num"));
		boolean result2 = WaitingDao.getInstance().delete(num);
		System.out.println(result2);
		}
}
