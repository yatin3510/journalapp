package com.LinkApp.journalapp.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.LinkApp.journalapp.entity.User;

public interface UserRepo extends MongoRepository<User,ObjectId> {

    User findByUsername(String username);
    
}
