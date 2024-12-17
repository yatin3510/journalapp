package com.LinkApp.journalapp.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.LinkApp.journalapp.entity.User;
import com.LinkApp.journalapp.repository.UserRepo;

@Component
public class UserService {

    @Autowired
    private UserRepo UserRepo;

    private static final PasswordEncoder passwordencoder=new BCryptPasswordEncoder();
    public void saveEntry(User user){
        user.setPassword(passwordencoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        UserRepo.save(user);
        
    }



    public List<User> getall(){
        return UserRepo.findAll();
    }

    public Optional<User> findbyid(ObjectId id){
        return UserRepo.findById(id);
    }

    public void deletebyid(ObjectId id){
        UserRepo.deleteById(id);
    }

    public User findbyusername(String username){
        return UserRepo.findByUsername(username);
    }
    
}
