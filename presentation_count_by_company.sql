-- сколько презентаций от каждой компании будет на конференции
-- чтобы,  например, поощрить их подарками
SELECT COUNT(p.id), 
(SELECT c.name FROM company c WHERE c.id = (SELECT company_id FROM speaker s
WHERE s.person_id = p.speaker_person_id)) as company
FROM presentation p 
GROUP BY company