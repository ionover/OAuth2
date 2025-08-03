-- Создание таблицы City
CREATE TABLE city (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Создание таблицы Person
CREATE TABLE person (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(1023) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    gender VARCHAR(10),
    email VARCHAR(255),
    phone VARCHAR(255),
    city_id BIGINT NOT NULL,
    CONSTRAINT fk_person_city FOREIGN KEY (city_id) REFERENCES city(id)
);

-- Вставка тестовых данных в таблицу City
INSERT INTO city (name) VALUES 
    ('Moscow'),
    ('Saint Petersburg'),
    ('Novosibirsk'),
    ('Yekaterinburg'),
    ('Kazan');

-- Вставка тестовых данных в таблицу Person
INSERT INTO person (name, surname, age, gender, email, phone, city_id) VALUES 
    ('Alexey', 'Smirnov', 28, 'MALE', 'alexey.smirnov@example.com', '555-0101', 1),
    ('Maria', 'Ivanova', 34, 'FEMALE', 'maria.ivanova@example.com', '555-0202', 1),
    ('Dmitry', 'Petrov', 45, 'MALE', 'dmitry.petrov@example.com', '555-0303', 2),
    ('Elena', 'Sokolova', 22, 'FEMALE', 'elena.sokolova@example.com', '555-0404', 2),
    ('Sergey', 'Kuznetsov', 39, 'MALE', 'sergey.kuznetsov@example.com', '555-0505', 3);
