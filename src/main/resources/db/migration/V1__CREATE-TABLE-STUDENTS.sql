CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    group_id INT,
    squad_id INT,
    FOREIGN KEY (group_id) REFERENCES groups(id),
    FOREIGN KEY (squad_id) REFERENCES squads(id)
);
