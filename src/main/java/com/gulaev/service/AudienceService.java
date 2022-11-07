package com.gulaev.service;

import com.gulaev.dao.AudienceDao;
import com.gulaev.models.Audience;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class AudienceService {

  private final AudienceDao audienceDao;

//  @Autowired
//  public AudienceService(AudienceDao audienceDao) {
//    log.trace("initialization AudienceService");
//    this.audienceDao = audienceDao;
//  }

  public Audience getByAudienceId(int id) {
        return audienceDao.getById(id);
  }

  public List<Audience> getEmptyAudience() {
    log.info("getEmptyAudience");
    List<Audience> audiences =
        audienceDao.loadAll().stream()
            .filter(audience -> audience.isEmpty() == true)
            .collect(Collectors.toList());
    log.debug("returned audiences = {}", audiences);
    return audiences;
  }

  public void updateAudienceById(
      int id,
      int audienceNumber,
      int floor,
      int numberOfSeats,
      boolean interactiveWhiteboard,
      boolean isEmpty) {
    log.debug(
        "updateAudienceById id ={}, audienceNumber ={}, floor ={}, numberOfSeats ={}, interactiveWhiteboard ={}, isEmpty ={}",
        id,
        audienceNumber,
        floor,
        numberOfSeats,
        interactiveWhiteboard,
        isEmpty);
    Audience audience =
        new Audience(id, audienceNumber, floor, numberOfSeats, interactiveWhiteboard, isEmpty);
    audienceDao.update(id, audience);
    log.debug("AudienceDao.update id = {} audience = {}", id, audience);
  }
}
