-- 코드를 입력하세요
SELECT 
    PT_NAME, PT_NO, GEND_CD, AGE, 
    CASE
        when tlno is null then 'NONE'
        else tlno
    END as tlno
from
    PATIENT
where
    age <= 12 and
    gend_cd = 'W'
order by age desc, pt_name;