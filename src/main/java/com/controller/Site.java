package com.controller;

import com.Service.impl.CourseServiceImpl;
import com.Service.impl.GroupServiceImpl;
import com.model.Course;
import com.model.Group;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/site")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Site {
    CourseServiceImpl courseService;
    GroupServiceImpl groupService;

    @GetMapping("/look")
    public String siteLook() {
        return "/site/site";
    }
    @ModelAttribute("courseList")
    public List<Course>getCourseList(){
        return courseService.findAll();
    }

    @ModelAttribute("groupList")
    public List<Group>getGroupList(){
        return groupService.findAll();
    }

    @GetMapping("/user/find-all")
    public String siteStudent(){
        return "/user/get-all";
    }







}
