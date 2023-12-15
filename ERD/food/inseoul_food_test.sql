SELECT * FROM inseoul_food;

drop table food;


SELECT COUNT(food_id) FROM food;


SELECT food_id, store_name, store_address, store_tel, langcodeId
FROM food

ORDER BY food_id ASC;

SELECT food_id, store_name, store_address, store_tel, langcodeId
FROM food
WHERE langcodeId = 'ko'
ORDER BY food_id ASC;

SELECT * FROM food_reviews;

INSERT into food_reviews
value (3, 122, 14, 'ㅇㅇ', 3.0,'ㅇㅇ');

SELECT * FROM review;


SELECT * FROM user;

# SELECT * FROM review_image;


