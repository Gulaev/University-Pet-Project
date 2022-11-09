package com.gulaev.controller;

import com.gulaev.models.Student;
import com.gulaev.service.StudentService;
import lombok.extern.slf4j.Slf4j;
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

  @Autowired
  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping()
  public String getAllStudent(Model model) {
    log.debug("getAllStudent URL = /student");
    model.addAttribute("students", studentService.getAllStudent());
    return "student/all_students";
  }
  @GetMapping("/new")
  public String newStudent(Model model){
    log.debug("addNewStudent model = {}", model);
    model.addAttribute("student", new Student());
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
    model.addAttribute("student", studentService.getStudentById(id));
    return "student/student";
  }

  @GetMapping("/{id}/edit")
  public String edit(Model model, @PathVariable int id) {
    model.addAttribute("student", studentService.getStudentById(id));
    return "student/edit_student";
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
  public String update(@ModelAttribute("student")Student student, @PathVariable("id") int id) {
    studentService.updateStudentById(id, student.getFirstName(), student.getLastName(),
        student.getGroupId());
    return "redirect:/student";
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String delete(@PathVariable("id") int id) {
    studentService.deleteStudentById(id);
    return "redirect:/student";
  }
}
