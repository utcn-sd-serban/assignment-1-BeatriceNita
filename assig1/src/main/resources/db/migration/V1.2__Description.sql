CREATE TABLE IF NOT EXISTS appuser (
    id serial PRIMARY KEY,
    username  VARCHAR(32),
    password  VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS question (
    id serial PRIMARY KEY,
    author_id INT NOT NULL REFERENCES appuser(id),
    create_date  TIMESTAMP,
    title        VARCHAR(32),
    text         VARCHAR(64)
);

CREATE TABLE IF NOT EXISTS tag (
    id serial PRIMARY KEY,
    tag_name  VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS tag_question (
    id serial PRIMARY KEY,
    id_q INT NOT NULL REFERENCES question(id),
    id_t INT NOT NULL REFERENCES tag(id)
);
