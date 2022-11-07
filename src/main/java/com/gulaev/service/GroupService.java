package com.gulaev.service;

import com.gulaev.dao.GroupDao;
import com.gulaev.models.Group;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class GroupService {

  private final GroupDao groupDao;

//  @Autowired
//  public GroupService(GroupDao groupDao) {
//    this.groupDao = groupDao;
//    log.trace("Initialization GroupService");
//  }

  public Group getGroupById(int id) {
    log.debug("getGroupById id = {}", id);
    Group byId = groupDao.getById(id);
    log.debug("return group = {}", byId);
    return byId;
  }

  public void updateGroupById(int id, int course) {
    log.debug("updateGroupById in id= {} courseId = {}",id,course);
    Group group = new Group(id, course);
    groupDao.update(id, group);
    log.debug("GroupDao.update id = {} group = {}", id, group);
  }
}
