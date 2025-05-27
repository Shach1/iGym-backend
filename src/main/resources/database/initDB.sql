-- Таблица с пользователями
CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash CHAR(64) NOT NULL,
    full_name VARCHAR(50) NOT NULL
);
-- Таблица профилей пользователей
CREATE TABLE IF NOT EXISTS user_profiles(
    user_id SERIAL PRIMARY KEY REFERENCES users(user_id),
    sex VARCHAR(6) NOT NULL CHECK (sex IN ('MALE', 'FEMALE')),
    birth_date DATE NOT NULL,
    height INTEGER NOT NULL CHECK (height BETWEEN 100 AND 250),
    weight DOUBLE PRECISION NOT NULL CHECK (weight BETWEEN 30 AND 300)
);

-- Таблица категорий справочника
CREATE TABLE IF NOT EXISTS reference_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Таблица рекомендаций
CREATE TABLE IF NOT EXISTS recommendations (
    recommendation_id INT AUTO_INCREMENT PRIMARY KEY,
    category_id INT NOT NULL,
    title VARCHAR(100) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    created_at DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (category_id) REFERENCES reference_categories(category_id)
);
