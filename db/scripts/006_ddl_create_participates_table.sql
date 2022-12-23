CREATE TABLE IF NOT EXISTS participates (
   id SERIAL PRIMARY KEY,
   item_id INT NOT NULL REFERENCES items(id),
   user_id INT NOT NULL REFERENCES users(id)
);