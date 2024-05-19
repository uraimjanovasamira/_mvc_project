package com.controller;

import com.Service.impl.CompanyService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.model.Company;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/company")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyController {

    CompanyService companyService;

    @GetMapping("/add")
    public String addCompany(Model model) {
        model.addAttribute("company", new Company());
        return "company/save";
    }

    @PostMapping("/save")
    public String saveCompany(@ModelAttribute("company") Company company) {
        companyService.save(company);
        return "redirect:/company/find-all";
    }

    @GetMapping("/find-all")
    public String findAll(Model model) {
        List<Company> companyList = companyService.findAll();
        model.addAttribute("companyList", companyList);
        return "company/get-all";
    }

    //найти по айди
    @GetMapping("/find_by_id")
    public String findById(@RequestParam("company_id") Long id, Model model) {
        model.addAttribute("only_company", companyService.findById(id));
        return "company/findById";
    }

    //обновить по айди
    @GetMapping("/update/{id}")
    public String updateById(@PathVariable("id") Long id, Model model) {
        Company company = companyService.findById(id);
        model.addAttribute("company", company);
        return "company/update";
    }

    @PostMapping("/merge_update/{id}")
    public String mergeUpdate(@ModelAttribute Company company, @PathVariable long id) {
        companyService.update(id, company);
        return "redirect:/company/find-all";
    }

    //удалить по айди
    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        companyService.delete(id);
        return "redirect:/company/find-all";
    }

}
