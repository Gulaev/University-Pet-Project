package com.gulaev.dao;

import com.gulaev.models.GroupAndLesson;
import com.gulaev.models.mappers.GroupAndLessonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupAndLessonDaoTest {

    @Test
    void getById() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        GroupAndLessonMapper groupAndLessonMapper = new GroupAndLessonMapper();
        GroupAndLessonDao groupAndLessonDao = new GroupAndLessonDao(groupAndLessonMapper, jdbcTemplate);

        String sql = "SELECT * FROM group_and_lesson WHERE group_id=?";
        groupAndLessonDao.getById(1);

        verify(jdbcTemplate, times(1)).query(sql,new Object[]{1}, groupAndLessonMapper);
    }

    @Test
    void loadAll() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        GroupAndLessonMapper groupAndLessonMapper = new GroupAndLessonMapper();
        GroupAndLessonDao groupAndLessonDao = new GroupAndLessonDao(groupAndLessonMapper, jdbcTemplate);

        String sql = "SELECT * FROM group_and_lesson";
        groupAndLessonDao.loadAll();

        verify(jdbcTemplate, times(1)).query(sql, groupAndLessonMapper);
    }

    @Test
    void deleteById() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        GroupAndLessonMapper groupAndLessonMapper = new GroupAndLessonMapper();
        GroupAndLessonDao groupAndLessonDao = new GroupAndLessonDao(groupAndLessonMapper, jdbcTemplate);

        String sql = "DELETE FROM group_and_lesson WHERE group_id=?";
        groupAndLessonDao.deleteById(1);

        verify(jdbcTemplate, times(1)).update(sql, 1);

    }

    @Test
    void addNewGroupAndLesson() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        GroupAndLessonMapper groupAndLessonMapper = new GroupAndLessonMapper();
        GroupAndLessonDao groupAndLessonDao = new GroupAndLessonDao(groupAndLessonMapper, jdbcTemplate);

        String sql = "INSERT INTO group_and_lesson VALUES (?, ?)";
        groupAndLessonDao.addNewGroupAndLesson(1,1);

        verify(jdbcTemplate, times(1)).update(sql, 1, 1);
    }
}