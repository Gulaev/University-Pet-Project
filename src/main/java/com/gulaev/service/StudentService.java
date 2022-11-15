package com.gulaev.service;

import com.gulaev.dao.GroupAndLessonDao;
import com.gulaev.dao.GroupDao;
import com.gulaev.dao.LessonDao;
import com.gulaev.dao.StudentDao;
import com.gulaev.models.Group;
import com.gulaev.models.Lesson;
import com.gulaev.models.Student;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class StudentService {

  private StudentDao studentDao;
  private GroupDao groupDao;
  private GroupAndLessonDao groupAndLessonDao;
  private LessonDao lessonDao;
  private GroupAndLessonService groupAndLessonService;

  public Student getStudentById(int id) {
    log.debug("getStudentById = {}", id);
    Student student = studentDao.getById(id);
    log.debug("returned student = {}", student);
    return student;
  }
  public List<Lesson> getAllLessonForStudentOneMonthFromTheCurrentTimeByStudentId(int studentId) {
    log.debug("getAllLessonForStudentOneMonthFromTheCurrentTimeByStudentId id = {}", studentId);
    Timestamp timeNow = new Timestamp(System.currentTimeMillis());
    Timestamp timeAfterNowOneMonth = new Timestamp(System.currentTimeMillis());
    timeAfterNowOneMonth.setMonth(timeNow.getMonth()+1);
    List<Lesson> lessonsForOneStudentByMount = getLessonForStudentByStudentId(studentId)
        .stream().filter(lesson -> lesson.getLessonStart().after(timeNow))
        .filter(l -> l.getLessonStart().before(timeAfterNowOneMonth))
        .collect(Collectors.toList());
    log.debug("sorting finish = {}", lessonsForOneStudentByMount);
    return lessonsForOneStudentByMount;
  }

  public List<Student> getAllStudent() {
    log.debug("getAllStudent");
    List<Student> students = studentDao.loadAll();
    log.debug("Load all students = {}", students);
    return students;
  }

  public List<Student> getStudentByGroups(int groupId) {
    log.debug("getStudentByGroups by groupId = {}", groupId);
    List<Student> allStudents = studentDao.loadAll();
    List<Group> groupsByGroupID =
        groupDao.loadAll().stream()
            .filter(group -> group.getGroupID().equals(groupId))
            .collect(Collectors.toList());

    List<Student> studentsInGroupByRequest = new ArrayList<>();

    for (Student student : allStudents) {
      for (Group group : groupsByGroupID) {
        if (student.getGroupId().equals(group.getGroupID())) {
          studentsInGroupByRequest.add(student);
        } else {
          continue;
        }
      }
    }
    log.debug("returned studentsInGroupByRequest = {}", studentsInGroupByRequest);
    return studentsInGroupByRequest;
  }

  public List<Student> getStudentByName(String name) {
    log.debug("getStudentsByName name = {}", name);
    List<Student> students = studentDao.loadAll().stream()
        .filter(student -> student.getFirstName().equals(name))
        .collect(Collectors.toList());
    log.debug("returned students = {}", students);
    return students;
  }

  public void addNewStudent(String firstName, String lastName, Integer groupId) {
    studentDao.addStudent(firstName, lastName, groupId);
    log.debug(
        "StudentDao.addStudent firstName = {} lastName = {} groupId = {}", firstName, lastName, groupId);
  }

  public void updateStudentById(int id, String firstName, String lastName, int groupId) {
    log.debug("updateStudentById id = {}, firstName = {}, lastName = {}, groupId = {}",id,
        firstName, lastName, groupId);
    Student student = new Student(id, firstName, lastName, groupId);
    studentDao.update(id, student);
    log.debug("StudentDao.update id = {} student = {}", id, student);
  }

  public List<Lesson> getLessonForStudentByStudentId(int id) {
    int groupOfStudent = studentDao.getById(id).getGroupId();
    log.debug("getLessonForStudentByStudentId id = {}", id);
    List<Lesson> allLessonsByOneGroup = groupAndLessonService.getAllLessonsByOneGroup(
        groupOfStudent);
    log.debug("returned allLessonsByOneGroup = {}", allLessonsByOneGroup);
    return allLessonsByOneGroup;
  }

  public void createNewStudent(Student student) {
    log.debug("createNewStudent() student = {}", student);
    studentDao.addStudent(student.getFirstName(), student.getLastName(),student.getGroupId());
    log.debug("studentDao.addNewStudent() ");
  }

  public void deleteStudentById(int id) {
    log.debug("deleteStudentById()");
    studentDao.deleteById(id);
    log.debug("deleteById");
  }
}
