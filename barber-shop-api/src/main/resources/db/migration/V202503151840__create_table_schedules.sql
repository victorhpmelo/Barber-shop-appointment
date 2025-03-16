CREATE TABLE IF NOT EXISTS schedules (
                                         id BIGSERIAL NOT NULL PRIMARY KEY,
                                         start_at TIMESTAMP NOT NULL,
                                         end_at TIMESTAMP NOT NULL,
                                         client_id BIGINT NOT NULL,
                                         CONSTRAINT uk_schedule_interval UNIQUE (start_at, end_at),
    CONSTRAINT fk_clients_schedules FOREIGN KEY (client_id) REFERENCES clients (id)
    );

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'uk_schedule_interval') THEN
ALTER TABLE schedules ADD CONSTRAINT uk_schedule_interval UNIQUE (start_at, end_at);
END IF;
END $$;