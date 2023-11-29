DROP TABLE IF EXISTS house_Contract;

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
    build_year int NOT NULL,
    house_kind_name varchar(20),
    contract_period varchar(20),
    new_ron_secd varchar(2),
    contract_score int,
    place_score int
)