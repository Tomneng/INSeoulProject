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

SELECT * FROM review;

SELECT * FROM user;

# SELECT * FROM review_image;


