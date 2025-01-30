select
    id, fish_name, f.length
from
    (select
        fish_type, max(length) as length
    from
        fish_info
    group by 
        fish_type) max_res,
    fish_info f,
    fish_name_info n
where
    max_res.fish_type = f.fish_type and
    max_res.length = f.length and
    f.fish_type = n.fish_type
order by id;