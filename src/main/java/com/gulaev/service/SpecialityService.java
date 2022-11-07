package com.gulaev.service;

import com.gulaev.dao.SpecialitiesAndTeachersDao;
import com.gulaev.dao.SpecialityDao;
import com.gulaev.dao.TeacherDao;
import com.gulaev.models.SpecialitiesAndTeachers;
import com.gulaev.models.Speciality;
import com.gulaev.models.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class SpecialityService {

  private SpecialityDao specialityDao;
  private SpecialitiesAndTeachersDao specialitiesAndTeachersDao;
  private TeacherDao teacherDao;

//  @Autowired
//  public SpecialityService(
//      SpecialityDao specialityDao,
//      SpecialitiesAndTeachersDao specialitiesAndTeachersDao,
//      TeacherDao teacherDao) {
//    this.specialityDao = specialityDao;
//    this.specialitiesAndTeachersDao = specialitiesAndTeachersDao;
//    this.teacherDao = teacherDao;
//    log.trace("Initialization SpecialityService");
//  }

  public Speciality getSpecialityById(int id) {
    log.debug("getSpecialityById id = {}", id);
    Speciality byId = specialityDao.getById(id);
    log.debug("returned speciality ={}", byId);
    return byId;
  }

  public List<Teacher> getAllTeacherBySpeciality(int specialityId) {
    log.debug("getAllTeacherBySpeciality specialityId = {}", specialityId);
    List<SpecialitiesAndTeachers> specialitiesAndTeachersById =
        specialitiesAndTeachersDao.loadAll().stream()
            .filter(spesial -> spesial.getSpecialityId().equals(specialityId))
            .collect(Collectors.toList());

    List<Teacher> teachers = teacherDao.loadAll();
    List<Teacher> teachersBySpeciality = new ArrayList<>();

    for (Teacher teacher : teachers) {
      for (SpecialitiesAndTeachers sp : specialitiesAndTeachersById) {
        if (sp.getTeacherId().equals(teacher.getTeacherId())) {
          teachersBySpeciality.add(teacher);
        } else {
          continue;
        }
      }
    }
    log.debug("returned teacherBySpeciality = {}", teachersBySpeciality);
    return teachersBySpeciality;
  }

  public void updateSpecialityById(int id, String nameOfSpeciality, int course, int audienceId) {
    log.debug("updateSpecialityByID id= {}, nameOfSpeciality ={}, course ={}, audienceId ={}",
        id, nameOfSpeciality, course, audienceId);
    Speciality speciality = new Speciality(id, nameOfSpeciality, course, audienceId);
    specialityDao.update(id, speciality);
    log.debug("SpecialityDao.update id = {} speciality = {}", id, speciality);
  }
}
