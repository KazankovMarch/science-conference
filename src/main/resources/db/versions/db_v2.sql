
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    fio varchar(255) NOT NULL,
    email varchar(255) UNIQUE
);

-- Для исправление нарушения третей нормальной формы
-- выношу audience_max_count и адрес аудитории в отдельную табличку
-- добавляю суррагатный ПК чтобы можно было легко менять адрес
-- (например, при переименовании улицы / изменении нумерации аудиторий / исправлении неточностей)
CREATE TABLE auditory (
    id SERIAL PRIMARY KEY,
    address varchar(255) NOT NULL UNIQUE,
    audience_max_count int NOT NULL DEFAULT 0
);

-- Для исправление нарушения второй нормальной формы
-- выношу презентацию и ее тему в отдельную табличку
CREATE TABLE presentation (
    id SERIAL PRIMARY KEY,
    title varchar(255) NOT NULL UNIQUE,
    subject varchar(255)
);
-- В старой табличке меняю первичный ключ на суррогатный,
-- чтобы было можно менять время начала показа презентации, не создавая новую запись
CREATE TABLE show(
    id SERIAL PRIMARY KEY,
    presentation_id bigint REFERENCES presentation(id),
    speaker_id bigint REFERENCES person(id),
    time_start timestamp,
    time_end timestamp,
    auditory_id bigint REFERENCES auditory(id)
);

-- Для исправления нарушения первой нормальной формы
-- я создаю табличку, соединящую показы и людей, которые на на них записаны
-- можно было бы просто продублировать строки в старой табличке с разными айди зрителя person_id
-- но это бы породило дублирования данных и нарушения других нормальных форм
CREATE TABLE ticket(
    id SERIAL PRIMARY KEY,
    person_id bigint REFERENCES person(id),
    show_id bigint REFERENCES show(id)
);


INSERT INTO person(fio, email) VALUES
('Казанков Андрей Дмитриевич', 'akazankov@fix.ru'),
('Попов Вячеслав Никитович', 'popov234@mail.ru'),
('Гришкевич Олег Юрьевич', 'grisha_master@list.ru'),
('Кагирова Камилла Сергеевна', 'KKSerg@gmail.com'),
('Дубровский Михаил Олегович', 'Dubrovski_mixa@mail.ru'),
('Шарипова Галиябану Газизовна', 'shabanu@gmail.ru');

INSERT INTO auditory(address, audience_max_count) VALUES
('корпус 2, ауд. 219', 70),
('корпус 2, ауд. 810', 30);

INSERT INTO presentation(title, subject) VALUES
('Domain Driven Development', 'Основы DDD, плюсы и минусы DDD, внедрение DDD в уже существующий проект'),
('Photo-realistic drawing', 'Современные способы создания фотореалистичных рисунков');

INSERT INTO show(presentation_id, speaker_id, time_start, time_end, auditory_id) VALUES
(1, 1, make_timestamp(2019, 1, 1, 8, 0, 0), make_timestamp(2019, 1, 1, 11, 00, 0), 2),
(2, 4, make_timestamp(2020, 1, 1, 11, 30, 0), make_timestamp(2020, 1, 1, 13, 00, 0), 1),
(1, 1, make_timestamp(2020, 1, 1, 8, 0, 0), make_timestamp(2020, 1, 1, 11, 00, 0), 1);

INSERT INTO ticket(person_id, show_id) VALUES
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(1, 2),
(5, 2),
(6, 3);

CREATE OR REPLACE FUNCTION show_time_intersection(i_time_start timestamp, i_time_end timestamp, i_auditory_id bigint)
    RETURNS TABLE(id bigint, title varchar(255)) AS
$BODY$
BEGIN
    RETURN QUERY SELECT id FROM show p
                 WHERE auditory_id = i_auditory_id AND
                     (
                                     p.time_start > i_time_start AND p.time_start < i_time_end
                             OR
                                     i_time_start > p.time_start AND i_time_start < p.time_end
                         );
END;
$BODY$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION check_show_time_intersection()
    RETURNS trigger AS
$BODY$
BEGIN
    IF EXISTS (SELECT * FROM show_time_intersection(NEW.time_start, NEW.time_end, NEW.auditory_id))
        THEN RAISE EXCEPTION 'intersects with other show';
    END IF;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

--триггер не позволяет пересечься двум показам презентаций по времени в одной аудитории
CREATE TRIGGER check_time_intersection_trigger
    BEFORE INSERT OR UPDATE OF time_end, time_start ON show
    FOR EACH ROW EXECUTE PROCEDURE check_show_time_intersection();


--следующие два триггера устраняют путаницу: если человек спикер,
-- ему не нужно (и нельзя) дополнительно числиться в зрителях данного показа презентации


CREATE OR REPLACE FUNCTION check_if_listener_is_not_speaker()
    RETURNS trigger AS
$BODY$
BEGIN
    IF (SELECT speaker_id FROM show s
        WHERE s.id = NEW.show_id) = NEW.person_id
    THEN
        RAISE EXCEPTION 'person is speaker already';
    END IF;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER check_if_listener_is_not_speaker_trigger
    BEFORE INSERT OR UPDATE ON ticket
    FOR EACH ROW EXECUTE PROCEDURE check_if_listener_is_not_speaker();



CREATE OR REPLACE FUNCTION check_if_speaker_is_not_listener()
    RETURNS trigger AS
$BODY$
BEGIN
    DELETE FROM ticket t
    WHERE t.person_id=NEW.speaker_id AND t.show_id = NEW.id;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql;

CREATE TRIGGER check_if_speaker_is_not_listener_trigger
    AFTER INSERT OR UPDATE OF speaker_id ON show
    FOR EACH ROW EXECUTE PROCEDURE check_if_speaker_is_not_listener();