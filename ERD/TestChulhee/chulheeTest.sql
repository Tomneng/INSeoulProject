alter table user
    add column mbti varchar(4);


UPDATE post
SET viewcnt = viewcnt + 1
WHERE post_id = 20;

SELECT t.tour_id     AS "tourId",
       t.tour_name   AS "tourName",
       t.tour_image1 AS "tourImage1",
       t.tour_addr1  AS "tourAddr1",
       t.tour_addr2  AS "tourAddr2"
FROM tour t
         JOIN
     tourinfosaved ts ON t.tour_id = ts.tour_id
GROUP BY t.tour_id
ORDER BY COUNT(ts.tour_id) DESC LIMIT 4;

SELECT t.tour_id         AS "tourId",
       t.tour_name       AS "tourName",
       t.tour_image1     AS "tourImage1",
       t.tour_addr1      AS "tourAddr1",
       t.tour_addr2      AS "tourAddr2",
       COUNT(ts.tour_id) AS "scrapCount"
FROM tour t
         JOIN
     tourinfosaved ts ON t.tour_id = ts.tour_id
GROUP BY t.tour_id, t.tour_name, t.tour_image1, t.tour_addr1, t.tour_addr2
ORDER BY scrapCount DESC LIMIT 4;

SELECT *
FROM tourinfosaved;


SELECT p.post_id      "postId",
       p.title        "title",
       p.content      "content",
       p.viewcnt      "viewcnt",
       p.post_regdate "postRegdate",
       u.user_id      "userId",
       u.username     "username",
       u.nickname     "nickname",
       u.mbti         "mbti"
FROM post p,
     user u
WHERE p.id = u.user_id
  and u.mbti = 'ENTJ';


-- 샘플 글
INSERT INTO post (id, title, content)
VALUES (2, '제목입니다1', '내용입니다1'),
       (2, '제목입니다2', '내용입니다2'),
       (3, '제목입니다3', '내용입니다3'),
       (3, '제목입니다4', '내용입니다4'),
       (3, '제목입니다5', '내용입니다5'),
       (4, '제목입니다6', '내용입니다6'),
       (4, '제목입니다7', '내용입니다7'),
       (4, '제목입니다8', '내용입니다8'),
       (4, '제목입니다9', '내용입니다9')
;
