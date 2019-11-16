-- презентации, на которые записалось больше всего людей, следим за современными трендами :)
SELECT p.id, p.title,
       (SELECT COUNT(*)
        FROM (show JOIN ticket ON show.id = ticket.show_id) ts
        WHERE ts.presentation_id = p.id)
FROM presentation p
LIMIT 10