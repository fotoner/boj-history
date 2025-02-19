-- 코드를 입력하세요
SELECT
    WAREHOUSE_ID, WAREHOUSE_NAME, address, NVL(FREEZER_YN, 'N') as FREEZER_YN
FROM
    FOOD_WAREHOUSE
WHERE address like '경기도%'
ORDER BY warehouse_id;
