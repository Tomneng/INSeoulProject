
SELECT
    p.post_id "postId",
    p.title "title",
    p.content "content",
    p.viewcnt "viewcnt",
    p.post_regdate "postRegdate"
FROM
    post p
WHERE
    1 = 1
 ORDER BY post_id DESC
    LIMIT 1 , 10;


SELECT count(*) FROM post;

select * from comment;

-- 샘플 데이터 삽입
INSERT INTO comment(post_id, id, com_content, com_regdate) VALUES
                                                      (1, 1, '1. user1이 1번글에 댓글 작성.', NOW()),
                                                      (2, 1, '2. user1이 1번글에 댓글 작성.', NOW()),
                                                      (3, 2, '3. user1이 2번글에 댓글 작성.', NOW()),
                                                      (4, 2, '4. user1이 2번글에 댓글 작성.', NOW()),
                                                      (5, 3, '5. user1이 3번글에 댓글 작성.', NOW()),
                                                      (6, 3, '6. user1이 3번글에 댓글 작성.', NOW()),
                                                      (7, 4, '7. user1이 4번글에 댓글 작성.', NOW()),
                                                      (8, 4, '8. user1이 4번글에 댓글 작성.', NOW()),
                                                      (9, 1, '9. admin1이 1번글에 댓글 작성.', NOW()),
                                                      (10, 1, '10. admin1이 1번글에 댓글 작성.', NOW());
;