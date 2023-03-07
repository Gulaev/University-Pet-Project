package com.gulaev.dao;

import com.gulaev.models.mappers.AudienceMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.*;

public class AudienceDaoTest {

    private Logger logger = LoggerFactory.getLogger(AudienceDaoTest.class);

//    @Test
//    void getByIdTest() {
//        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
//        AudienceMapper audienceMapper = new AudienceMapper();
//        AudienceDao audienceDao = new AudienceDao(jdbcTemplate, audienceMapper);
//
//        String sql = "SELECT * FROM audiences WHERE audience_id =?;";
//        audienceDao.getById(1);
//
//        verify(jdbcTemplate, times(1)).query(sql,new Object[]{1} ,audienceMapper);
//
//    }
//
//    @Test
//    void loadAllTest() {
//        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
//        AudienceMapper audienceMapper = new AudienceMapper();
//
//        AudienceDao audienceDao = new AudienceDao(jdbcTemplate, audienceMapper);
//        String sql = "SELECT * FROM audiences;";
//
//        audienceDao.loadAll();
//        verify(jdbcTemplate, times(1)).query(sql, audienceMapper);
//
//
//    }
//
//    @Test
//    void deleteByIdTest() {
//        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
//        AudienceMapper audienceMapper = new AudienceMapper();
//        AudienceDao audienceDao = new AudienceDao(jdbcTemplate, audienceMapper);
//
//        String sql = "DELETE FROM audiences WHERE audience_id=?";
//        audienceDao.deleteById(1);
//        verify(jdbcTemplate, times(1)).update(sql, 1);
//
//    }
//
//    @Test
//    void addNewAudienceTest() {
//        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
//        AudienceDao audienceDao = new AudienceDao(jdbcTemplate, new AudienceMapper());
//
//        String sql = "INSERT INTO " +
//                "audiences(audience_number, floor, number_of_seats, interactive_whiteboard, is_empty)" +
//                " VALUES (?, ?, ?, ?, ?);";
//        audienceDao.addNewAudience(1,1,100, true, true);
//
//        verify(jdbcTemplate, times(1)).update(sql, 1,1, 100, true, true);
//
//    }
}
