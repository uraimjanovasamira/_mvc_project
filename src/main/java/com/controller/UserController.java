package com.controller;

import com.Service.impl.CourseServiceImpl;
import com.Service.impl.GroupServiceImpl;
import com.Service.impl.UserServiceImpl;
import com.model.Course;
import com.model.Group;
import com.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserServiceImpl userService;
    GroupServiceImpl groupService;
    CourseServiceImpl courseService;

    @ModelAttribute("courseList")
    public List<Course> courses() {
        return courseService.findAll();
    }
    @ModelAttribute("groupList")
    public List<Group> groups() {
        return groupService.findAll();
    }
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/user/save";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/user/find-all";
    }


    @GetMapping("/find-all")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user/get-all";
    }

    @GetMapping("/find_by_id")
    public String findById(@RequestParam("user_id") Long id, Model model) {
        model.addAttribute("only_user", userService.findById(id));
        return "user/findById";
    }

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:user/find-all";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user/find-all";
    }

}
