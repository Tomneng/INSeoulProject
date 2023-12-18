SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS attachment;
DROP TABLE IF EXISTS user_authority;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS com_like;
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS contact_us;
DROP TABLE IF EXISTS foodInfoSaved;
DROP TABLE IF EXISTS reviewImage;
DROP TABLE IF EXISTS food_reviews;
DROP TABLE IF EXISTS food;
DROP TABLE IF EXISTS houseContractScore;
DROP TABLE IF EXISTS houseInfoSaved;
DROP TABLE IF EXISTS house_Contract;
DROP TABLE IF EXISTS post_like;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS tourInfoSaved;
DROP TABLE IF EXISTS tour;
DROP TABLE IF EXISTS user;

/* Create Tables */

-- 첨부파일테이블
CREATE TABLE attachment
(
    review_img_id int NOT NULL AUTO_INCREMENT COMMENT '리뷰이미지아이디',
    post_id int NOT NULL COMMENT '게시글아이디',
    review_source_name varchar(100) NOT NULL COMMENT '리뷰첨부소스이름',
    review_file_name varchar(100) NOT NULL COMMENT '리뷰첨부파일이름',
    PRIMARY KEY (review_img_id),
    UNIQUE (review_img_id),
    UNIQUE (post_id)
) COMMENT = '첨부파일테이블';


-- 권한테이블
CREATE TABLE authority
(
    authority_id int PRIMARY KEY AUTO_INCREMENT, #column명 다름 비버에 있는걸로 할듯
    authority_name varchar(80) NOT NULL UNIQUE #column명 다름 비버에 있는걸로 할듯 + 길이제한 다름
) COMMENT = '권한테이블';


-- 댓글테이블
CREATE TABLE comment
(
    com_id int NOT NULL AUTO_INCREMENT COMMENT '댓글아이디',
    post_id int NOT NULL COMMENT '게시글아이디',
    id int NOT NULL COMMENT '회원아이디',
    com_content text NOT NULL COMMENT '댓글내용',
    com_regdate datetime NOT NULL COMMENT '댓글작성일자',
    PRIMARY KEY (com_id),
    UNIQUE (com_id),
    UNIQUE (post_id),
    UNIQUE (id)
) COMMENT = '댓글테이블';


-- 댓글좋아요테이블
CREATE TABLE com_like
(
    id int NOT NULL COMMENT '회원아이디',
    com_id int NOT NULL COMMENT '댓글아이디',
    UNIQUE (id),
    UNIQUE (com_id)
) COMMENT = '댓글좋아요테이블';


-- 문의사항테이블
CREATE TABLE contact_us
(
    complain_id int PRIMARY KEY AUTO_INCREMENT,
    user_id int REFERENCES User(user_id), #비버에 외래키 참조 설정안되있음(되어 있어야 함, unique는 필요없을듯)
    content longtext NOT NULL,
    answer longtext,
    is_answered boolean NOT NULL DEFAULT false # 비버에 디폴트 설정 잘못됨 (고쳐야함)
) COMMENT = '문의사항테이블';


-- 음식점테이블
-- 대표이미지 제거, varchar값 변경, 평점&언어코드 추가
CREATE TABLE food
(
    food_id int NOT NULL AUTO_INCREMENT COMMENT '음식점아이디',
    store_name varchar(150) NOT NULL COMMENT '장소명',
    store_address varchar(350) COMMENT '음식점 주소',
    store_tel varchar(80) COMMENT '전화번호',
    langcodeId varchar(10),
    review_avg double DEFAULT 0.0,
    PRIMARY KEY (food_id),
    UNIQUE (food_id)
) COMMENT = '음식점테이블';


-- 음식점정보스크랩테이블
CREATE TABLE foodInfoSaved
(
    food_scrapted_id int NOT NULL AUTO_INCREMENT COMMENT '음식정보스크랩아이디',
    food_id int NOT NULL COMMENT '음식점아이디',
    id int NOT NULL COMMENT '회원아이디',
    PRIMARY KEY (food_scrapted_id),
    UNIQUE (food_scrapted_id),
    UNIQUE (food_id),
    UNIQUE (id)
) COMMENT = '음식점정보스크랩테이블';


-- 음식점리뷰테이블
-- 첨부이미지 제거
CREATE TABLE food_reviews
(
    review_id int NOT NULL AUTO_INCREMENT COMMENT '리뷰아이디',
    id int NOT NULL COMMENT '회원아이디',
    food_id int NOT NULL COMMENT '음식점아이디',
    review_category varchar(100) COMMENT '리뷰카테고리',
    review_star double NOT NULL COMMENT '평점',
    review_content varchar(100) COMMENT '리뷰글',
    PRIMARY KEY (review_id),
    UNIQUE (review_id)
) COMMENT = '음식점리뷰테이블';


-- 부동산계약데이터테이블
CREATE TABLE house_Contract #테이블명 다름, 이걸로 갈듯
(
    house_id int PRIMARY KEY AUTO_INCREMENT,
    acc_year VARCHAR(4) NOT NULL,
    ssg_code int NOT NULL,
    ssg_name VARCHAR(10) NOT NULL, #colum 명 다름 name으로 할것임
    dong_code int NOT NULL,
    dong_name VARCHAR(10) NOT NULL,
    land_kind int,
    land_kind_name varchar(5),
    bobn varchar(4),
    bubn varchar(4),
    floor int, #타입이 다름
    contract_date varchar(10) NOT NULL,
    rent_kind varchar(2) NOT NULL,
    rent_area DOUBLE NOT NULL, #area가 맞음 are는 오타
    rent_deposit int NOT NULL,
    rent_fee int NOT NULL,
    building_name varchar(20),
    build_year int,
    house_kind_name varchar(20),
    contract_period varchar(20), #이거 null 허용으로 바꿈
    new_ron_secd varchar(2),
    address varchar(100) # column명이 다름 이걸로 갈듯
) COMMENT = '부동산계약데이터테이블';


-- 부동산 점수테이블
CREATE TABLE houseContractScore
(
    user_id int REFERENCES user(user_id), #column명 다름 이걸로 갈듯
    house_id int REFERENCES house_contract(house_id), #외래키설정 비버에 안되있음
    contract_score int, #디폴트 value는 없애는 방향
    place_score int,
    PRIMARY KEY (user_id, house_id) #비버에 pk설정 안되있음
) COMMENT = '부동산 점수테이블';


-- 부동산정보스크랩테이블
CREATE TABLE houseInfoSaved
(
    house_scrapted_id int PRIMARY KEY AUTO_INCREMENT,
    user_id int REFERENCES user(user_id), #이거도 다름
    house_id int REFERENCES house_contract(house_id) #이거 굳이 NOTNULL필요없을거 같긴함
) COMMENT = '부동산정보스크랩테이블';


-- 게시글테이블
CREATE TABLE post
(
    post_id int NOT NULL AUTO_INCREMENT COMMENT '게시글아이디',
    id int NOT NULL COMMENT '회원아이디',
    title varchar(50) NOT NULL COMMENT '게시글제목',
    content longtext COMMENT '게시글내용',
    viewcnt int COMMENT '조회수',
    post_regdate datetime default now(),   #NOT NULL에서 default now()로 변경
    PRIMARY KEY (post_id),
    UNIQUE (post_id),
    UNIQUE (id)
) COMMENT = '게시글테이블';


-- 게시글좋아요테이블
CREATE TABLE post_like
(
    id int NOT NULL COMMENT '회원아이디',
    post_id int NOT NULL COMMENT '게시글아이디',
    UNIQUE (id),
    UNIQUE (post_id)
) COMMENT = '게시글좋아요테이블';


-- 음식점리뷰첨부이미지
CREATE TABLE reviewImage
(
    review_img_id int NOT NULL AUTO_INCREMENT COMMENT '리뷰이미지아이디',
    review_id int NOT NULL COMMENT '리뷰아이디',
    review_source_name varchar(100) NOT NULL COMMENT '리뷰첨부소스이름',
    review_file_name varchar(100) NOT NULL COMMENT '리뷰첨부파일이름',
    PRIMARY KEY (review_img_id),
    UNIQUE (review_img_id),
    UNIQUE (review_id)
) COMMENT = '음식점리뷰첨부이미지';


-- 관광테이블 수정 2023.12.15
CREATE TABLE tour
(
    tour_id int PRIMARY KEY AUTO_INCREMENT,
    tour_name varchar(100) NOT NULL,
    tour_addr1 varchar(150),
    tour_addr2 varchar(150),
    tour_image1 varchar(200),
    tour_image2 varchar(200),
    tour_contentid int,
    tour_sigungucode varchar(10),
    tour_mapx double,
    tour_mapy double
) COMMENT = '관광테이블';


-- 관광정보스크랩테이블
CREATE TABLE tourInfoSaved
(
    tour_scrapted_id int NOT NULL AUTO_INCREMENT COMMENT '관광정보스크랩아이디',
    tour_id int NOT NULL COMMENT '관광지아이디',
    id int NOT NULL COMMENT '회원아이디',
    PRIMARY KEY (tour_scrapted_id),
    UNIQUE (tour_scrapted_id),
    UNIQUE (tour_id),
    UNIQUE (id)
) COMMENT = '관광정보스크랩테이블';


-- 회원테이블
CREATE TABLE user
(
    user_id int PRIMARY KEY AUTO_INCREMENT, #이건 둘다 똑같은데 user_id로 갈듯
    username varchar(100) NOT NULL UNIQUE , #column명 다름 email임이게
    password varchar(200) NOT NULL, #길이제한 다름 (수정함)
    nickname varchar(80) NOT NULL, #column명 다름, nickname으로 갈듯(수정함)
    regdate datetime DEFAULT now(), #column명 다름, 비버에 디폴트값없음(수정함)
    providerId varchar(200), #비버에 이거랑 provider 둘다 추가되야됨
    provider varchar(40)
) COMMENT = '회원테이블';


-- 회원권한테이블
CREATE TABLE user_authority
(
    user_id int NOT NULL, #column명 다름 이걸로 갈듯
    authority_id int NOT NULL,
    PRIMARY KEY (user_id, authority_id) #Unique 설정 필요없긴함
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


ALTER TABLE reviewImage
    ADD FOREIGN KEY (review_id)
        REFERENCES food_reviews (review_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE houseContractScore
    ADD FOREIGN KEY (house_id)
        REFERENCES house_Contract (house_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE houseInfoSaved
    ADD FOREIGN KEY (house_id)
        REFERENCES house_Contract (house_id)
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
    ADD FOREIGN KEY (id)
        REFERENCES user (user_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE com_like
    ADD FOREIGN KEY (id)
        REFERENCES user (user_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE foodInfoSaved
    ADD FOREIGN KEY (id)
        REFERENCES user (user_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE food_reviews
    ADD FOREIGN KEY (id)
        REFERENCES user (user_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE houseContractScore
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
    ADD FOREIGN KEY (id)
        REFERENCES user (user_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE post_like
    ADD FOREIGN KEY (id)
        REFERENCES user (user_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE tourInfoSaved
    ADD FOREIGN KEY (id)
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

