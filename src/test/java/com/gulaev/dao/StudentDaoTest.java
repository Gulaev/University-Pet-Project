package com.gulaev.dao;

import com.gulaev.models.mappers.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

public class StudentDaoTest {

    @Test
    void getByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        StudentMapper studentMapper = new StudentMapper();
        StudentDao studentDao = new StudentDao(jdbcTemplate, studentMapper);

        String sql = "SELECT * FROM students WHERE student_id = ?";
        studentDao.getById(1);

        verify(jdbcTemplate, times(1)).query(sql, new Object[]{1}, studentMapper);

    }

    @Test
    void loadAllTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        StudentMapper studentMapper = new StudentMapper();
        StudentDao studentDao = new StudentDao(jdbcTemplate, studentMapper);

        String sql1 = "SELECT * FROM students;";

        studentDao.loadAll();
        verify(jdbcTemplate, times(1)).query(sql1, studentMapper);


    }

    @Test
    void deleteByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        StudentMapper studentMapper = new StudentMapper();
        StudentDao studentDao = new StudentDao(jdbcTemplate, studentMapper);

        String sql = "DELETE FROM students WHERE student_id=?";

        studentDao.deleteById(1);
        verify(jdbcTemplate, times(1)).update(sql, 1);

    }

    @Test
    void addNewStudentTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        StudentMapper studentMapper = new StudentMapper();
        StudentDao studentDao = new StudentDao(jdbcTemplate, studentMapper);

        String sql = "INSERT INTO students(first_name, last_name, group_id) VALUES (?, ?, ?);";

        studentDao.addStudent("John", "Doe", 1);

        verify(jdbcTemplate, times(1)).update(sql, "John", "Doe", 1);

    }
}
