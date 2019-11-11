CREATE OR REPLACE FUNCTION check_if_speaker_is_not_listener()
    RETURNS trigger AS
$BODY$
BEGIN
    DELETE FROM ticket t
    WHERE t.person_id=NEW.speaker_person_id AND t.presentation_id = NEW.id;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;
DROP TRIGGER check_if_speaker_is_not_listener_trigger ON presentation;
CREATE TRIGGER check_if_speaker_is_not_listener_trigger
    AFTER UPDATE OF speaker_person_id ON presentation
    FOR EACH ROW EXECUTE PROCEDURE check_if_speaker_is_not_listener();


CREATE OR REPLACE FUNCTION check_if_listener_is_not_speaker()
    RETURNS trigger AS
$BODY$
BEGIN
    IF (SELECT speaker_person_id FROM presentation p
        WHERE NEW.presentation_id = p.id) = NEW.person_id
    THEN
        RAISE EXCEPTION 'person is speaker already';
    END IF;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;
DROP TRIGGER check_if_listener_is_not_speaker_trigger ON ticket;
CREATE TRIGGER check_if_listener_is_not_speaker_trigger
    BEFORE INSERT ON ticket
    FOR EACH ROW EXECUTE PROCEDURE check_if_listener_is_not_speaker();
