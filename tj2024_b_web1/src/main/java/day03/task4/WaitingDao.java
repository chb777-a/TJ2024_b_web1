package day03.task4;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WaitingDao {
	private Connection conn;
	// 싱글톤
	private static WaitingDao instance = new WaitingDao();
	private WaitingDao() {
		try {
			// DB연동 코드 : 
			// 코드 작성전에 필수 : 프로젝트의 mysql-connector-j-9.1.0.jar
			// 라이브러리 폴더 경로 : src -> main -> webapp -> web-inf -> lib 
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mydb0120",
					"root" , "1234");
		}catch( Exception e ) { System.out.println(e); }
	}
	public static WaitingDao getInstance() {return instance;}
	
	// 1. 대기 등록 sql
	public boolean waitRg(WaitingDto waitingDto) {
		try {
			String sql = "insert into waiting(name,people,phone)values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, waitingDto.getName());
			ps.setInt(2, waitingDto.getNum());
			ps.setString(3, waitingDto.getPhone());
			int count = ps.executeUpdate();
			if(count == 1) return true;
		}catch(SQLException e) {System.out.println(e);}
		return false;
	} // Rg end
	
	// 2. 대기 조회 sql
	public ArrayList<WaitingDto> findsom(){
		ArrayList<WaitingDto> list = new ArrayList<WaitingDto>();
		try {
			String sql = "select * from waiting";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				WaitingDto waitingDto = new WaitingDto();
				waitingDto.setName(rs.getString("name"));
				waitingDto.setNum(rs.getInt("people"));
				waitingDto.setPhone(rs.getString("phone"));
				list.add(waitingDto);	
			}
		}catch(SQLException e) {System.out.println(e);}
		return list;
	}
	
	// 3. 개별 수정
	public boolean update(WaitingDto waitingDto) {
		try {
			String sql = "update waiting set name = ? , people = ? , phone = ? where num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, waitingDto.getName());
			ps.setInt(2, waitingDto.getNum());
			ps.setString(3 , waitingDto.getPhone());
			int count = ps.executeUpdate();
			if(count == 1) { return true;}
			
		}catch(SQLException e) {System.out.println(e);}
		return false;
	}
	
	// 4. 개별 삭제
	public boolean delete(int num) {
		try {
			String sql = "delete from visit where num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			int count = ps.executeUpdate();
			if(count == 1) return true;
			
		}catch(SQLException e) {System.out.println(e);}
		return false;
	}
}
