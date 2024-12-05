package com.LinkApp.journalapp.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.LinkApp.journalapp.entity.JournalEntry;
import com.LinkApp.journalapp.entity.User;
import com.LinkApp.journalapp.repository.JournalEntryRepo;
import com.LinkApp.journalapp.repository.UserRepo;

@Component
public class JournalEntryService {
    

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry myEntry,String username){
            User user=userService.findbyusername(username);
            myEntry.setDate(LocalDateTime.now());
            JournalEntry journalEntry=journalEntryRepo.save(myEntry);
            user.getJournalEntries().add(journalEntry);
            userService.saveEntry(user);
    }

    public void saveEntry(JournalEntry myEntry){
        journalEntryRepo.save(myEntry);       
}

    public List<JournalEntry> getall(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findbyid(ObjectId id){
        return journalEntryRepo.findById(id);
    }

    public void deletebyid(ObjectId id,String username){
        User user=userService.findbyusername(username);
        user.getJournalEntries().removeIf(x->x.getId().equals(id));
        userService.saveEntry(user);
        journalEntryRepo.deleteById(id);
    }
}
