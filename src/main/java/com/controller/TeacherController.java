package com.controller;

import com.Service.impl.TeacherServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.model.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherController {

    TeacherServiceImpl teacherService;

    @GetMapping("/add")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teacher/save";
    }

    @PostMapping("/save")
    public String saveTeacher(@ModelAttribute("teacher") Teacher teacher ) {
        teacherService.save(teacher);
        return "redirect:teacher/find-all";
    }

    @GetMapping("/find-all")
    public String findAll(Model model) {
        List<Teacher> teacherList = teacherService.findAll();
        model.addAttribute("teacherList", teacherList);
        return "teacher/get-all";
    }

    @GetMapping("/find_by_id")
    public String findById(@RequestParam("teacher_id") Long id, Model model) {
        model.addAttribute("only_teacher", teacherService.findById(id));
        return "teacher/findById";
    }

    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Long id, Model model) {
        Teacher teacher= teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teacher/update";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Teacher teacher, @PathVariable long id) {
        teacherService.update(id, teacher);
        return "redirect:teacher/find-all";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        teacherService.delete(id);
        return "redirect:/teacher/find-all";
    }

}
