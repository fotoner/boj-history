-- 코드를 입력하세요
select mcdp_cd as "진료과코드", count(mcdp_cd) as "5월예약건수" from (SELECT mcdp_cd FROM APPOINTMENT WHERE to_char(apnt_ymd, 'yyyymm') = '202205')
group by mcdp_cd
order by count(mcdp_cd) asc, mcdp_cd asc;