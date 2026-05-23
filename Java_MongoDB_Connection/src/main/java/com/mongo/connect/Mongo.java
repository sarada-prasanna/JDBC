package com.mongo.connect;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;

public class Mongo {
	public static void main(String[] args) {
		try(MongoClient mongoClient = new MongoClient("localhost",27017)){
			MongoCredential mongoCredential = MongoCredential.createCredential("root","testjava","root".toCharArray());
			System.out.println("Database connected successfully");
			
			MongoDatabase mongoDatabase = mongoClient.getDatabase("testjava");
			System.out.println("mongo credential "+mongoCredential);
			System.out.println("database name"+mongoDatabase.getName());
			
			mongoDatabase.createCollection("java");
			System.out.println("Collection created successfully");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
