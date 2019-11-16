-- Сколько разных зрителей посетит аудиторию с id = 1. 
-- Например, чтобы знать сколько одноразовых защитных
-- шапочек нужно будет закупить, если эта аудитория связана
-- с химическими опытами :)
SELECT COUNT(*) FROM 
(
SELECT DISTINCT person_id 
FROM ticket t JOIN presentation p ON t.presentation_id = p.id
WHERE p.auditory_id = 1
) persons