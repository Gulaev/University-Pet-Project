package com.gulaev.dao;

import com.gulaev.models.mappers.LessonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalTime;

import static org.mockito.Mockito.*;

public class LessonDaoTest {

    @Test
    void getByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        LessonMapper lessonMapper = new LessonMapper();
        LessonDao lessonDao = new LessonDao(jdbcTemplate, lessonMapper);

        String sql = "SELECT * FROM lessons WHERE lesson_id = ?;";

        lessonDao.getById(1);

        verify(jdbcTemplate, times(1)).query(sql, new Object[]{1}, lessonMapper);

    }

    @Test
    void loadAllTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        LessonMapper lessonMapper = new LessonMapper();
        LessonDao lessonDao = new LessonDao(jdbcTemplate, lessonMapper);

        String sql1 = "SELECT * FROM lessons;";

        lessonDao.loadAll();

        verify(jdbcTemplate, times(1)).query(sql1, lessonMapper);
    }

    @Test
    void deleteByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        LessonDao lessonDao = new LessonDao(jdbcTemplate, new LessonMapper());

        String sql = "DELETE FROM lessons WHERE lesson_id= ?";
        lessonDao.deleteById(1);
        verify(jdbcTemplate, times(1)).update(sql,1);

    }

    @Test
    void addLessonTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        LessonDao lessonDao = new LessonDao(jdbcTemplate, new LessonMapper());


        Timestamp lessonStart = Timestamp.valueOf("2022-07-12 09:30:00.000");
        Timestamp lessonEnd = Timestamp.valueOf("2022-07-12 11:00:00.000");

        String sql = "INSERT INTO lessons(lesson_name, lesson_start, lesson_end, audience_id, subject_id) " +
                "VALUES (?, ?, ?, ?, ?);";

        lessonDao.addNewLesson("Math", lessonStart, lessonEnd, 1,1);

        verify(jdbcTemplate, times(1)).update(sql, "Math", lessonStart, lessonEnd, 1, 1);

    }
}
