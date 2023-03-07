package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.Audience;
import com.gulaev.models.mappers.AudienceMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
public class AudienceDao implements Dao<Audience> {

  private JdbcTemplate jdbcTemplate;
  private AudienceMapper audienceMapper;
  private SessionFactory sessionFactory;

  @Autowired
  public AudienceDao(JdbcTemplate jdbcTemplate, AudienceMapper audienceMapper,
      SessionFactory sessionFactory) {
    this.jdbcTemplate = jdbcTemplate;
    this.audienceMapper = audienceMapper;
    this.sessionFactory = sessionFactory;
    log.trace("Initialization AudienceDao");
  }

  @Transactional(readOnly = true)
  public Audience getById(int id) {

    Session session = sessionFactory.getCurrentSession();

//     session.get(com.gulaev.repository.Audience.class,id);
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

    Session session = sessionFactory.getCurrentSession();
    session.delete(session.get(Audience.class,id));

//    jdbcTemplate.update("DELETE FROM audiences WHERE audience_id=?", id);
//    log.debug("deleteById id = {}", id);
  }

  @Override
  public void update(int id, Audience audience) {

    Session session = sessionFactory.getCurrentSession();
    com.gulaev.repository.Audience audienceToByUpdated  = session.get(
        com.gulaev.repository.Audience.class, id);

      audienceToByUpdated.setAudienceNumber(audience.getAudienceNumber());
      audienceToByUpdated.setEmpty(audience.isEmpty());
      audienceToByUpdated.setFloor(audience.getFloor());
      audienceToByUpdated.setNumberOfSeats(audience.getNumberOfSeats());
      //    jdbcTemplate.update(
//        "UPDATE audiences SET audience_number=?, floor=?, number_of_seats=?, "
//            + "interactive_whiteboard=?, is_empty=? WHERE audience_id=?;",
//        audience.getAudienceNumber(),
//        audience.getFloor(),
//        audience.getNumberOfSeats(),
//        audience.isInteractiveWhiteboard(),
//        audience.isEmpty(),
//        id);
//    log.debug("update id = {}", id);
  }

  @Transactional(readOnly = false)
  public void addNewAudience(
      int audienceNumber,
      int floor,
      int numberOfSeats,
      boolean interactiveWhiteboard,
      boolean isEmpty) {

    com.gulaev.repository.Audience audience = new com.gulaev.repository.Audience();
    audience.setAudienceNumber(audienceNumber);
    audience.setFloor(floor);
    audience.setNumberOfSeats(numberOfSeats);
    audience.setInteractiveWhiteboard(interactiveWhiteboard);
    audience.setEmpty(isEmpty);

    Session session = sessionFactory.getCurrentSession();

    session.save(audience);


//    jdbcTemplate.update(
//        "INSERT INTO "
//            + "audiences(audience_number, floor, number_of_seats, interactive_whiteboard, is_empty)"
//            + " VALUES (?, ?, ?, ?, ?);",
//        audienceNumber,
//        floor,
//        numberOfSeats,
//        interactiveWhiteboard,
//        isEmpty);
//    log.debug("addNewAudience");
  }
}
