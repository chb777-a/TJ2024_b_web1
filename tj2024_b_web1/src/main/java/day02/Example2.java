package day02;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 1 단계 : extends HttpServlet
// 2 단계 : @WebServlet (주소정의)
// 3 단계 : http method(get , post , put , delete 메소드 재정의

@WebServlet("/day02/example2")
public class Example2 extends HttpServlet{
	// 1. doGet 자동완성
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("사용자가 서버로부터 get 메소드로 요청이 왔습니다.");
		
		// 쿼리스트링 매개변수를 가져오기
			// http://localhhost"8080/tj2024_b_web1/day01/example2?name=유재석
			//.getParameter("URL경로상의 매개변수명");   // 매개변수명의 값 (String타입) 변환 함수 , 없으면 NULL ,
		System.out.println(" 1. URL ? 뒤에 'name'이라는 매개변수명을 가진 변수의 값 가져오기 :");
		System.out.println(req.getParameter("name"));
		
			// http://localhhost"8080/tj2024_b_web1/day01/example2?name = 유재석 & age = 40
		System.out.println(" URL ? 뒤에 'age'이라는 매개변수명을 가진 변수의 값 가져오기 :");
		System.out.println(req.getParameter("age"));
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("사용자가 서버로부터 post 메소드로 요청이 왔습니다.");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		System.out.printf("name : %s , age : %s" , name , age);
		
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("사용자가 서버로부터 put 메소드로 요청이 왔습니다.");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		System.out.printf("name : %s , age : %s" , name , age);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("사용자가 서버로부터 delete 메소드로 요청이 왔습니다.");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		System.out.printf("name : %s , age : %s" , name , age);
	}
}
