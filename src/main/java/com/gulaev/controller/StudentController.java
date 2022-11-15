package com.gulaev.controller;

import com.gulaev.domain.Timetable;
import com.gulaev.models.Student;
import com.gulaev.service.LessonService;
import com.gulaev.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.StringMatcher.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/student")
public class StudentController {

  private StudentService studentService;
  private LessonService lessonService;


  @Autowired
  public StudentController(StudentService studentService, LessonService lessonService) {
    this.studentService = studentService;
    this.lessonService = lessonService;
  }

  @GetMapping()
  public String getAllStudent(Model model) {
    log.debug("getAllStudent URL = /student");
    model.addAttribute("students", studentService.getAllStudent());
    log.debug("all students = {}", studentService.getAllStudent());
    return "student/all_students";
  }
  @GetMapping("/new")
  public String newStudent(Model model){
    log.debug("addNewStudent model = {}", model);
    model.addAttribute("student", new Student());
    log.debug("New Student Back = {}", model.getAttribute("student"));
    return "student/new_student";
  }

  @PostMapping()
  public String create(@ModelAttribute("student") Student student) {
    log.debug("create student = {}",student);
    studentService.createNewStudent(student);
    log.debug("created Student");
    return "redirect:/student";
  }

  @GetMapping("/{id}")
  public String getStudentById(@PathVariable() int id, Model model) {
    log.debug("getStudentById id = {}", id);
    model.addAttribute("student", studentService.getStudentById(id));
    log.debug("model.addAttribute = {}", model.getAttribute("student"));
    return "student/student";
  }

  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable int id) {
    log.debug("edit Student By Id = {}", id);
    model.addAttribute("student", studentService.getStudentById(id));
    log.debug("model.addAttribute = {}", model.getAttribute("student"));
    return "student/edit_student";
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public String update(@ModelAttribute("student")Student student, @PathVariable("id") int id) {
    log.debug("update Student student = {}", student);
    studentService.updateStudentById(id, student.getFirstName(), student.getLastName(),
        student.getGroupId());
    log.debug("studentService.updateStudentById Student = {}", student);
    return "redirect:/student";
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String delete(@PathVariable("id") int id) {
    log.debug("delete Student By Id id = {}", id);
    studentService.deleteStudentById(id);
    return "redirect:/student";
  }

  @GetMapping("/{id}/lessons")
  public String getLessonForThisMount(Model model, @PathVariable int id){
    log.debug("getLessonForThisMount studentId = {}", id);
    model.addAttribute("lessonService", lessonService);
    model.addAttribute("lessons", studentService.getAllLessonForStudentOneMonthFromTheCurrentTimeByStudentId(id));
    log.debug("model.addAttribute = {}", model.getAttribute("lessons"));
    return "student/lessons";
  }
}
