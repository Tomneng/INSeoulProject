SELECT p.post_id      "postId",
       p.title        "title",
       p.content      "content",
       p.viewcnt      "viewcnt",
       p.post_regdate "postRegdate"
FROM post p
WHERE 1 = 1
ORDER BY post_id DESC
LIMIT 1 , 10;


SELECT count(*)
FROM post;

select *
from post;

select *
from user;

select *
from comment;

-- 샘플 데이터 삽입
INSERT INTO comment(post_id, id, com_content, com_regdate)
VALUES (1, 1, '1. user1이 1번글에 댓글 작성.', NOW()),
       (2, 1, '2. user1이 1번글에 댓글 작성.', NOW()),
       (3, 2, '3. user1이 2번글에 댓글 작성.', NOW()),
       (4, 2, '4. user1이 2번글에 댓글 작성.', NOW()),
       (5, 3, '5. user1이 3번글에 댓글 작성.', NOW()),
       (6, 3, '6. user1이 3번글에 댓글 작성.', NOW()),
       (7, 4, '7. user1이 4번글에 댓글 작성.', NOW()),
       (8, 4, '8. user1이 4번글에 댓글 작성.', NOW()),
       (9, 1, '9. admin1이 1번글에 댓글 작성.', NOW()),
       (10, 1, '10. admin1이 1번글에 댓글 작성.', NOW());

INSERT INTO comment(post_id, id, com_content, com_regdate)
VALUES (30, 1, '댓글', NOW());

INSERT INTO comment(post_id, id, com_content, com_regdate)
VALUES (48, 4, '댓글1', NOW()),
       (48, 4, '댓글2', NOW()),
       (48, 4, '댓글3', NOW()),
       (48, 4, '댓글4', NOW());



SELECT c.com_id      "com_id",
       c.com_content "com_content",
       c.com_regdate "com_regdate",
       c.post_id     "post_id",
       u.user_id     "user_id",
       u.username    "username",
       u.nickname    "nickname",
       u.regdate     "regdate"
FROM comment c,
     user u
WHERE c.id = u.user_id
  AND c.post_id = 48
ORDER BY c.com_id DESC;









