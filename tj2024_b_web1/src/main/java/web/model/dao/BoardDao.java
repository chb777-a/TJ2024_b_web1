package web.model.dao;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import lombok.Getter;
import lombok.NoArgsConstructor;
import web.model.dto.BoardDto;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE) // private 디폴트 생성자가 자동 생성해줌
public class BoardDao extends Dao{
	@Getter //싱글톤을 반환하는 메소드를 자동 생성해줌 [ getInstance() 자동 생성 ]
	private static BoardDao instance = new BoardDao();
	
	// [1] 글쓰기 메소드
	public boolean write(BoardDto boardDto) {
		try {
			// sql 작성
			String sql ="insert into board(btitle , bcontent , bdate , bview) values(? , ? , ? , ?)";
			// DB와 연동된 곳에 sql 기재
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, boardDto.getBtitle());
			ps.setString(2, boardDto.getBcontent());
			ps.setInt(3,  boardDto.getMno());
			ps.setInt(4, boardDto.getCno());
			// 기재된 sql 실행한 다음 결과를 받음
			int count = ps.executeUpdate();
			// 결과에 따른 처리 및 반환
			if(count == 1) return true;
		}catch(SQLException e) {System.out.println(e);}
		return false;
	}
	
	// [2] 전체 게시물 조회 메소드
	public ArrayList<BoardDto> findAll(int cno , int startRow , int display){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		try {
			// 게시물 테이블의 모든 속성을 전체 조회 : select * from board;;
			// 게시물 테이블의 모든 속성과 회원 테이블의 mid 속성도 조회
			// inner join : 다른 테이블과 같이 조회할 때 , 조인 조건 주로 : pk-fk
			
			// select * from 테이블 A inner join 테이블B on 테이블A.PK필드명 = 테이블B.FK필드명;
			// 정렬 : order by 필드명 desc=내림차순 , asc=오름차순
			// String sql = " select * from board b inner join member m on b.mno = m.mno order by b.bno desc";
			
			String sql = " select * from board b inner join member m on b.mno = m.mno where b.cno = ? order by b.bno desc limit ? , ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ps.setInt(2, startRow);
			ps.setInt(3, display);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setBno(rs.getInt("bno"));
				boardDto.setBtitle(rs.getString("btitle"));
				boardDto.setBcontent(rs.getString("bcontent"));
				boardDto.setBdate(rs.getString("bdate"));
				boardDto.setBview(rs.getInt("bview"));
				boardDto.setMno(rs.getInt("mno"));
				boardDto.setCno(rs.getInt("cno"));
				boardDto.setMid(rs.getString("mid"));// 회원테이블과 조인한 결과 회원아이디도 조회 가능하다.
				list.add(boardDto);
			}
		}catch(SQLException e) {System.out.println(e);}
		return list;
	}
	
	// [3] 개별 게시물 조회
	public BoardDto findBy(int bno) {
		try {
			// 특정 게시물 테이블의 게시물 1개 조회
			// String sql = "select * from board where bno = ? ";
			
			// 게시물 테이블과 회원 테이블 교집합을 구해서 회원아이디로 조회 가능
			// String sql = "select * from board b inner join member b on b.mno = m.mno";
			
			// 게시물 테이블과 회원 테이블과 카테고리 테이블을 교집합을 구해서 회원아이디와 카테고리명 조회 가능
			String sql = "select * from board b"
					+ " inner join member b on b.mno = m.mno"
					+ " inner join category c on b.cno = c.cno"
					+ " where bno = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, bno);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				BoardDto boardDto = new BoardDto();
				boardDto.setBno(rs.getInt("bno"));
				boardDto.setBtitle(rs.getString("btitle"));
				boardDto.setBcontent(rs.getString("bcontent"));
				boardDto.setBdate(rs.getString("bdate"));
				boardDto.setBview(rs.getInt("bview"));
				boardDto.setMno(rs.getInt("mno"));
				boardDto.setCno(rs.getInt("cno"));
				boardDto.setMid(rs.getString("mid"));
				boardDto.setCname(rs.getString("cname"));
				return boardDto;
			}
		}catch(Exception e) {System.out.println(e);}
		return null;
	}
	
	// [4] 게시물 수정
	public boolean update(BoardDto boardDto) {
		try {
			String sql = "update board set btitle=? , bcontent=? where bno=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, boardDto.getBtitle());
			ps.setString(2, boardDto.getBcontent());
			ps.setInt(3, boardDto.getBno());
			
			int count = ps.executeUpdate();
			
			if(count ==1) {return true;} // 수정 성공
		}catch(SQLException e) {System.out.println(e);}
		return false; // 수정 실패
	}
	
	// [5] 게시물 삭제
	public boolean delete(int bno) {
		try {
			String sql = "delete from board where bno=?";
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ps.setInt(1, bno);
	         int count = ps.executeUpdate();
	         if(count == 1) {return true;}
	      }catch(SQLException e) {System.out.println(e);}
	      return false;
	}
}
