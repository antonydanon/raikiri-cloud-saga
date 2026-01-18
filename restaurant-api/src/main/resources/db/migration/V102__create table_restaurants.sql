CREATE TABLE IF NOT EXISTS restaurants
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    address VARCHAR(500),
    phone VARCHAR(20),
    is_active BOOLEAN DEFAULT true
);