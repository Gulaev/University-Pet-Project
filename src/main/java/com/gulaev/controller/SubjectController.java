package com.gulaev.controller;

import com.gulaev.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subject")
public class SubjectController {

  private SubjectService subjectService;

  @Autowired
  public SubjectController(SubjectService subjectService) {
    this.subjectService = subjectService;
  }

  @GetMapping("/{id}")
  public String getSubjectById(@PathVariable int id, Model model) {
    model.addAttribute("subject", subjectService.getSubjectById(id));
    return "subject/subject";
  }
}
