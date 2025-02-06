package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString

public class BoardDto {
    private int bno; //게시물 번호
    private String btitle; // 게시물 제목
    private String bcontent; // 게시물 내용
    private int bview; // 게시물조회수
    private String bdate; // 게시물작성일
    private int mno; // 작성자의 회원번호
    private int cno; // 카테고리의 번호
    // HTML에 출력할 때 작성자의 회원번호가 아닌 작성자 ID 출력
    private String mid;
   // HTML에 출력할 때 카테고리의 번호가 아닌 카테고리명을 출력
    private String cname;
}