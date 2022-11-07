package com.gulaev.controller;

import com.gulaev.service.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/speciality")
public class SpecialityController {

  SpecialityService specialityService;

  @Autowired
  public SpecialityController(SpecialityService specialityService) {
    this.specialityService = specialityService;
  }

  @GetMapping("/{id}")
  public String getSpecialityById(@PathVariable int id, Model model) {
    model.addAttribute("speciality", specialityService.getSpecialityById(id));
    return "speciality/speciality";
  }
}
