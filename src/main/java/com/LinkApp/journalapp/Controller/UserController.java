package com.LinkApp.journalapp.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LinkApp.journalapp.Service.UserService;
import com.LinkApp.journalapp.entity.User;
import com.LinkApp.journalapp.repository.UserRepo;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService UserService;

    @GetMapping
    public List<User> getAllJournalEntriesOfUser(){
        return UserService.getall();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        UserService.saveEntry(user);
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateMapping(@RequestBody User user,@PathVariable String username){
        User userIndb=UserService.findbyusername(username);
        if(userIndb!=null){
            userIndb.setUsername(user.getUsername());
            userIndb.setPassword(user.getPassword());
            UserService.saveEntry(userIndb);
            return new ResponseEntity<>(userIndb,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
