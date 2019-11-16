-- Сколько разных зрителей посетит аудиторию с id = 1. 
-- Например, чтобы знать сколько одноразовых защитных
-- шапочек нужно будет закупить, если эта аудитория связана
-- с химическими опытами :)
SELECT COUNT(*) FROM 
(
SELECT DISTINCT person_id 
FROM ticket t JOIN show s ON t.show_id = s.id
WHERE s.auditory_id = 1
) persons