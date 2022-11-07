package com.gulaev.controller;

import com.gulaev.service.AudienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/audience")
public class AudienceController {

  private AudienceService audienceService;

  @Autowired
  public AudienceController(AudienceService audienceService) {
    this.audienceService = audienceService;
  }



  @GetMapping("/{id}")
  public String getAudienceById(@PathVariable int id, Model model) {
    model.addAttribute("audience", audienceService.getByAudienceId(id));
    return "audience/audience";
  }
}
