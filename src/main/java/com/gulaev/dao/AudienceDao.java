package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.Audience;
import com.gulaev.models.mappers.AudienceMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AudienceDao implements Dao<Audience> {

  private JdbcTemplate jdbcTemplate;
  private AudienceMapper audienceMapper;

  @Autowired
  public AudienceDao(JdbcTemplate jdbcTemplate, AudienceMapper audienceMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.audienceMapper = audienceMapper;
    log.trace("Initialization AudienceDao");
  }

  public Audience getById(int id) {
    String sql1 = "SELECT * FROM audiences WHERE audience_id =?;";
    log.debug("getById  id ={}", id);
    try {
      return jdbcTemplate.query(sql1, new Object[] {id}, audienceMapper).stream()
          .findAny()
          .orElseThrow(DaoLayerException::new);

    } catch (DaoLayerException e) {
      log.error("Audience with this Id does not Exist id = {}", id, e);
      throw new RuntimeException(e);
    }
  }

  public List<Audience> loadAll() {
    String sql1 = "SELECT * FROM audiences;";
    log.debug("loadAll by audiences;");
    return jdbcTemplate.query(sql1, audienceMapper);
  }

  @Override
  public void deleteById(int id) {
    jdbcTemplate.update("DELETE FROM audiences WHERE audience_id=?", id);
    log.debug("deleteById id = {}", id);
  }

  @Override
  public void update(int id, Audience audience) {
    jdbcTemplate.update(
        "UPDATE audiences SET audience_number=?, floor=?, number_of_seats=?, "
            + "interactive_whiteboard=?, is_empty=? WHERE audience_id=?;",
        audience.getAudienceNumber(),
        audience.getFloor(),
        audience.getNumberOfSeats(),
        audience.isInteractiveWhiteboard(),
        audience.isEmpty(),
        id);
    log.debug("update id = {}", id);
  }

  public void addNewAudience(
      int audienceNumber,
      int floor,
      int numberOfSeats,
      boolean interactiveWhiteboard,
      boolean isEmpty) {
    jdbcTemplate.update(
        "INSERT INTO "
            + "audiences(audience_number, floor, number_of_seats, interactive_whiteboard, is_empty)"
            + " VALUES (?, ?, ?, ?, ?);",
        audienceNumber,
        floor,
        numberOfSeats,
        interactiveWhiteboard,
        isEmpty);
    log.debug("addNewAudience");
  }
}
