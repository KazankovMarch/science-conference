CREATE OR REPLACE FUNCTION select_time_intersection(i_time_start timestamp, i_time_end timestamp, i_auditory_id bigint)
    RETURNS TABLE(id bigint, title varchar(255)) AS
$BODY$
BEGIN
    RETURN QUERY SELECT id, title FROM presentation p
                 WHERE auditory_id = i_auditory_id AND
                     (
                                     p.time_start > i_time_start AND p.time_start < i_time_end
                             OR
                                     i_time_start > p.time_start AND i_time_start < p.time_end
                         );
END;
$BODY$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION check_presentation_time_intersection()
    RETURNS trigger AS
$BODY$
BEGIN
    IF EXISTS(SELECT id, title FROM presentation p
              WHERE auditory_id = NEW.auditory_id AND
                  (p.time_start > NEW.time_start AND p.time_start < NEW.time_end
                      OR
                   NEW.time_start > p.time_start AND NEW.time_start < p.time_end)
        ) THEN
        RAISE EXCEPTION 'intersects with other presentation';
    END IF;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

--триггер не позволяет пересечься двум презентациям по времени в одной аудитории
CREATE TRIGGER check_time_intersection_trigger
    BEFORE INSERT OR UPDATE OF time_end, time_start ON presentation
    FOR EACH ROW EXECUTE PROCEDURE check_presentation_time_intersection();

--следующие два триггера устраняют путаницу: если человек спикер,
-- ему не нужно (и нельзя) дополнительно числиться в записавшихся на прослушивание этой презентации


CREATE OR REPLACE FUNCTION check_if_listener_is_not_speaker()
    RETURNS trigger AS
$BODY$
BEGIN
    IF (SELECT person_id FROM presentation p
        WHERE NEW.presentation_id = p.id) = NEW.person_id
    THEN
        RAISE EXCEPTION 'person is speaker already';
    END IF;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER check_if_listener_is_not_speaker_trigger
    BEFORE INSERT ON ticket
    FOR EACH ROW EXECUTE PROCEDURE check_if_listener_is_not_speaker();



CREATE OR REPLACE FUNCTION check_if_speaker_is_not_listener()
    RETURNS trigger AS
$BODY$
BEGIN
    DELETE FROM ticket t
    WHERE t.person_id=NEW.speaker_id AND t.presentation_id = NEW.id;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER check_if_speaker_is_not_listener_trigger
    AFTER UPDATE OF speaker_id ON presentation
    FOR EACH ROW EXECUTE PROCEDURE check_if_speaker_is_not_listener();