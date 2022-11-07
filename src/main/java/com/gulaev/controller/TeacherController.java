package com.gulaev.controller;

import com.gulaev.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

  private TeacherService teacherService;

  @Autowired
  public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
  }

  @GetMapping("/{id}")
  public String getTeacherById(@PathVariable int id, Model model) {
    model.addAttribute("teacher", teacherService.getTeacherById(id));
    return "teacher/teacher";
  }
}
