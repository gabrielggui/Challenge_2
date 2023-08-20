CREATE TABLE organizers_groups (
    organizer_id INT,
    group_id INT,
    FOREIGN KEY (organizer_id) REFERENCES organizers(id),
    FOREIGN KEY (group_id) REFERENCES groups(id)
);