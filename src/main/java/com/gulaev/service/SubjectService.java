package com.gulaev.service;

import com.gulaev.dao.SpecialityDao;
import com.gulaev.dao.SubjectDao;
import com.gulaev.models.Subject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class SubjectService {

  private SubjectDao subjectDao;
  private SpecialityDao specialityDao;

  public Subject getSubjectById(int id) {
    log.debug("getSubjectById id = {}", id);
    Subject subject = subjectDao.getById(id);
    log.debug("returned subjectId = {}", id);
    return subject;
  }

  public void addNewSubject(
      String nameOfSubject,
      String subjectDescription,
      String specialityName,
      Integer subjectTeacherId) {
    log.debug("addNewSubject nameOfSubject = {}, subjectDescription = {}, specialityName = {}, subjectTeacherId = {}",
        nameOfSubject,
        subjectDescription,
        specialityName,
        subjectTeacherId);
    Integer specialityIdByName =
        specialityDao.loadAll().stream()
            .filter(speciality -> speciality.getNameOfSpeciality().equals(specialityName))
            .findAny()
            .orElse(null)
            .getSpecialityId();
    subjectDao.addNewSubject(
        nameOfSubject, subjectDescription, specialityIdByName, subjectTeacherId);
    log.debug(
        "SubjectDao.addNewSubject nameOfSubject = {} subjectDescription = {} specialityName = {} subjectTeacherId = {}",
        nameOfSubject,
        subjectDescription,
        specialityName,
        subjectTeacherId);
  }

  public void updateSubjectById(
      int id,
      String subjectName,
      String subjectDescription,
      int specialityId,
      int subjectTeacherId) {
    log.debug("updateSubjectById id = {}, subjectName = {}, subjectDescription = {}, specialityId = "
        + "{}, subjectTeacherId = {}", id, subjectName, subjectDescription, specialityId, subjectTeacherId);
    subjectDao.update(
        id, new Subject(id, subjectName, subjectDescription, specialityId, subjectTeacherId));
    log.debug(
        "SubjectDao.update id = {} subjectName = {} subjectDescription = {} specialityId = {} "
            + "subjectTeacherId = {}",
        id,
        subjectName,
        subjectDescription,
        specialityId,
        subjectTeacherId);
  }
}
