-- 코드를 입력하세요
SELECT 
    round(avg(daily_fee),0) as AVERAGE_FEE 
FROM 
    CAR_RENTAL_COMPANY_CAR 
WHERE
    car_type = 'SUV';