DROP TABLE IF EXISTS tour;
CREATE TABLE tour(
    tour_id int PRIMARY KEY AUTO_INCREMENT,
    tour_name varchar(100) NOT NULL,
    tour_addr1 varchar(150),
    tour_addr2 varchar(150),
#     tourInfo varchar(100),
#     tourTel varchar(15),
    tour_image1 varchar(200),
    tour_image2 varchar(200),
#     tourUrl varchar(30),
    tour_contentid int,
    tour_sigungucode varchar(10),
    tour_mapx double,
    tour_mapy double,
    tourItem varchar(500)
);

DROP TABLE IF EXISTS contact_us;
CREATE TABLE contact_us (
    complain_id int PRIMARY KEY AUTO_INCREMENT,
    user_id int NOT NULL,
    content varchar(300) NOT NULL,
    answer varchar(300),
    is_answered boolean NOT NULL DEFAULT FALSE
);

DROP TABLE IF EXISTS user;
CREATE TABLE user (
    user_id int PRIMARY KEY AUTO_INCREMENT,
    email varchar(50) NOT NULL,
    password varchar(200) NOT NULL,
    user_regdate datetime default now() NOT NULL,
    nickname varchar(20) UNIQUE NOT NULL,
    mbti varchar(4)
);