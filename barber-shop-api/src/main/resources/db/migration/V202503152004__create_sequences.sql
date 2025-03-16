CREATE SEQUENCE IF NOT EXISTS clients_seq START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS schedules_seq START WITH 1 INCREMENT BY 50;

DO $$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'uk_email') THEN
            ALTER TABLE clients ADD CONSTRAINT uk_email UNIQUE (email);
        END IF;
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'uk_phone') THEN
            ALTER TABLE clients ADD CONSTRAINT uk_phone UNIQUE (phone);
        END IF;
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'uk_schedule_interval') THEN
            ALTER TABLE schedules ADD CONSTRAINT uk_schedule_interval UNIQUE (start_at, end_at);
        END IF;
        IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'fk_schedules_on_client') THEN
            ALTER TABLE schedules ADD CONSTRAINT fk_schedules_on_client FOREIGN KEY (client_id) REFERENCES clients (id);
        END IF;
    END $$;