select info.REST_ID,	REST_NAME,	FOOD_TYPE,	FAVORITES,	ADDRESS, score
from 
    REST_INFO info,
(SELECT rest_id, round(avg(REVIEW_SCORE),2) as score
from rest_review
group by rest_id) review 
where 
    info.rest_id = review.rest_id and address like '서울%'
order by score desc, favorites desc;