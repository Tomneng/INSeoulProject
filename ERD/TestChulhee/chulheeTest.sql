alter table user
add column mbti varchar(4);


UPDATE post
SET viewcnt = viewcnt + 1
WHERE post_id = 20;
