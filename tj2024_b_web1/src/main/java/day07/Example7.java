package day07;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/day07/example7")
public class Example7 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//[1] 톰캣 안에 있는 객체 호출 및 (첫 http 요청) 생성
		HttpSession httpSession = req.getSession();
		System.out.println(httpSession);
		
		//[2] 세션 객체의 주요 메소드
		System.out.println(httpSession.getAttributeNames());
		System.out.println(httpSession.getCreationTime());
		System.out.println(httpSession.getId());
		System.out.println(httpSession.getLastAccessedTime());
		System.out.println(httpSession.isNew());
		
		//[3] 세션 객체의 속성명과 속성값 저장
		httpSession.setAttribute("세션속성1", "유재석");
		
		//[4] 세션 객체의 지정한 속성명의 값 호출
		Object object = httpSession.getAttribute("세션속성1");
		System.out.println((String)object);
		
		//[5] 세션 객체의 지정한 속성 제거
		httpSession.removeAttribute("세션속성1");
	}
}
