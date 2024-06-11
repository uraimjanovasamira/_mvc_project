package com.controller;

import com.Service.impl.GroupServiceImpl;
import com.model.Company;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.model.Group;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GroupController {

     private final GroupServiceImpl groupService;

     @ModelAttribute("groupList")
     public List<Group>groupList(){
         return groupService.findAll();
     }


    @GetMapping("/add")
    public String addGroup(Model model) {
        model.addAttribute("group", new Group());
        return "group/save";
    }

    @PostMapping("/save")
    public String saveGroup(@ModelAttribute("group") Group group) {
        groupService.save(group);
        return "redirect:/user/find-all";
    }
    @GetMapping("/find-all")
    public String findAll(Model model) {
        List<Group> groupList = groupService.findAll();
        model.addAttribute("groupList", groupList);
        return "/group/get-all";
    }

    @GetMapping("/find_by_id")
    public String findById(@RequestParam("group_id") Long id, Model model) {
        model.addAttribute("only_group", groupService.findById(id));
        return "group/findById";
    }

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Long id, Model model) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return "group/update";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Group group, @PathVariable("id") long id) {
        groupService.update(id, group);
        return "redirect:group/find-all";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        groupService.delete(id);
        return "redirect:/group/find-all";
    }
}

