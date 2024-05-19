package com.controller;

import com.Service.impl.StudentServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {

    StudentServiceImpl studentService;

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/save";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:student/find-all";
    }

    @GetMapping("/find-all")
    public String findAll(Model model) {
        List<Student> studentList = studentService.findAll();
        model.addAttribute("studentList", studentList);
        return "student/get-all";
    }

    //найти по айди
    @GetMapping("/find_by_id")
    public String findById(@RequestParam("student_id") Long id, Model model) {
        model.addAttribute("only_student", studentService.findById(id));
        return "student/findById";
    }

    //обновить по айди
    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Long id, Model model) {
        Student student= studentService.findById(id);
        model.addAttribute("student", student);
        return "student/update";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Student student, @PathVariable long id) {
        studentService.update(id, student);
        return "redirect:student/find-all";
    }

    //удалить по айди
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        studentService.delete(id);
        return "redirect:/student/find-all";
    }

}
