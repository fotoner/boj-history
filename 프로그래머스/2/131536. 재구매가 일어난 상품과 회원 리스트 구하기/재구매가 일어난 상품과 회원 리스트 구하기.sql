SELECT
    USER_ID, PRODUCT_ID
FROM 
    ONLINE_SALE
GROUP BY user_id, product_id 
HAVING count(PRODUCT_ID) > 1
ORDER BY 
 user_ID asc, product_id DESC;