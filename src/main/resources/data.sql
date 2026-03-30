INSERT INTO venues (venue_name, location) VALUES ('The Grand Ballroom', '123 Main St, New York');
INSERT INTO venues (venue_name, location) VALUES ('Tech Hub Center', '456 Innovation Ave, San Francisco');
INSERT INTO venues (venue_name, location) VALUES ('Greenwood Park', '789 Nature Trail, Austin');
INSERT INTO venues (venue_name, location) VALUES ('Skyline Lounge', '101 Rooftop Way, Chicago');
INSERT INTO venues (venue_name, location) VALUES ('Ocean Breeze Resort', '202 Coastal Rd, Miami');

INSERT INTO events (event_name, event_date, venue_id) VALUES ('Annual Tech Summit', '2024-05-15', 2);
INSERT INTO events (event_name, event_date, venue_id) VALUES ('Wedding of Alice & Bob', '2024-06-20', 1);
INSERT INTO events (event_name, event_date, venue_id) VALUES ('Summer Music Festival', '2024-07-10', 3);
INSERT INTO events (event_name, event_date, venue_id) VALUES ('Startup Pitch Night', '2024-08-05', 2);
INSERT INTO events (event_name, event_date, venue_id) VALUES ('Corporate Gala', '2024-09-12', 4);

INSERT INTO attendees (attendee_name, email) VALUES ('Roa Chamreun', 'roa@example.com');
INSERT INTO attendees (attendee_name, email) VALUES ('Rem', 'rem@example.com');
INSERT INTO attendees (attendee_name, email) VALUES ('Alice Johnson', 'alice@testmail.com');
INSERT INTO attendees (attendee_name, email) VALUES ('Bob Wilson', 'bob@dev.io');
INSERT INTO attendees (attendee_name, email) VALUES ('Saber', 'saber@hq.com');

INSERT INTO event_attendee (attendee_id, event_id) VALUES (1, 1);
INSERT INTO event_attendee (attendee_id, event_id) VALUES (2, 1);
INSERT INTO event_attendee (attendee_id, event_id) VALUES (3, 2);
INSERT INTO event_attendee (attendee_id, event_id) VALUES (4, 3);
INSERT INTO event_attendee (attendee_id, event_id) VALUES (5, 4);
