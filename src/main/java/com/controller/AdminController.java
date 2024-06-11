package com.controller;

import com.Service.impl.UserServiceImpl;
import com.model.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminController {
    UserServiceImpl userService;

    @GetMapping("/add")
    public String addCompany(Model model) {
        model.addAttribute("admin", new User());
        return "admin/admin";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("admin") User user) {
        userService.save(user);
        return "redirect:/admin/site/look";
    }

    //обновить по айди
    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute User user, @PathVariable("id") long id) {
        userService.update(id, user);
        return "redirect:/user/find-all";
    }

    //удалить по айди
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user/find-all";
    }



}
