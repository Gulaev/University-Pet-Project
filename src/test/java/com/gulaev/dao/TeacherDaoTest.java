package com.gulaev.dao;

import com.gulaev.config.SpringConfig;
import com.gulaev.models.Teacher;
import com.gulaev.models.mappers.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TeacherDaoTest {

    @Autowired
    ApplicationContext context;

    @Test
    void getByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

        String sql = "SELECT * FROM teachers WHERE teacher_id = ?;";

        TeacherMapper teacherMapper = new TeacherMapper();

        TeacherDao teacherDao = new TeacherDao(jdbcTemplate, teacherMapper);
        teacherDao.getById(1);
        verify(jdbcTemplate, times(1)).query(sql, new Object[]{1},
                        teacherMapper);

    }

    @Test
    void loadAllTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        TeacherMapper teacherMapper = new TeacherMapper();

        String sql = "SELECT * FROM teachers;";

        TeacherDao teacherDao = new TeacherDao(jdbcTemplate, teacherMapper);

        teacherDao.loadAll();

        verify(jdbcTemplate, times(1)).query(sql, teacherMapper);
    }

    @Test
    void deleteByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        TeacherMapper teacherMapper = new TeacherMapper();

        String sql = "DELETE FROM teachers WHERE teacher_id=?";

        TeacherDao teacherDao = new TeacherDao(jdbcTemplate, teacherMapper);
        teacherDao.deleteById(1);

        verify(jdbcTemplate, times(1)).update(sql, 1);


    }

    @Test
    void addNewTeacherTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);

        String sql = "INSERT INTO teachers(first_name, last_name) VALUES (?,?)";

        TeacherDao teacherDao = new TeacherDao(jdbcTemplate, new TeacherMapper());
        teacherDao.addNewTeacher("John", "Doe");

        verify(jdbcTemplate, times(1)).update(sql, "John", "Doe");
    }
}
