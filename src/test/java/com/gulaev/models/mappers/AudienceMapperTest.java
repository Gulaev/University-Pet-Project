package com.gulaev.models.mappers;

import com.gulaev.models.Audience;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AudienceMapperTest {

    @Test
    void mapRowTest() throws SQLException {

        Audience audience = new Audience();

        ResultSet resultSet = mock(ResultSet.class);
        audience.setAudienceId(1);
        audience.setAudienceNumber(1);
        audience.setFloor(1);
        audience.setNumberOfSeats(100);
        audience.setInteractiveWhiteboard(true);
        audience.setEmpty(true);

        when(resultSet.getInt("audience_id")).thenReturn(1);
        when(resultSet.getInt("audience_number")).thenReturn(1);
        when(resultSet.getInt("floor")).thenReturn(1);
        when(resultSet.getInt("number_of_seats")).thenReturn(100);
        when(resultSet.getBoolean("interactive_whiteboard")).thenReturn(true);
        when(resultSet.getBoolean("is_empty")).thenReturn(true);

        AudienceMapper audienceMapper = new AudienceMapper();

        assertEquals(audience, audienceMapper.mapRow(resultSet, 1));
    }
}