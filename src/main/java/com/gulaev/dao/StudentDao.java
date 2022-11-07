package com.gulaev.dao;

import com.gulaev.exception.DaoLayerException;
import com.gulaev.models.Student;
import com.gulaev.models.mappers.StudentMapper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class StudentDao implements Dao<Student> {

  private JdbcTemplate jdbcTemplate;
  private StudentMapper studentMapper;

  @Autowired
  public StudentDao(JdbcTemplate jdbcTemplate, StudentMapper studentMapper) {
    this.jdbcTemplate = jdbcTemplate;
    this.studentMapper = studentMapper;
    log.trace("Initialization StudentDao");
  }

  @Override
  public Student getById(int id) {
    String sql1 = "SELECT * FROM students WHERE student_id = ?";
    log.debug("getById id = {}", id);
    try {
      return jdbcTemplate.query(sql1, new Object[] {id}, studentMapper).stream()
          .findAny()
          .orElseThrow(DaoLayerException::new);
    } catch (DaoLayerException e) {
      log.error("Student with this Id do not Exist id= {}", id, e);
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Student> loadAll() {
    String sql1 = "SELECT * FROM students;";
    log.debug("loadAll by students");
    return jdbcTemplate.query(sql1, studentMapper);
  }

  @Override
  public void deleteById(int id) {
    jdbcTemplate.update("DELETE FROM students WHERE student_id=?", id);
    log.debug("deleteById id={}", id);
  }

  @Override
  public void update(int id, Student student) {
    jdbcTemplate.update(
        "UPDATE students SET first_name=?, last_name=?, group_id=? WHERE student_id=?;",
        student.getFirstName(),
        student.getLastName(),
        student.getGroupId(),
        id);
    log.debug("update id ={}", id);
  }

  public void addStudent(String firstName, String lastName, int groupId) {
    jdbcTemplate.update(
        "INSERT INTO students(first_name, last_name, group_id) VALUES (?, ?, ?);",
        firstName,
        lastName,
        groupId);
    log.debug("addStudent");
  }
}
