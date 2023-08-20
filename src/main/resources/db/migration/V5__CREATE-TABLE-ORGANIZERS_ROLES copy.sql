CREATE TABLE organizers_roles (
    organizer_id INT,
    role VARCHAR(255),
    FOREIGN KEY (organizer_id) REFERENCES organizers(id)
);