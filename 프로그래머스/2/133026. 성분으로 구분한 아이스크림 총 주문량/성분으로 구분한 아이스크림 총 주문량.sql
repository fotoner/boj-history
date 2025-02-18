-- 코드를 입력하세요
SELECT
    INGREDIENT_TYPE, sum(total_order) as total_order 
from
    (select INGREDIENT_TYPE, TOTAL_ORDER from first_half h, ICECREAM_INFO i where h.flavor = i.flavor)
group by
    INGREDIENT_TYPE;