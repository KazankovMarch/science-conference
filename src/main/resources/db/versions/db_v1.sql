
CREATE TABLE person (
    id SERIAL PRIMARY KEY,
    fio varchar(255) NOT NULL,
    email varchar(255) UNIQUE
);

--нарушение 1 нормальной формы: список слушателей не скалярный
--нарушение 2 нормальной формы: subject зависит только от title, но не от time_start
--нарушение 3 нормальной формы: audience_max_count зависит только от auditory_address
CREATE TABLE presentation (
    title varchar(255),
    subject varchar(255),
    speaker_id bigint REFERENCES person(id),
    time_start timestamp,
    time_end timestamp,
    audience_max_count int DEFAULT null,
    auditory_address varchar(255) DEFAULT null,
    audience bigint[] NOT NULL DEFAULT array[]::bigint[]
);
ALTER TABLE presentation ADD PRIMARY KEY (title, time_start);

INSERT INTO person(fio, email) VALUES
('Казанков Андрей Дмитриевич', 'akazankov@fix.ru'),
('Попов Вячеслав Никитович', 'popov234@mail.ru'),
('Гришкевич Олег Юрьевич', 'grisha_master@list.ru'),
('Кагирова Камилла Сергеевна', 'KKSerg@gmail.com'),
('Дубровский Михаил Олегович', 'Dubrovski_mixa@mail.ru');

INSERT INTO presentation
(title, subject, speaker_id, time_start, time_end, audience_max_count, auditory_address, audience) VALUES
('Domain Driven Development', 'Основы DDD, плюсы и минусы DDD, внедрение DDD в уже существующий проект', 1, make_timestamp(2020, 1, 1, 8, 0, 0), make_timestamp(2020, 1, 1, 11, 00, 0),
 70, 'корпус 2, ауд. 219', array[2,3,4,5]),
('Photo-realistic drawing', 'Современные способы создания фотореалистичных рисунков', 4, make_timestamp(2020, 1, 1, 11, 30, 0), make_timestamp(2020, 1, 1, 13, 00, 0),
 70, 'корпус 2, ауд. 810', array[1, 5])