package day03.task4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import day03.task4.WaitingDao;
import day03.task4.WaitingDto;

public class WaitingDao {
	private Connection conn;
	// 싱글톤
	private WaitingDao() {
		try {
			// DB 연도을 위해 mysql-con~~jar 파일을 src -> main -> web-inf -> lib 에 add build 하기
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mydb0120"
					,"root" , "1234"
					);
		}catch (Exception e) {
			System.out.println("DB 연동 실패" + e);
		}
	}
	private static WaitingDao instance = new WaitingDao();
	public static WaitingDao getInstance() {
		return instance;
	}
	// 싱글톤 e
	
	public boolean add(WaitingDto waitingDto) {
		try {
			String sql = "insert into waiting(tel, count) values (?,?) ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, waitingDto.getTel());
			ps.setInt(2, waitingDto.getCount());
		
			int i = ps.executeUpdate();
			
			if(i == 1) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	public ArrayList<WaitingDto> read() {
		ArrayList<WaitingDto> list = new ArrayList<WaitingDto>();
		
		try {
			String sql = " select * from waiting";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				WaitingDto waitingDto = new WaitingDto();
				waitingDto.setNum(rs.getInt("num"));
				waitingDto.setTel(rs.getString("tel"));
				waitingDto.setCount(rs.getInt("count"));
				
				list.add(waitingDto);
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	public boolean update(WaitingDto waitingDto) {
		try {
			String sql = "update waiting set tel = ? , count = ?  where num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, waitingDto.getTel());
			ps.setInt(2, waitingDto.getCount());
			ps.setInt(3, waitingDto.getNum());
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				return true;
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	
	public boolean delete(int num) {
		try {
			String sql = "delete from waiting where num = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			
			int i = ps.executeUpdate();
			if(i == 1) {
				return true;
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}
}