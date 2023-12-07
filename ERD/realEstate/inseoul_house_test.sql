SELECT * FROM house_contract;

SELECT building_name FROM house_contract;


SELECT count(*)
FROM house_contract
WHERE acc_year = '2023' AND ssg_name = '관악구' AND dong_code = 10200 AND house_kind_name = '아파트';

SELECT contract_score, place_score
FROM house_contract
WHERE house_id = 343;