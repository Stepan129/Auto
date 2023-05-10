DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE autos
(
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(32),
    brand VARCHAR(32),
    model VARCHAR(32),
    year INTEGER,
    country VARCHAR(64),
    condition VARCHAR(64),
    mileage FLOAT,
    price FLOAT,
    info TEXT
)

CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(32) UNIQUE NOT NULL,
    password_hash VARCHAR(128)       NOT NULL,
    is_admin      BOOLEAN            NOT NULL,
    first_name    VARCHAR(64)        NOT NULL,
    middle_name   VARCHAR(64)        NOT NULL,
    last_name     VARCHAR(64)        NOT NULL,
    phone         VARCHAR(16)        NOT NULL,
    email         VARCHAR(32)        NOT NULL,
    info          TEXT
);