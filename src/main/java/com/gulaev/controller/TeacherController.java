package com.gulaev.controller;

import com.gulaev.service.LessonService;
import com.gulaev.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/teacher")
public class TeacherController {

  private TeacherService teacherService;
  private LessonService lessonService;

  @Autowired
  public TeacherController(TeacherService teacherService, LessonService lessonService) {
    this.teacherService = teacherService;
    this.lessonService = lessonService;
  }

  @GetMapping("/{id}")
  public String getTeacherById(@PathVariable int id, Model model) {
    model.addAttribute("teacher", teacherService.getTeacherById(id));
    return "teacher/teacher";
  }

  @GetMapping()
  public String getAllTeacher(Model model) {
    model.addAttribute("teachers", teacherService.loadAll());
    return "teacher/all_teachers";
  }

  @GetMapping("/{id}/lessons")
  public String allLessonForTeacherPerMouth(Model model, @PathVariable int id) {
    log.debug("allLessonForTeacherPerMouth id = {}", id);
    model.addAttribute("lessonService", lessonService);
    model.addAttribute("lessons",
        teacherService.getAllLessonForTeacherOneMonthFromTheCurrentTimeByStudentId(id));
    log.debug("model.addAttribute = {}", model.getAttribute("lessons"));
    return "teacher/lessons";
  }
}
