-- 코드를 입력하세요
select to_char(SALES_DATE, 'yyyy-mm-dd') sales_date, product_id, user_id, sales_amount from ONLINE_SALE where to_char(SALES_DATE, 'yyyy-mm') = '2022-03'
    union
select  to_char(SALES_DATE, 'yyyy-mm-dd') sales_date, product_id, null as user_id, sales_amount from offline_SALE where to_char(SALES_DATE, 'yyyy-mm') = '2022-03'
order by
    sales_date,
    product_id,
    user_id;
