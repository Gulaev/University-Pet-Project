package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.Teacher;
import com.gulaev.models.mappers.TeacherMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class TeacherDao implements Dao<Teacher> {

  private JdbcTemplate jdbcTemplate;
  private TeacherMapper teacherMapper;

  @Autowired
  public TeacherDao(JdbcTemplate jdbcTemplate, TeacherMapper teacherMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.teacherMapper = teacherMapper;
    log.trace("Initialization TeacherDao");
  }

  @Override
  public Teacher getById(int id) {
    String sql1 = "SELECT * FROM teachers WHERE teacher_id = ?;";
    log.debug("getById id = {}", id);
    try {
      return jdbcTemplate.query(sql1, new Object[] {id}, teacherMapper).stream()
          .findAny()
          .orElseThrow(DaoLayerException::new);
    } catch (DaoLayerException e) {
      log.error("Teacher with this Id does not exist id= {}", id, e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Teacher> loadAll() {
    String sql1 = "SELECT * FROM teachers;";
    log.debug("loadAll by teacher");
    return jdbcTemplate.query(sql1, teacherMapper);
  }

  @Override
  public void update(int id, Teacher teacher) {
    jdbcTemplate.update(
        "UPDATE teachers SET first_name=?, last_name=? WHERE teacher_id=?",
        teacher.getFirstName(),
        teacher.getLastName(),
        id);
    log.debug("update id = {}", id);
  }

  @Override
  public void deleteById(int id) {
    jdbcTemplate.update("DELETE FROM teachers WHERE teacher_id=?", id);
    log.debug("deleteById id={}", id);
  }

  public void addNewTeacher(String firstName, String lastName) {
    jdbcTemplate.update(
        "INSERT INTO teachers(first_name, last_name) VALUES (?,?)", firstName, lastName);
    log.debug("update");
  }
}
