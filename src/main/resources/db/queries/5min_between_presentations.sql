-- Ищет показы презентаций в одной аудитории, между которыми перерыв меньше 5 мин 
-- (будет давка, аудитория не успеет проветриться)
SELECT s1.id, s1.presentation_id, s1.auditory_id, s1.time_end,  s2.time_start, s2.id, s2.presentation_id, s2.auditory_id
FROM show s1 JOIN show s2 
ON s2.time_start - s1.time_end < '00:05:00'
AND s2.time_start >= s1.time_end
AND s2.auditory_id = s1.auditory_id