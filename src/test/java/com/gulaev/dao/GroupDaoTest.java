package com.gulaev.dao;

import com.gulaev.models.Group;
import com.gulaev.models.Student;
import com.gulaev.models.mappers.GroupMapper;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class GroupDaoTest {


    @Test
    void getByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        GroupMapper groupMapper = new GroupMapper();
        GroupDao groupDao = new GroupDao(jdbcTemplate, groupMapper);

        String sql = "SELECT * FROM groups WHERE group_id = ?;";

        groupDao.getById(1);

        verify(jdbcTemplate, times(1)).query(sql, new Object[]{1} ,groupMapper);

    }

    @Test
    void loadAllTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        GroupMapper groupMapper = new GroupMapper();
        GroupDao groupDao = new GroupDao(jdbcTemplate, groupMapper);

        String sql1 = "SELECT * FROM groups;";

        groupDao.loadAll();

        verify(jdbcTemplate, times(1)).query(sql1, groupMapper);

    }

    @Test
    void deleteByIdTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        GroupDao groupDao = new GroupDao(jdbcTemplate, new GroupMapper());

        String sql = "DELETE FROM groups WHERE group_id=?";
        int id = 1;
        groupDao.deleteById(id);
        verify(jdbcTemplate, times(1)).update(sql,id);

    }

    @Test
    void addNewGroupTest() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        GroupDao groupDao = new GroupDao(jdbcTemplate,  new GroupMapper());

        String sql = "INSERT INTO groups(course) VALUES (?);";
        int id = 1;
        groupDao.addNewGroup(id);
        verify(jdbcTemplate, times(1)).update(sql, id);

    }
}
