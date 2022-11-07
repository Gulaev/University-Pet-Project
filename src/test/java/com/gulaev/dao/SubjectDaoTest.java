package com.gulaev.dao;

import com.gulaev.models.mappers.SubjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

public class SubjectDaoTest {

    @Test
    void getByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        SubjectMapper subjectMapper = new SubjectMapper();
        SubjectDao subjectDao = new SubjectDao(jdbcTemplate, subjectMapper);

        String sql1 = "SELECT * FROM subjects WHERE subject_id = ?;";

        subjectDao.getById(1);
        verify(jdbcTemplate, times(1)).query(sql1, new Object[]{1}, subjectMapper);

    }

    @Test
    void loadAllTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        SubjectMapper subjectMapper = new SubjectMapper();
        SubjectDao subjectDao = new SubjectDao(jdbcTemplate, subjectMapper);

        String sql1 = "SELECT * FROM subjects;";
        subjectDao.loadAll();
        verify(jdbcTemplate, times(1)).query(sql1, subjectMapper);

    }

    @Test
    void deleteByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        SubjectDao subjectDao = new SubjectDao(jdbcTemplate, new SubjectMapper());

        String sql = "DELETE FROM subjects WHERE subject_id=?;";

        subjectDao.deleteById(1);
        verify(jdbcTemplate, times(1)).update(sql, 1);


    }

    @Test
    void addBySubject() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        SubjectDao subjectDao = new SubjectDao(jdbcTemplate, new SubjectMapper());

        String sql = "INSERT INTO subjects(subject_name, subjects_description, speciality_id, subject_teacher_id) " +
                "VALUES (?, ?, ?, ?)";

        subjectDao.addNewSubject("Math", "Math course",1, 1);

        verify(jdbcTemplate, times(1)).update(sql,"Math", "Math course", 1, 1);

    }
}
