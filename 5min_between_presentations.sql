-- Ищет презентации в одной аудитории, между которыми перерыв меньше 5 мин 
-- (будет давка, аудитория не успеет проветрится)
SELECT p1.id, p1.title, p1.auditory_id, p1.time_end,  p2.time_start, p2.id, p2.title, p2.auditory_id  
FROM presentation p1 JOIN presentation p2 
ON p2.time_start - p1.time_end < '00:05:00'
AND p2.time_start >= p1.time_end
AND p2.auditory_id = p1.auditory_id