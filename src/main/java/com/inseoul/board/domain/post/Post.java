package com.inseoul.board.domain.post;


import com.inseoul.user.domain.User;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    private Long postId;
    private String title;  // 제목
    private String email;
    private String content;     // 내용
    private Long viewcnt;  // 조회수 원래 int로 했었음
    private LocalDateTime postRegdate; //시간 자동으로 꼿기

    private User user;  // 글 작성자 (FK)

    // 첨부파일
    @ToString.Exclude
    @Builder.Default
    private List<Attachment> fileList = new ArrayList<>();
}
