-- 코드를 입력하세요
SELECT
    BOOK_ID, to_char(PUBLISHED_DATE, 'yyyy-mm-dd') as PUBLISHED_DATE
from BOOK 
where category = '인문' and
    to_char(PUBLISHED_DATE, 'yyyy') = '2021'
order by BOOK_ID desc;