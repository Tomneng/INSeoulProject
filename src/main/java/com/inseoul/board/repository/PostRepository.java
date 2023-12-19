package com.inseoul.board.repository;

import com.inseoul.board.domain.post.Post;

import java.util.List;

// repository layer (aka. Data layer)
// Datasource 에 직접 접근
// DAO
public interface PostRepository {

    // 새 글 작성 (INSERT) <- Post
    int save(Post post);    // 포스트 객체 받는다

    // 특정 id 글 내용 읽기 (SELECT)
    // 만약 해당 id 의 글 없으면 null 리턴함
    Post findById(Long postId);

    // 특정 id 글 조회수 +1 증가 (UPDATE)
    int incViewCnt(Long postId);

    // 전체 글 목록 : 최신순 (SELECT)
    List<Post> findAll();

    // 특정 id 글 수정 (제목, 내용) (UPDATE)
    int update(Post post);     //

    // 특정 id 글 삭제하기 (DELETE)
    int deleteById(Post post);

//    페이지네이션
    List<Post> selectFromRow(int from, int rows);

    // 전체 글의 개수
    int countAll();

}