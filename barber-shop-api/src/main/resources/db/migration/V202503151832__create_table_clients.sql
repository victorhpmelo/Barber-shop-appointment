CREATE TABLE IF NOT EXISTS clients (
                                       id BIGSERIAL NOT NULL PRIMARY KEY,
                                       name VARCHAR(150) NOT NULL,
                                       email VARCHAR(150) NOT NULL,
                                       phone CHAR(11) NOT NULL,
                                       CONSTRAINT uk_email UNIQUE (email),
                                       CONSTRAINT uk_phone UNIQUE (phone)
);

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'uk_email') THEN
            ALTER TABLE clients ADD CONSTRAINT uk_email UNIQUE (email);
        END IF;
    END $$;