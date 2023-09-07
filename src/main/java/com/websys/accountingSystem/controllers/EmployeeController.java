/*
package com.websys.accountingSystem.controllers;

import com.websys.accountingSystem.models.Employee;
import com.websys.accountingSystem.repo.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.websys.accountingSystem.models.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){this.employeeRepository=employeeRepository;}

    @GetMapping
    public String employeeController(Model model){
        model.addAttribute("employee",employeeRepository.findAll());
        model.addAttribute("newemployee",new Employee());
        return "employee";
    }

    @PostMapping
    public String addEmployee(@ModelAttribute("newemployee")  @Valid Employee employee, Errors errors){

        if(errors.hasErrors()){
            return "employee";
        }

        employeeRepository.save(employee);

        return "redirect:/employee";
    }

    @PostMapping("/{id}/edit")
    public String editById(@PathVariable("id") Long id, Model model){
        if(employeeRepository.findById(id).isPresent()){
            model.addAttribute("employee",employeeRepository.findById(id).get());
            return "editEmployee";
        }
        model.addAttribute("employee", new Employee());
        return "editEmployee";
    }

    @PostMapping("/{id}")
    public String updateById(@Valid Employee employee, Errors errors){

        if(errors.hasErrors()){
            return "editEmployee";
        }

        employeeRepository.save(employee);

        return "redirect:/employee";
    }

    @PostMapping("/{id}/remove")
    public String deleteById(@PathVariable("id") Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employeeRepository.delete(employee);

        return "redirect:/employee";
    }
}
*/
