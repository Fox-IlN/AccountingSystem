package com.websys.accountingSystem.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectoryController {

    @GetMapping("/directory")
    public String directoryMain(Model model){
        return "directory-main";
    }

    @GetMapping("/search")
    public String search() {return "search";}
}
