-- 코드를 입력하세요
select 
    i.FLAVOR as FLAVOR
from 
    FIRST_HALF h
    join ICECREAM_INFO i on h.FLAVOR = i.FLAVOR
where
    total_order > 3000 and
    INGREDIENT_TYPE = 'fruit_based'