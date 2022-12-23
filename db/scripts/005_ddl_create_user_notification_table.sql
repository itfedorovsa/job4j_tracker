CREATE TABLE IF NOT EXISTS user_notifications (
    id SERIAL PRIMARY KEY,
    messenger TEXT,
    identify TEXT,
    user_id INT REFERENCES users(id)
);