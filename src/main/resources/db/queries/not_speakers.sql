-- участники конференции, не выступающие ни с одной презентацией
-- например, чтобы закупить для них футболки с надписью "Участник конференции N, <фио>"
SELECT * FROM person p
WHERE (NOT EXISTS (SELECT * FROM  show s WHERE s.speaker_id = p.id))