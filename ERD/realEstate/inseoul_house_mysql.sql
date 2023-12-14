
DROP TABLE IF EXISTS housecontract;

DROP TABLE IF EXISTS house_Contract;

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS user_authorities;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS complain;
DROP TABLE IF EXISTS authority;
DROP TABLE IF EXISTS houseInfoSaved;

CREATE TABLE house_Contract
(
    house_id int PRIMARY KEY AUTO_INCREMENT,
    acc_year VARCHAR(4) NOT NULL,
    ssg_code int NOT NULL,
    ssg_name VARCHAR(10) NOT NULL,
    dong_code int NOT NULL,
    dong_name VARCHAR(10) NOT NULL,
    land_kind int,
    land_kind_name varchar(5),
    bobn varchar(4),
    bubn varchar(4),
    floor int,
    contract_date varchar(10) NOT NULL,
    rent_kind varchar(2) NOT NULL,
    rent_area DOUBLE NOT NULL,
    rent_deposit int NOT NULL,
    rent_fee int NOT NULL,
    building_name varchar(20),
    build_year int,
    house_kind_name varchar(20),
    contract_period varchar(20),
    new_ron_secd varchar(2),
    distinguish varchar(150),
    address varchar(100)
);


CREATE TABLE complain
(
    complain_id int PRIMARY KEY AUTO_INCREMENT,
    user_id int REFERENCES User(id),
    content longtext NOT NULL,
    answer longtext,
    is_answered boolean NOT NULL DEFAULT false
);

CREATE TABLE authority
(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(80) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);

CREATE TABLE user
(
    id int NOT NULL AUTO_INCREMENT,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    name varchar(80) NOT NULL,
    regdate datetime DEFAULT now(),
    providerId varchar(200),
    provider varchar(40),
    PRIMARY KEY (id),
    UNIQUE (username)
);

CREATE TABLE user_authorities
(
    user_id int NOT NULL,
    authority_id int NOT NULL,
    PRIMARY KEY (user_id, authority_id)
);

ALTER TABLE user_authorities
    ADD FOREIGN KEY (authority_id)
        REFERENCES authority (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;

ALTER TABLE user_authorities
    ADD FOREIGN KEY (user_id)
        REFERENCES user (id)
        ON UPDATE RESTRICT
        ON DELETE CASCADE
;

CREATE TABLE house_Contract_Score
(
    user_id int REFERENCES user(id),
    house_id int REFERENCES house_contract(house_id),
    contract_score int,
    place_score int,
    PRIMARY KEY (user_id, house_id)
);

CREATE TABLE houseInfoSaved
(
    house_scrapted_id int PRIMARY KEY AUTO_INCREMENT,
    user_id int REFERENCES user(id),
    house_id int REFERENCES house_contract(house_id)
);
