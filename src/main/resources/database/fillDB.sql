INSERT IGNORE INTO users (username, email, password_hash, full_name)
VALUES ('admin', 'admin@mail.ru', '$2a$10$t2NWpqQGLKSSRM6ZCa/ymOTkrdrMuzHniXOQzT3aqOArgai30.z/O', 'Glavni Admin');

INSERT IGNORE INTO user_profiles (user_id, sex, birth_date, height, weight )
VALUES (1, 'MALE', '1990-01-01', 173, 79);