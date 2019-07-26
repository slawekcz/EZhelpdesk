package pl.coderslab.ezhelpdesk.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController implements ErrorController {

    @RequestMapping(path = "/error")
    public ModelAndView showError() {
        return new ModelAndView("error");
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}