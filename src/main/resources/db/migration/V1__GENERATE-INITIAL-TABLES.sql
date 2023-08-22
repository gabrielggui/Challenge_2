CREATE TABLE pb_groups (
    id INT AUTO_INCREMENT PRIMARY KEY,
    group_name VARCHAR(255) NOT NULL
);

CREATE TABLE organizers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE organizers_groups (
    organizer_id INT,
    group_id INT,
    FOREIGN KEY (organizer_id) REFERENCES organizers(id),
    FOREIGN KEY (group_id) REFERENCES pb_groups(id)
);

CREATE TABLE organizers_roles (
    organizer_id INT,
    role VARCHAR(255),
    FOREIGN KEY (organizer_id) REFERENCES organizers(id)
);

CREATE TABLE squads (
    id INT AUTO_INCREMENT PRIMARY KEY,
    squad_name VARCHAR(255) NOT NULL
);

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    group_id INT,
    squad_id INT,
    FOREIGN KEY (group_id) REFERENCES pb_groups(id),
    FOREIGN KEY (squad_id) REFERENCES squads(id)
);

CREATE TABLE assessments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    activity_name VARCHAR(255) NOT NULL,
    grade FLOAT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students(id)
);