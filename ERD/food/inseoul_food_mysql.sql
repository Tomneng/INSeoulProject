DROP TABLE IF EXISTS user;

DROP TABLE IF EXISTS food;

CREATE TABLE food(
    food_id INT NOT NULL AUTO_INCREMENT,
    store_name VARCHAR(150) NOT NULL,
    store_address VARCHAR(350),
    store_tel VARCHAR(80),
    langcodeId VARCHAR(10),
    review_avg DECIMAL(2,1) DEFAULT 0.0,
    PRIMARY KEY (food_id)
);

DROP TABLE IF EXISTS review;

CREATE TABLE review(
    review_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    food_id INT NOT NULL,
    user_id INT NOT NULL,
    review_category VARCHAR(100),
    review_star DECIMAL(2,1) NOT NULL,
    review_content VARCHAR(100),
    FOREIGN KEY (`food_id`) REFERENCES `food` (`food_id`),
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

INSERT INTO review (review_id, food_id,user_id, review_category, review_star, review_content)
VALUES (1, 1, 12, '음식이 맛있어요', 3.0, '여기는 순두부집이에요');

INSERT INTO review (review_id, food_id,user_id, review_category, review_star, review_content)
VALUES (2, 19, 12, '음식이 맛있어요', 3.0, '샘플');

INSERT INTO review
    VALUES (3,22,15,'음식이 맛있어요', 4.5, '산골막국수');


DROP TABLE IF EXISTS user;


CREATE TABLE user(
    user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nickName VARCHAR(20),
    email VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    regDate DATETIME DEFAULT now(),
    mbti VARCHAR(4)
);
INSERT INTO user(user_id, nickName, email, password, regDate)
    VALUES (12, '쑤', 'ssoo@gmail.com', '1234', now());

INSERT INTO user
    VALUES (15,'sso','sso@gmail.com','1234', now(), 'istj');

DROP TABLE IF EXISTS review_image;
CREATE TABLE review_image(
    review_img_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    review_id INT NOT NULL,
    review_source_name VARCHAR(100) NOT NULL,
    review_filename VARCHAR(100) NOT NULL,
    FOREIGN KEY (`review_id`) REFERENCES `review` (`review_id`)
);