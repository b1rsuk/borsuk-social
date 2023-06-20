CREATE TABLE IF NOT EXISTS client
(
    id          BIGSERIAL PRIMARY KEY,
    create_date TIMESTAMP(6),
    name        VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS message
(
    id           BIGSERIAL PRIMARY KEY,
    create_date  TIMESTAMP(6),
    message      VARCHAR(255) NOT NULL,
    client_id    bigint       NOT NULL
            REFERENCES client,
    receipter_id bigint       NOT NULL
            REFERENCES client
);

CREATE TABLE IF NOT EXISTS role
(
    id        BIGSERIAL PRIMARY KEY,
    role_type SMALLINT NOT NULL
            CHECK ((role_type >= 0) AND (role_type <= 1))
);

CREATE TABLE IF NOT EXISTS client_role
(
    client_id bigint NOT NULL
            REFERENCES client,
    role_id   bigint NOT NULL
            REFERENCES role,
        UNIQUE (client_id, role_id)
);

