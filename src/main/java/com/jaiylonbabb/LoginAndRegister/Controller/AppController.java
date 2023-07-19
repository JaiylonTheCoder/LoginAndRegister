package com.jaiylonbabb.LoginAndRegister.Controller;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/")
    public String goHome() {
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/error")
//    public String errorPage(){
//        return "error";
//    }
    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
