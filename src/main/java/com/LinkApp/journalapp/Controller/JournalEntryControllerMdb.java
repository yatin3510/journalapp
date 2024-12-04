package com.LinkApp.journalapp.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<JournalEntry>>getAll(){
        List<JournalEntry>all=journalEntryService.getall();
        if(all!=null && !all.isEmpty())
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // RequestBody will take data from body and convert it into json
    @PostMapping
    public ResponseEntity<JournalEntry> createentry(@RequestBody JournalEntry MyEntry){
        try{
            MyEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(MyEntry);
            return new ResponseEntity<>(MyEntry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Getting data for particular id
    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> GetJournalEntryById(@PathVariable ObjectId myid){
        Optional<JournalEntry> journalEntry=journalEntryService.findbyid(myid);
        if(journalEntry.isPresent())
        {
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> DelJournalEntryById(@PathVariable ObjectId myid){
        journalEntryService.deletebyid(myid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("id/{myid}")
    public ResponseEntity<?> UpdateJournalEntryById(@PathVariable ObjectId myid,@RequestBody JournalEntry newEntry){
        JournalEntry old=journalEntryService.findbyid(myid).orElse(null);
        if(old!=null)
        {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("")? newEntry.getContent() : old.getContent()) ;
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
