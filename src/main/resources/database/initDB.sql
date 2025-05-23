CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY AUTO_INCREMENT,                      -- Уникальный идентификатор пользователя
    username VARCHAR(50) UNIQUE NOT NULL,                           -- Имя пользователя (уникальное)
    email VARCHAR(100) UNIQUE NOT NULL,                             -- Адрес электронной почты (уникальный)
    password_hash CHAR(64) NOT NULL,                                -- Хэш пароля для безопасного хранения
    full_name VARCHAR(50) NOT NULL                                  -- Полное имя пользователя
);

CREATE TABLE IF NOT EXISTS user_profiles(
    user_id SERIAL PRIMARY KEY REFERENCES users(user_id),
    sex VARCHAR(6) NOT NULL CHECK (sex IN ('MALE', 'FEMALE')),
    birth_date DATE NOT NULL,
    height INTEGER NOT NULL CHECK (height BETWEEN 100 AND 250),
    weight DOUBLE PRECISION NOT NULL CHECK (weight BETWEEN 30 AND 300)
)

