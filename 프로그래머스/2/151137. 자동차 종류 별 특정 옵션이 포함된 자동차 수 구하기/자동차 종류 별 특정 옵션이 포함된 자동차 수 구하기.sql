-- 코드를 입력하세요
SELECT
    car_type, count(car_type) as CARS
from (select car_type from CAR_RENTAL_COMPANY_CAR where options like '%통풍시트%' or options like '%열선시트%' or options like '%가죽시트%')
group by car_type
order by CAR_TYPE;