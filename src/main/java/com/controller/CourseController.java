package com.controller;

import com.Service.impl.CourseServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.model.Course;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    CourseServiceImpl courseService;


    @GetMapping("/add")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "course/save";
    }

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        courseService.save(course);
        return "redirect:course/find-all";
    }

    @GetMapping("/find-all")
    public String findAll(Model model) {
        List<Course> courseList = courseService.findAll();
        model.addAttribute("courseList", courseList);
        return "course/get-all";
    }

    @GetMapping("/find_by_id")
    public String findById(@RequestParam("course_id") Long id, Model model) {
        model.addAttribute("only_course", courseService.findById(id));
        return "course/findById";
    }

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Long id, Model model) {
        Course course = courseService.findById(id);
        model.addAttribute("course", course);
        return "course/update";
    }

    @PutMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Course course, @PathVariable long id) {
        courseService.update(id, course);
        return "redirect:course/find-all";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        courseService.delete(id);
        return "redirect:/course/find-all";
    }
}
