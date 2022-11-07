package com.gulaev.controller;


import com.gulaev.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/group")
public class GroupController {

  GroupService groupService;

  @Autowired
  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  @GetMapping("/{id}")
  public String getGroupById(@PathVariable int id, Model model) {
    model.addAttribute("group", groupService.getGroupById(id));
    return "group/group";
  }
}
