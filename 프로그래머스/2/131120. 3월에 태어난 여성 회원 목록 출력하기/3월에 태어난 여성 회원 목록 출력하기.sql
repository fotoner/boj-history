SELECT
    MEMBER_ID,	MEMBER_NAME,	GENDER,	date_format(DATE_OF_BIRTH, '%Y-%m-%d') as Date_of_Birth 
FROM
    MEMBER_PROFILE
where
    MONTH(date_of_birth) = 3 and 
    gender = 'W' and
    tlno is not null

