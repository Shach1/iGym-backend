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

-- Таблица групп мышц
CREATE TABLE IF NOT EXISTS muscle_groups (
    muscle_group_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description TEXT NOT NULL
);

-- Таблица упражнений
CREATE TABLE IF NOT EXISTS exercises (
    exercise_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    muscle_group_id INT NOT NULL,
    FOREIGN KEY (muscle_group_id) REFERENCES muscle_groups(muscle_group_id)
);

-- Таблица тренировок
CREATE TABLE IF NOT EXISTS workouts (
    workout_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    muscle_group_id INT NOT NULL,
    FOREIGN KEY (muscle_group_id) REFERENCES muscle_groups(muscle_group_id)
);

-- Связующая таблица (многие-ко-многим между тренировками и упражнениями)
CREATE TABLE IF NOT EXISTS workout_exercises (
    workout_id INT NOT NULL,
    exercise_id INT NOT NULL,
    exercise_order INT NOT NULL,
    PRIMARY KEY (workout_id, exercise_id),
    FOREIGN KEY (workout_id) REFERENCES workouts(workout_id),
    FOREIGN KEY (exercise_id) REFERENCES exercises(exercise_id)
);