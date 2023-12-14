SELECT * FROM house_contract;

SELECT building_name FROM house_contract;


SELECT count(*)
FROM house_contract
WHERE acc_year = '2023' AND ssg_name = '관악구' AND dong_code = 10200 AND house_kind_name = '아파트';

SELECT * FROM complain;

INSERT INTO authority (name) VALUES
                                    ('ROLE_MEMBER'), ('ROLE_ADMIN')
;

INSERT INTO user (username, password, name) VALUES
# ('user1@mail.com', '1234', '회원1'),
# ('user2@mail.com', '1234', '회원2'),
# ('admin1@mail.com', '1234', '관리자1')
('user1@mail.com', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', '회원1'),
('user2@mail.com', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa', '회원2'),
('admin1@mail.com', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '관리자1')
;

INSERT INTO user_authorities VALUES
                                    (1, 1),
                                    (3, 1),
                                    (3, 2)
;


SELECT * FROM user;
SELECT * FROM user_authorities;
SELECT * FROM houseinfosaved;

DELETE FROM houseinfosaved;


