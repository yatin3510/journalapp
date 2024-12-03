package com.LinkApp.journalapp.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.LinkApp.journalapp.entity.JournalEntry;
import com.LinkApp.journalapp.repository.JournalEntryRepo;

@Component
public class JournalEntryService {
    

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntry myEntry){
            journalEntryRepo.save(myEntry);
    }

    public List<JournalEntry> getall(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findbyid(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deletebyid(ObjectId id){
        journalEntryRepo.deleteById(id);
    }
}
