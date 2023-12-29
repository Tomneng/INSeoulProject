alter table user
    add column mbti varchar(4);


UPDATE post
SET viewcnt = viewcnt + 1
WHERE post_id = 20;

SELECT
    t.tour_id AS "tourId",
    t.tour_name AS "tourName",
    t.tour_image1 AS "tourImage1",
    t.tour_addr1 AS "tourAddr1",
    t.tour_addr2 AS "tourAddr2"
FROM
    tour t
        JOIN
    tourinfosaved ts ON t.tour_id = ts.tour_id
GROUP BY
    t.tour_id
ORDER BY
    COUNT(ts.tour_id) DESC
    LIMIT 4;

SELECT
    t.tour_id AS "tourId",
    t.tour_name AS "tourName",
    t.tour_image1 AS "tourImage1",
    t.tour_addr1 AS "tourAddr1",
    t.tour_addr2 AS "tourAddr2",
    COUNT(ts.tour_id) AS "scrapCount"
FROM
    tour t
        JOIN
    tourinfosaved ts ON t.tour_id = ts.tour_id
GROUP BY
    t.tour_id, t.tour_name, t.tour_image1, t.tour_addr1, t.tour_addr2
ORDER BY
    scrapCount DESC
LIMIT 4;

SELECT * FROM tourinfosaved;


