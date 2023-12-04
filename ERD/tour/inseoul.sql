DROP TABLE IF EXISTS tour

CREATE TABLE tour(
    tour_id int PRIMARY KEY AUTO_INCREMENT,
    tour_name varchar(20) NOT NULL,
    tour_address varchar(50),
    tour_image varchar(50)
);

SELECT * FROM tour;