package ru.university.examsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GettingStartedController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gettingStarted() {
        return "gettingstarted";
    }
}
