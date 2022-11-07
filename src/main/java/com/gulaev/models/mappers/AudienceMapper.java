package com.gulaev.models.mappers;

import com.gulaev.models.Audience;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AudienceMapper implements RowMapper<Audience> {

  @Autowired ApplicationContext context;

  @Override
  public Audience mapRow(ResultSet rs, int rowNum) {
    log.trace("Mapping Audience starting mapRow...");
    Audience audience = new Audience();

    try {

      audience.setAudienceId(rs.getInt("audience_id"));
      audience.setAudienceNumber(rs.getInt("audience_number"));
      audience.setFloor(rs.getInt("floor"));
      audience.setNumberOfSeats(rs.getInt("number_of_seats"));
      audience.setInteractiveWhiteboard(rs.getBoolean("interactive_whiteboard"));
      audience.setEmpty(rs.getBoolean("is_empty"));

    } catch (SQLException e) {

      log.error("SQL Mapping Error ", e);
      throw new RuntimeException(e);
    }

    log.debug("Mapping ended {}", audience);
    return audience;
  }
}
