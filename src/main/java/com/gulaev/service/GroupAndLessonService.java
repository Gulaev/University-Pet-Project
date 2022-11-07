package com.gulaev.service;

import com.gulaev.dao.GroupAndLessonDao;
import com.gulaev.dao.GroupDao;
import com.gulaev.dao.LessonDao;
import com.gulaev.models.GroupAndLesson;
import com.gulaev.models.Lesson;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class GroupAndLessonService {

  private GroupAndLessonDao groupAndLessonDao;
  private GroupDao groupDao;
  private LessonDao lessonDao;

//  @Autowired
//  public GroupAndLessonService(
//      GroupAndLessonDao groupAndLessonDao, GroupDao groupDao, LessonDao lessonDao) {
//    this.groupAndLessonDao = groupAndLessonDao;
//    this.groupDao = groupDao;
//    this.lessonDao = lessonDao;
//    log.trace("Initialization GroupAndLessonService");
//  }

  public List<Lesson> getAllLessonsByOneGroup(int groupId) {
    log.debug("getAllLessonsByOneGroup groupId = {}",groupId);
    List<Integer> lessonsIds = new ArrayList<>();
    groupAndLessonDao.loadAll().stream()
        .filter(g -> g.getGroupId().equals(groupId))
        .collect(Collectors.toList())
        .forEach(groupAndLesson -> lessonsIds.add(groupAndLesson.getLessonId()));

    List<Lesson> allLessons = lessonDao.loadAll();
    List<Lesson> lessonsOneGroup = new ArrayList<>();

    for (Integer lessonId : lessonsIds) {
      for (Lesson lesson : allLessons) {
        if (lessonId.equals(lesson.getLessonId())) {
          lessonsOneGroup.add(lesson);
        } else {
          continue;
        }
      }
    }
    log.debug("returned lessonsOneGroup = {}", lessonsOneGroup);
    return lessonsOneGroup;
  }

  public void updateGroupAndLessonByGroupId(int id, int lessonId) {
    log.debug("updateGroupAndLessonByGroupId id = {}, lessonId = {}", id, lessonId);
    GroupAndLesson groupAndLesson = new GroupAndLesson(id, lessonId);
    groupAndLessonDao.update(id, groupAndLesson);
    log.debug("GroupAndLessonDao.update id = {} groupAndLesson = {}", id, groupAndLesson);
  }
}
