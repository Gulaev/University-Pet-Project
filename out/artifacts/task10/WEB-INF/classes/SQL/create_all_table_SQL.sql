CREATE TABLE IF NOT EXISTS teachers (
      teacher_id BIGSERIAL NOT NULL PRIMARY KEY,
      first_name VARCHAR(50) NOT NULL,
      last_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS audiences (
    audience_id BIGSERIAL NOT NULL PRIMARY KEY,
    audience_number INT,
    floor INT NOT NULL,
    number_of_seats INT NOT NULL,
    interactive_whiteboard boolean,
    is_empty boolean
);

CREATE TABLE IF NOT EXISTS groups (
    group_id BIGSERIAL PRIMARY KEY NOT NULL,
    course INT
);

CREATE TABLE IF NOT EXISTS students (
    student_id BIGSERIAL NOT NULL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    group_id INT NOT NUll,
        FOREIGN KEY (group_id)
            REFERENCES groups(group_id)
);

CREATE TABLE IF NOT EXISTS specialities(
    speciality_id BIGSERIAL NOT NULL PRIMARY KEY,
    name_of_speciality VARCHAR(100),
--    teacher_id INT,
    course INT,
    audience_id INT,
--        FOREIGN KEY (teacher_id)
--           REFERENCES teachers(teacher_id),
        FOREIGN KEY (audience_id)
           REFERENCES audiences(audience_id)
);

CREATE TABLE IF NOT EXISTS subjects(
      subject_id BIGSERIAL NOT NULL PRIMARY KEY,
      subject_name VARCHAR(100),
      subjects_description TEXT,
      speciality_id INT,
      subject_teacher_id INT,
      FOREIGN KEY (subject_teacher_id)
            REFERENCES teachers(teacher_id),

      FOREIGN KEY (speciality_id)
            REFERENCES specialities(speciality_id)
);

CREATE TABLE IF NOT EXISTS lessons (
      lesson_id BIGSERIAL NOT NULL PRIMARY KEY,
      lesson_name VARCHAR(100),
--      group_id INT,
      lesson_start TIMESTAMP,
      lesson_end TIMESTAMP,
      audience_id INT,
      subject_id INT,
--      FOREIGN KEY (group_id)
--            REFERENCES groups(group_id),

      FOREIGN KEY (audience_id)
            REFERENCES audiences(audience_id),

      FOREIGN KEY (subject_id)
            REFERENCES subjects(subject_id)
);

CREATE TABLE IF NOT EXISTS group_and_lesson (
      group_id INT,
      lesson_id INT,
      FOREIGN KEY(group_id)
            REFERENCES groups(group_id),

      FOREIGN KEY(lesson_id)
            REFERENCES lessons(lesson_id)
);

CREATE TABLE IF NOT EXISTS specialities_and_teachers (
      speciality_id INT,
      teacher_id INT,
      FOREIGN KEY (speciality_id)
            REFERENCES specialities(speciality_id),

      FOREIGN KEY (teacher_id)
            REFERENCES teachers(teacher_id)
);



--ALTER TABLE students ADD group_id INT REFERENCES groups(group_id);
--ALTER TABLE groups ADD speciality_id INT REFERENCES specialities(speciality_id);
--SELECT * FROM subjects LEFT OUTER JOIN teachers ON subjects.subject_teacher_id = teachers.teacher_id RIGHT OUTER JOIN specialities ON subjects.speciality_id = specialities.speciality_id;