SELECT
    TITLE, board.BOARD_ID, REPLY_ID, reply.WRITER_ID, reply.CONTENTS, to_char(reply.CREATED_DATE, 'yyyy-mm-dd') as created_date
FROM
    USED_GOODS_BOARD board
    join USED_GOODS_REPLY reply on board.board_id = reply.board_id
WHERE
    to_char(board.CREATED_DATE, 'yyyy-mm') = '2022-10'
order by 
    reply.CREATED_DATE asc, board.title asc