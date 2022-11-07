package com.gulaev.dao;

import com.gulaev.models.mappers.SpecialitiesAndTeachersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpecialitiesAndTeachersDaoTest {

    @Test
    void getById() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        SpecialitiesAndTeachersMapper specialitiesAndTeachersMapper = new SpecialitiesAndTeachersMapper();
        SpecialitiesAndTeachersDao specialitiesAndTeachersDao =
                new SpecialitiesAndTeachersDao(jdbcTemplate, specialitiesAndTeachersMapper);

        String sql = "SELECT * FROM specialities_and_teachers WHERE speciality_id=?";
        specialitiesAndTeachersDao.getById(1);

        verify(jdbcTemplate, times(1))
                .query(sql, new Object[]{1}, specialitiesAndTeachersMapper);
    }

    @Test
    void loadAll() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        SpecialitiesAndTeachersMapper specialitiesAndTeachersMapper = new SpecialitiesAndTeachersMapper();
        SpecialitiesAndTeachersDao specialitiesAndTeachersDao =
                new SpecialitiesAndTeachersDao(jdbcTemplate, specialitiesAndTeachersMapper);

        String sql = "SELECT * FROM specialities_and_teachers";
        specialitiesAndTeachersDao.loadAll();

        verify(jdbcTemplate, times(1)).query(sql, specialitiesAndTeachersMapper);

    }

    @Test
    void deleteById() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        SpecialitiesAndTeachersMapper specialitiesAndTeachersMapper = new SpecialitiesAndTeachersMapper();
        SpecialitiesAndTeachersDao specialitiesAndTeachersDao =
                new SpecialitiesAndTeachersDao(jdbcTemplate, specialitiesAndTeachersMapper);

        String sql = "DELETE FROM specialities_and_teachers WHERE speciality_id=?";
        specialitiesAndTeachersDao.deleteById(1);

        verify(jdbcTemplate, times(1)).update(sql, 1);
    }

    @Test
    void addNewSpecialitiesAndTeachers() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        SpecialitiesAndTeachersMapper specialitiesAndTeachersMapper = new SpecialitiesAndTeachersMapper();
        SpecialitiesAndTeachersDao specialitiesAndTeachersDao =
                new SpecialitiesAndTeachersDao(jdbcTemplate, specialitiesAndTeachersMapper);

        String sql = "INSERT INTO specialities_and_teachers VALUES (?, ?)";
        specialitiesAndTeachersDao.addNewSpecialitiesAndTeachers(1,1);

        verify(jdbcTemplate, times(1)).update(sql, 1, 1);
    }
}