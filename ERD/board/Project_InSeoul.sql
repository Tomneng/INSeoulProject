SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS attachment;
DROP TABLE IF EXISTS user_authority;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS com_like;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS contact_us;
DROP TABLE IF EXISTS foodInfoSaved;
DROP TABLE IF EXISTS food_reviews;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS houseInfoSaved;
DROP TABLE IF EXISTS houseContract;
DROP TABLE IF EXISTS post_like;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS tourInfoSaved;
DROP TABLE IF EXISTS tour;
DROP TABLE IF EXISTS user;




/* Create Tables */

-- 첨부파일테이블
CREATE TABLE attachment
(
	attach_id int NOT NULL AUTO_INCREMENT,
	post_id int NOT NULL,
	source_name varchar(100) NOT NULL,
	file_name varchar(100) NOT NULL,
	PRIMARY KEY (attach_id),
	UNIQUE (attach_id),
	UNIQUE (post_id)
);


-- 권한테이블
CREATE TABLE authority
(
	authority_id int NOT NULL AUTO_INCREMENT,
	authority_name varchar(20) NOT NULL,
	PRIMARY KEY (authority_id),
	UNIQUE (authority_id),
	UNIQUE (authority_name)
) COMMENT = '권한테이블';


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

select * from comment;


-- 댓글좋아요테이블
CREATE TABLE com_like
(
	user_id int NOT NULL,
	com_id int NOT NULL,
	UNIQUE (user_id),
	UNIQUE (com_id)
);


-- 문의사항테이블
CREATE TABLE contact_us
(
	complain_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	content longtext NOT NULL,
	answer longtext,
	is_answered boolean DEFAULT 'FALSE' NOT NULL,
	PRIMARY KEY (complain_id),
	UNIQUE (complain_id),
	UNIQUE (user_id)
);


-- 음식점테이블
CREATE TABLE food
(
	food_id int NOT NULL AUTO_INCREMENT,
	store_name varchar(50) NOT NULL,
	store_address varchar(100),
	store_tel varchar(15),
	food_image varchar(50),
	PRIMARY KEY (food_id),
	UNIQUE (food_id)
);


-- 음식점정보스크랩테이블
CREATE TABLE foodInfoSaved
(
	food_scrapted_id int NOT NULL AUTO_INCREMENT COMMENT '음식정보스크랩아이디',
	food_id int NOT NULL COMMENT '음식점아이디',
	user_id int NOT NULL COMMENT '회원아이디',
	PRIMARY KEY (food_scrapted_id),
	UNIQUE (food_scrapted_id),
	UNIQUE (food_id),
	UNIQUE (user_id)
) COMMENT = '음식점정보스크랩테이블';


-- 음식점리뷰테이블
CREATE TABLE food_reviews
(
	review_id int NOT NULL AUTO_INCREMENT COMMENT '리뷰아이디',
	user_id int NOT NULL COMMENT '회원아이디',
	food_id int NOT NULL COMMENT '음식점아이디',
	review_category varchar(100) COMMENT '리뷰카테고리',
	review_image varchar(50) COMMENT '첨부이미지',
	review_star double NOT NULL COMMENT '평점',
	review_content varchar(100) COMMENT '리뷰글',
	PRIMARY KEY (review_id),
	UNIQUE (review_id),
	UNIQUE (user_id),
	UNIQUE (food_id)
) COMMENT = '음식점리뷰테이블';


-- 부동산계약데이터테이블
CREATE TABLE houseContract
(
	house_id int NOT NULL AUTO_INCREMENT COMMENT '부동산데이터아이디',
	acc_year varchar(4) NOT NULL COMMENT '계약년도',
	ssg_code int NOT NULL COMMENT '자치구코드',
	ssg_nm varchar(10) NOT NULL COMMENT '자치구명',
	dong_code int NOT NULL COMMENT '법정동코드',
	dong_name varchar(10) NOT NULL COMMENT '법정동명',
	land_kind int COMMENT '지번구분',
	land_kind_name varchar(5) COMMENT '지번구분명',
	bobn varchar(4) COMMENT '본번',
	bubn varchar(4) COMMENT '부번',
	floor double COMMENT '층',
	contract_date varchar(10) NOT NULL COMMENT '계약일',
	rent_kind varchar(2) NOT NULL COMMENT '전월세구분',
	contract_score int DEFAULT 0 COMMENT '계약점수지표',
	rent_are double NOT NULL COMMENT '임대면적(m^2)',
	rent_deposit int NOT NULL COMMENT '보증금(만원)',
	rent_fee int NOT NULL COMMENT '임대료(만원)',
	building_name varchar(20) COMMENT '건물명',
	build_year int NOT NULL COMMENT '건축년도',
	house_kind_name varchar(20) COMMENT '건물용도',
	contract_period varchar(20) NOT NULL COMMENT '계약기간',
	new_ron_secd varchar(2) COMMENT '신규갱신여부',
	place_score int DEFAULT 0 COMMENT '매물점수지표',
	building_address varchar(20) COMMENT '건물주소값',
	PRIMARY KEY (house_id),
	UNIQUE (house_id)
) COMMENT = '부동산계약데이터테이블';


-- 부동산정보스크랩테이블
CREATE TABLE houseInfoSaved
(
	house_scrapted_id int NOT NULL AUTO_INCREMENT COMMENT '부동산정보스크랩아이디',
	user_id int NOT NULL COMMENT '회원아이디',
	house_id int NOT NULL COMMENT '부동산데이터아이디',
	PRIMARY KEY (house_scrapted_id),
	UNIQUE (house_scrapted_id),
	UNIQUE (user_id),
	UNIQUE (house_id)
) COMMENT = '부동산정보스크랩테이블';


-- 게시글테이블
CREATE TABLE post
(
	post_id int NOT NULL AUTO_INCREMENT,
	user_id int NOT NULL,
	title varchar(50) NOT NULL,
	content longtext,
	viewcnt int,
	post_regdate datetime NOT NULL,
	PRIMARY KEY (post_id),
	UNIQUE (post_id),
	UNIQUE (user_id)
);


-- 게시글좋아요테이블
CREATE TABLE post_like
(
	user_id int NOT NULL,
	post_id int NOT NULL,
	UNIQUE (user_id),
	UNIQUE (post_id)
);


-- 관광테이블
CREATE TABLE tour
(
	tour_id int NOT NULL AUTO_INCREMENT COMMENT '관광지아이디',
	tour_name varchar(10) NOT NULL COMMENT '관광지명',
	tour_address varchar(50) COMMENT '관광지주소',
	tour_info varchar(100) COMMENT '관광지설명',
	tour_image varchar(50) COMMENT '관광지이미지',
	PRIMARY KEY (tour_id),
	UNIQUE (tour_id)
) COMMENT = '관광테이블';


-- 관광정보스크랩테이블
CREATE TABLE tourInfoSaved
(
	tour_scrapted_id int NOT NULL AUTO_INCREMENT COMMENT '관광정보스크랩아이디',
	tour_id int NOT NULL COMMENT '관광지아이디',
	user_id int NOT NULL COMMENT '회원아이디',
	PRIMARY KEY (tour_scrapted_id),
	UNIQUE (tour_scrapted_id),
	UNIQUE (tour_id),
	UNIQUE (user_id)
) COMMENT = '관광정보스크랩테이블';

# 회원 아디디 아래로 입력값 받기
-- 회원테이블
CREATE TABLE user
(
	user_id int NOT NULL AUTO_INCREMENT,
	email varchar(50) NOT NULL,
	password varchar(200) NOT NULL,
	user_regdate datetime DEFAULT now(),
	nickname varchar(80) UNIQUE,
	mbti varchar(4),

	PRIMARY KEY (user_id),
	UNIQUE (user_id)
);

select * from user;
INSERT INTO user (email, password, nickname, mbti)
VALUES ('1234@mail.com', '1234', 'cat', 'estj');

delete from user;

-- 회원권한테이블
CREATE TABLE user_authority
(
	authority_id int NOT NULL COMMENT '권한아이디',
	user_id int NOT NULL COMMENT '회원아이디',
	PRIMARY KEY (authority_id, user_id),
	UNIQUE (authority_id),
	UNIQUE (user_id)
) COMMENT = '회원권한테이블';



/* Create Foreign Keys */

ALTER TABLE user_authority
	ADD FOREIGN KEY (authority_id)
	REFERENCES authority (authority_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE com_like
	ADD FOREIGN KEY (com_id)
	REFERENCES comment (com_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE foodInfoSaved
	ADD FOREIGN KEY (food_id)
	REFERENCES food (food_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE food_reviews
	ADD FOREIGN KEY (food_id)
	REFERENCES food (food_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE houseInfoSaved
	ADD FOREIGN KEY (house_id)
	REFERENCES houseContract (house_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE attachment
	ADD FOREIGN KEY (post_id)
	REFERENCES post (post_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE comment
	ADD FOREIGN KEY (post_id)
	REFERENCES post (post_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE post_like
	ADD FOREIGN KEY (post_id)
	REFERENCES post (post_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tourInfoSaved
	ADD FOREIGN KEY (tour_id)
	REFERENCES tour (tour_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE comment
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE com_like
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE contact_us
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE foodInfoSaved
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE food_reviews
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE houseInfoSaved
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE post
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE post_like
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tourInfoSaved
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE user_authority
	ADD FOREIGN KEY (user_id)
	REFERENCES user (user_id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;





