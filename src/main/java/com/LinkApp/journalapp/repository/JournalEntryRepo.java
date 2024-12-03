package com.LinkApp.journalapp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.LinkApp.journalapp.entity.JournalEntry;

public interface JournalEntryRepo extends MongoRepository<JournalEntry,ObjectId>{

    
} 
