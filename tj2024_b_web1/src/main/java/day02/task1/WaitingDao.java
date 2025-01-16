package day02.task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WaitingDao {
	private Connection conn;
	// 싱글톤 생성
	private static WaitingDao instance = new WaitingDao();
	private WaitingDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/waiting",
					"root" , "1234");
		}catch(Exception e) {System.out.println(e);}
	}
	public static WaitingDao getInstance() {return instance;}
	
	// 대기 등록
	public boolean waiting(String phone , int with) {
		try {
			String sql = "insert into wait(phone,with1)values(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, phone);
			ps.setInt(2, with);
			int count = ps.executeUpdate();
			if(count == 1) return true;
		}catch(SQLException e) {System.out.println(e);}
		return false;
		
	}
	
	// 대기 삭제
	public boolean delete(int num) {
		try {
			String sql = "delete from wait where num =?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			int count = ps.executeUpdate();
			if(count ==1) return true;
		}catch(SQLException e) {System.out.println(e);}
		return false;
		
	}
	
}
