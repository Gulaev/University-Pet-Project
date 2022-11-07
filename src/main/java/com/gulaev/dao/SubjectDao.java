package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.Subject;
import com.gulaev.models.mappers.SubjectMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SubjectDao implements Dao<Subject> {

  private JdbcTemplate jdbcTemplate;
  private SubjectMapper subjectMapper;

  @Autowired
  public SubjectDao(JdbcTemplate jdbcTemplate, SubjectMapper subjectMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.subjectMapper = subjectMapper;
    log.trace("Initialization SubjectDao");
  }

  public Subject getById(int id) {
    String sql1 = "SELECT * FROM subjects WHERE subject_id = ?;";
    log.debug("getById id = {}", id);
    try {
      return jdbcTemplate.query(sql1, new Object[] {id}, subjectMapper).stream()
          .findAny()
          .orElseThrow(DaoLayerException::new);
    } catch (DaoLayerException e) {
      log.debug("Subject with this Id does not Exist id= {}", id, e);
      throw new RuntimeException(e);
    }
  }

  public List<Subject> loadAll() {
    String sql1 = "SELECT * FROM subjects;";
    log.debug("loadAll by subjects");
    return jdbcTemplate.query(sql1, subjectMapper);
  }

  @Override
  public void deleteById(int id) {
    jdbcTemplate.update("DELETE FROM subjects WHERE subject_id=?;", id);
    log.debug("deleteById id={};", id);
  }

  @Override
  public void update(int id, Subject subject) {
    jdbcTemplate.update(
        "UPDATE subjects SET subject_name=?, subjects_description=?, speciality_id=?, "
            + "subject_teacher_id=? WHERE subject_id=?",
        subject.getSubjectName(),
        subject.getSubjectDescription(),
        subject.getSpecialityId(),
        subject.getSubjectTeacherId(),
        id);
    log.debug("update id = {}", id);
  }

  public void addNewSubject(
      String subjectName, String subjectsDescription, int specialityId, int subjectTeacherId) {
    jdbcTemplate.update(
        "INSERT INTO subjects(subject_name, subjects_description, "
            + "speciality_id, subject_teacher_id) VALUES (?, ?, ?, ?)",
        subjectName,
        subjectsDescription,
        specialityId,
        subjectTeacherId);
    log.debug("addNewSubject");
  }
}
