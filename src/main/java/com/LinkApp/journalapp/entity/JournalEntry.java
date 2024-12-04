package com.LinkApp.journalapp.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Document(collection = "journal_entries")
@Data   
public class JournalEntry {

    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;

    // public LocalDateTime getdate() {
    //     return date;
    // }

    // public void setDate(LocalDateTime date) {
    //     this.date = date;
    // }


    // public ObjectId getId() {
    //     return id;
    // }

    // // Setter for id
    // public void setId(ObjectId id) {
    //     this.id = id;
    // }

    // // Getter for title
    // public String getTitle() {
    //     return title;
    // }

    // // Setter for title
    // public void setTitle(String title) {
    //     this.title = title;
    // }

    // // Getter for content
    // public String getContent() {
    //     return content;
    // }

    // // Setter for content
    // public void setContent(String content) {
    //     this.content = content;
    // }

    
}
