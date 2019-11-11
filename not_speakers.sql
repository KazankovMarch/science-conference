SELECT * FROM person p 
WHERE (NOT EXISTS (SELECT * FROM  speaker s WHERE s.person_id = p.id))