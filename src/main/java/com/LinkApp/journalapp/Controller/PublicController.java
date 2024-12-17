package com.LinkApp.journalapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LinkApp.journalapp.Service.UserService;
import com.LinkApp.journalapp.entity.User;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService UserService;
    
    @GetMapping("/health-check")
    public String healthcheck(){
        return "ok";
    }
    
    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        UserService.saveEntry(user);
    }

}
