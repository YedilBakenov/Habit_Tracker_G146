CREATE TABLE IF NOT EXISTS g146.cities
(
    id           SERIAL PRIMARY KEY,
    city_name    varchar,
    code         varchar,
    count_people int
);

CREATE TABLE IF NOT EXISTS g146.habits
(
    id           SERIAL PRIMARY KEY,
    habit_name    varchar,
    duration  numeric,
    frequency int,
    created_at timestamp,
    updated_at timestamp
);

CREATE TABLE IF NOT EXISTS g146.users
(
    id           SERIAL PRIMARY KEY,
    age int,
    full_name varchar,
    gpa numeric,
    city_id int
);

CREATE TABLE IF NOT EXISTS g146.users_habits
(
    user_id           int,
    habits_id int
);