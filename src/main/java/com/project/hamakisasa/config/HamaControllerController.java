package com.project.hamakisasa.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HamaControllerController {

    @GetMapping("/")
    public String home() {
        return "home";
    }
    @GetMapping("/admin_dashboard")
    public String admin_dashboard() {
        return "admin_dashboard";
    }
    @GetMapping("/landlord_dashboard")
    public String landlord_dashboard() {
        return "landlord_dashboard";
    }
    @GetMapping("/user_dashboard")
    public String user_dashboard() {
        return "user_dashboard";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/fragments/{page}")
    public String loadPage(@PathVariable String page) {
        return "fragments/" + page;
    }
}
