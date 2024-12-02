package com.LinkApp.journalapp.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LinkApp.journalapp.entity.JournalEntry;

// RequestMapping will add mapping to the whole class
@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    
    private Map<Long,JournalEntry> journalentries= new HashMap<>();


    // Gettting all the mappings together
    @GetMapping
    public List<JournalEntry>getAll(){
        return new ArrayList<>(journalentries.values());
    }

    // RequestBody will take data from body and convert it into json
    @PostMapping
    public boolean createentry(@RequestBody JournalEntry MyEntry){
        journalentries.put(MyEntry.getId(),MyEntry);
        return true;
    }

    // Getting data for particular id
    @GetMapping("id/{myid}")
    public JournalEntry GetJournalEntryById(@PathVariable Long myid){
        return journalentries.get(myid);
    }

    @DeleteMapping("id/{myid}")
    public JournalEntry DelJournalEntryById(@PathVariable Long myid){
        return journalentries.remove(myid);
    }


    @PutMapping("id/{myid}")
    public JournalEntry UpdateJournalEntryById(@PathVariable Long myid,@RequestBody JournalEntry myEntry){
        return journalentries.put(myid,myEntry);
    }
        
}
