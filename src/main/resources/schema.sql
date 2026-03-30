CREATE TABLE IF NOT EXISTS venues
(
    venue_id   SERIAL PRIMARY KEY,
    venue_name VARCHAR(100) NOT NULL UNIQUE,
    location   VARCHAR(150)
);

CREATE TABLE IF NOT EXISTS events
(
    event_id   SERIAL PRIMARY KEY,
    event_name VARCHAR(100) NOT NULL UNIQUE,
    event_date DATE DEFAULT NOW(),
    venue_id   INT          NOT NULL REFERENCES venues (venue_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS attendees
(
    attendee_id   SERIAL PRIMARY KEY,
    attendee_name VARCHAR(50) NOT NULL,
    email         VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS event_attendee
(
    attendee_id INT REFERENCES attendees (attendee_id) NOT NULL,
    event_id    INT REFERENCES events (event_id)       NOT NULL,
    PRIMARY KEY (attendee_id, event_id)
)