package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor // 룸북 준비
public class PageDto { // 페이징 처리된 데이터들의 이동객체
	private int totalCount; // 현재 자료 개수
	private int page ; // 현재 페이지
	private int totalpage ; // 현재 페이지수
	private int startbtn; // 버튼 시작번호
	private int endbtn; // 페이징 버튼 끝번호
	private Object data; // 페이징된 자료
	
}
