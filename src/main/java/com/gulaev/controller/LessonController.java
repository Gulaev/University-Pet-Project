package com.gulaev.controller;

import com.gulaev.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lesson")
public class LessonController {

  private LessonService lessonService;

  @Autowired
  public LessonController(LessonService lessonService) {
    this.lessonService = lessonService;
  }

  @GetMapping()
  public String getAllLesson(Model model) {
    model.addAttribute("lessonService", lessonService);
    model.addAttribute("lessons", lessonService.getAllLessons());
    return "lesson/all_lesson";
  }

  @GetMapping("/{id}")
  public String getLessonById(@PathVariable int id, Model model) {
    model.addAttribute("lesson", lessonService.getLessonById(id));
    return "lesson/lesson";
  }
}
