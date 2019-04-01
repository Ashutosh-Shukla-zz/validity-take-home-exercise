package com.validity.takehometest.controller;

import java.util.List;

import com.validity.takehometest.model.Person;
import com.validity.takehometest.service.FindDuplicatesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FindDuplicatesController {

    @Autowired
    private FindDuplicatesService findDuplicatesService;

    private static final Logger logger = LoggerFactory.getLogger(FindDuplicatesController.class);

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @PostMapping("/upload")
    public String processCSV(@RequestParam("csvFile") MultipartFile csvFile, Model model) throws Exception {
        logger.info("Reading file and checking for duplicates");

        List<List<Person>> separateList;
        try {
            separateList = this.findDuplicatesService.processDataFromCSV(csvFile);
        } catch (Exception e) {
            logger.error(e.toString());
            throw new RuntimeException("An error occured while processing the CSV.");
        }

        model.addAttribute("listOfUsers", separateList);
        return "result";

    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handle(RuntimeException ex)
    {
        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", ex);
        return model;
    }

    

    

}
