INSERT INTO user (email, password, nickname, mbti)
VALUES
    ('user3@example.com', 'password3', 'nick3', 'ISFP');

INSERT INTO post (user_id, title, content)
VALUES
    (4, 'Post Title 4', 'This is the content of the second post.');

select * from user;
select * FROM post;