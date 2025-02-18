-- 코드를 입력하세요
SELECT distinct
    car_id,
    case
        when car_id in (
            select car_id from CAR_RENTAL_COMPANY_RENTAL_HISTORY
            where to_date('2022-10-16', 'yyyy-mm-dd') between start_date and end_date
        ) then '대여중'
        else '대여 가능'
    end as AVAILABILITY
FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY
order by car_id desc;
