-- Поиск спикеров, выступающих больше всего, чтобы наградить их
SELECT p.id, p.fio, p.email,
    (SELECT COUNT(s.id) FROM show s WHERE p.id = s.speaker_id) as count
FROM person p
ORDER BY count DESC
LIMIT 3