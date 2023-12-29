# 리뷰
DROP TABLE food;
DROP TABLE food_reviews;

DROP TABLE foodinfosaved;

# DROP TABLE reviewimage;

DELETE FROM food WHERE review_avg;

update food set review_avg = 0.0 where food_id = 24;

SELECT * FROM food_reviews;
# 음식점
SELECT * FROM food;
SELECT * FROM food WHERE food_id = 5653;

SELECT DISTINCT store_name, store_address, store_tel, langcodeId, review_avg FROM food WHERE langcodeId='ko';

SELECT * FROM food WHERE langcodeId = 'ko';

SELECT store_tel FROM food WHERE langcodeId = 'ko';

# SELECT store_tel FROM food WHERE langcodeId = 'ko' ORDER BY  ASC LIMIT3;

SELECT * FROM user;

SELECT * FROM tour;
SELECT * FROM houseinfosaved NATURAL JOIN user WHERE house_id=351;
# SELECT * FROM reviewimage;


DROP TABLE house_Contract;

DROP TABLE housecontractscore;

drop table houseinfosaved;

