-- 회원테이블
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    user_id int NOT NULL AUTO_INCREMENT,
    email varchar(50) NOT NULL,
    password varchar(200) NOT NULL,
    nickname varchar(80) UNIQUE NOT NULL,
    mbti varchar(4),
    user_regdate datetime DEFAULT now(),

    PRIMARY KEY (user_id),
    UNIQUE (user_id)
);

-- 게시글테이블
CREATE TABLE post
(
    post_id int NOT NULL AUTO_INCREMENT,
#     user_id int NOT NULL,
    user_id int,
    title varchar(50) NOT NULL,
    content longtext,
    viewcnt int,
    post_regdate datetime default now(),

    PRIMARY KEY (post_id),
    UNIQUE (post_id)
#     UNIQUE (user_id)
);

-- 댓글테이블
CREATE TABLE comment
(
    com_id int NOT NULL AUTO_INCREMENT,
    post_id int NOT NULL,
    user_id int NOT NULL,
    com_content text NOT NULL,
    com_regdate datetime NOT NULL,

    PRIMARY KEY (com_id),
    UNIQUE (com_id),
    UNIQUE (post_id),
    UNIQUE (user_id)
);

-- 댓글좋아요테이블
CREATE TABLE com_like
(
    user_id int NOT NULL,
    com_id int NOT NULL,

    UNIQUE (user_id),
    UNIQUE (com_id)
);

-- 게시글좋아요테이블
CREATE TABLE post_like
(
    user_id int NOT NULL,
    post_id int NOT NULL,

    UNIQUE (user_id),
    UNIQUE (post_id)
);

SELECT
    user_id "userId"
     , email "email"
     , password "password"
     , user_regdate "userRegdate"
     , nickname "nickname"
     , mbti "mbti"
FROM user
WHERE  3 = 3

