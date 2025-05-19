CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY AUTO_INCREMENT,                      -- Уникальный идентификатор пользователя
    username VARCHAR(50) UNIQUE NOT NULL,                           -- Имя пользователя (уникальное)
    email VARCHAR(100) UNIQUE NOT NULL,                             -- Адрес электронной почты (уникальный)
    password_hash CHAR(64) NOT NULL,                                -- Хэш пароля для безопасного хранения
    full_name VARCHAR(50) NOT NULL,                                 -- Полное имя пользователя
    bio TEXT ,                                                      -- Описание профиля
    date_of_birth DATE                                              -- Дата рождения пользователя
);

