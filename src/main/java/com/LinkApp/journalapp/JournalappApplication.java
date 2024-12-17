package com.LinkApp.journalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalappApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalappApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager forTransactions(MongoDatabaseFactory dbfactory){
		return new MongoTransactionManager(dbfactory);
	}

	
}
