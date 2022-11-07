package com.gulaev.models.mappers;

import com.gulaev.config.SpringConfig;
import com.gulaev.dao.StudentDao;
import com.gulaev.models.Group;
import com.gulaev.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GroupMapperTest {

    @Test
    void mapRowTest() throws SQLException {
        Group group = new Group();
        ResultSet resultSet = mock(ResultSet.class);
        List<Integer> students = new ArrayList<>();
        students.add(1);


        when(resultSet.getInt("group_id")).thenReturn(1);
        when(resultSet.getInt("course")).thenReturn(1);


        group.setGroupID(1);
        group.setCourse(1);

        GroupMapper groupMapper = new GroupMapper();


        assertEquals(group, groupMapper.mapRow(resultSet, 1));
    }

}