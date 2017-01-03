package org.shop.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by vprasanna on 5/22/2016.
 */
@Controller
public class LoginController {

    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView handle() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "loginpage", method = RequestMethod.GET)
    public String loginpage() {
        return "redirect:/";
    }


}
