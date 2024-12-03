package com.LinkApp.journalapp.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LinkApp.journalapp.Service.JournalEntryService;
import com.LinkApp.journalapp.entity.JournalEntry;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerMdb {
    

    @Autowired
    private JournalEntryService journalEntryService;

    // Gettting all the mappings together
    @GetMapping
    public List<JournalEntry>getAll(){
        return journalEntryService.getall();
    }

    // RequestBody will take data from body and convert it into json
    @PostMapping
    public JournalEntry createentry(@RequestBody JournalEntry MyEntry){
        MyEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(MyEntry);
        return MyEntry;
    }

    // Getting data for particular id
    @GetMapping("id/{myid}")
    public JournalEntry GetJournalEntryById(@PathVariable ObjectId myid){
        return journalEntryService.findbyid(myid).orElse(null);
    }

    @DeleteMapping("id/{myid}")
    public boolean DelJournalEntryById(@PathVariable ObjectId myid){
        journalEntryService.deletebyid(myid);
        return true;
    }


    @PutMapping("id/{myid}")
    public JournalEntry UpdateJournalEntryById(@PathVariable ObjectId myid,@RequestBody JournalEntry newEntry){
        JournalEntry old=journalEntryService.findbyid(myid).orElse(null);
        if(old!=null)
        {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("")? newEntry.getContent() : old.getContent()) ;
        }
        journalEntryService.saveEntry(old);

        return null;
    }
    
}
