package com.mongo.db;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;

public class ConnectToMongoDB 
{
	public static void creatingMongoClient() 
	{
		//creating mongo client
		try (MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// creating credential
			MongoCredential mongoCredential = MongoCredential.createCredential("root", "java_mongodb_connection",
					"root".toCharArray());
			System.out.println("connectd to the data base successfully");

			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			System.out.println("mongo credential = " + mongoCredential);
			System.out.println("Database name = " + mongoDatabase.getName());

			// creating a collection
			mongoDatabase.createCollection("product");
			System.out.println("collection created successfully");
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//inserting data to database
	public static void insertOneDocument() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			//creating a phone document
			Document phoneDocument = new Document("productName","Phone")
					.append("description", "i phone 15")
					.append("price", 70000)
					.append("color", "white");
			
			//inserting phone data to document into the collection
			productCollection.insertOne(phoneDocument);
			System.out.println("Documnet inserted successfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void insertManyDocument() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			System.out.println("Database Name = "+mongoDatabase.getName());
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			//creating a phone document
			Document iphoneDocument = new Document("productName","I Phone")
					.append("description", "i phone 15")
					.append("price", 80000)
					.append("color", "black");
			Document samsungDocument = new Document("productName","Samsung")
					.append("description", "samsung galaxy")
					.append("price", 100000)
					.append("color", "green");
			Document oppoDocument = new Document("productName","Oppo")
					.append("description", "oppo f19")
					.append("price", 20000)
					.append("color", "blue");
			
			List<Document> documentsList = new ArrayList<Document>();
			documentsList.add(oppoDocument);
			documentsList.add(samsungDocument);
			documentsList.add(iphoneDocument);
			
			//Inserting multiple data to the database
			productCollection.insertMany(documentsList);
			System.out.println("Document inserted successfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void retrivingAllDocumnetFromDatabase() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			//getting the iterable object
			FindIterable<Document> findIterable = productCollection.find();
			
			//getting the iterator
			Iterator<Document> iterator = findIterable.iterator();
			while(iterator.hasNext()) {
				Document document = iterator.next();
				//System.out.println(document);
				//System.out.println(document.get("productName"));
				System.out.println("----------------------------------------------------------------");
				System.out.println(document.toJson(JsonWriterSettings.builder().indent(true).build()));

		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void updateOneDocumnet() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			productCollection.updateOne(Filters.eq("productName","Phone"),Updates.set("price", 8000));
			productCollection.updateOne(Filters.eq("productName","Phone"),Updates.set("color", "silver"));
			productCollection.updateOne(Filters.eq("productName","Phone"),Updates.set("description", "vivo phone"));
			productCollection.updateOne(Filters.eq("productName","Phone"),Updates.set("productName", "smart phone"));
			
			//after changing the product name
			productCollection.updateOne(Filters.eq("productName","smart phone"),Updates.set("color", "silver black"));
			productCollection.updateOne(Filters.eq("productName","smart phone"),Updates.set("price", 25000));

			System.out.println("Document updated succesfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateManyDocument() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			//It changes the price of all documents which have 25000 to 40000 
			productCollection.updateMany(Filters.eq("price",25000),Updates.set("price", 40000));
			System.out.println("Document updated succesfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readSpecificDocumnet() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			Document productDocument = productCollection.find(new Document("productName","Oppo")).first();	
			System.out.println("product Document = "+productDocument);
			System.out.println("Document read succesfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void readRangeOfDocuments() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			//Get The range of data as json format
			FindIterable<Document> findIterable = productCollection.find(Filters.gte("price", 100000));
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while(mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next().toJson());
			}
			
			System.out.println("-------------------------------------------");
			
			//Getting the range of documents as as list
			List<Document> productList = productCollection.find(Filters.gt("price", 10000)).into(new ArrayList<>());
			for(Document productDocument : productList) {
				System.out.println(productDocument.toJson());
			}
			
			System.out.println("--------------------------------------------");
			
			//get the range of documents using consumer which is a functional interface
			Consumer<Document> printConsumer = document-> System.out.println(document.toJson());
			productCollection.find(Filters.gt("price", 10000)).forEach(printConsumer);
			System.out.println("Document read range succesfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void findOneAndUpdate() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			//return the old version of the document before the update.
			FindOneAndUpdateOptions andUpdateOptions = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER);
			Document newProductDocument = productCollection.findOneAndUpdate(Filters.eq("productName","Samsung"),Updates.set("price", 50000),andUpdateOptions);
			System.out.println(newProductDocument);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void deleteOneDocument() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			DeleteResult deleteResult = productCollection.deleteOne(Filters.eq("productName","smart phone"));
			System.out.println(deleteResult.getDeletedCount());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteDocument() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			DeleteResult deleteResult = productCollection.deleteMany(Filters.eq("price",80000));
			System.out.println(deleteResult.getDeletedCount());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void findOneDeleteDocument() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//Retrieving a product collection
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");
			System.out.println("product collection selected successfully");
			
			Document document = productCollection.findOneAndDelete(Filters.eq("productName","Oppo"));
			//System.out.println(document.toJson());
			System.out.println(document.toJson(JsonWriterSettings.builder().indent(true).build()));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void getdatabases() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			//get all the databases
			List<Document> database = mongoClient.listDatabases().into(new ArrayList<>());
			database.forEach(db->System.out.println(db.toJson(JsonWriterSettings.builder().indent(true).build())));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void listOfCollection() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("java_mongodb_connection");
			
			//retriving list of collections
			MongoIterable<String> iterable = mongoDatabase.listCollectionNames();
			for(String c : iterable) {
				System.out.println(c);
			}
						
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void dropCollection() {
		try(MongoClient mongoClient = new MongoClient("localhost", 27017))
		{
			// Accessing the database
			MongoDatabase mongoDatabase = mongoClient.getDatabase("first_db");
			
			MongoCollection<Document> productCollection = mongoDatabase.getCollection("product");	
			productCollection.drop();
			System.out.println("collection removed successfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		creatingMongoClient();
		
		//insertOneDocument();
		
		//insertManyDocument();
		
		//retrivingAllDocumnetFromDatabase();
		
		//updateOneDocumnet();
		
		//updateManyDocument();
		
		//readSpecificDocumnet();
		
		//readRangeOfDocuments();
		
		//findOneAndUpdate();
		
		//deleteOneDocument();
		
		//deleteDocument();
		
		//findOneDeleteDocument();
		
		//insertManyDocument();
		
		//retrivingAllDocumnetFromDatabase();
		
		//getdatabases();
		
		//listOfCollection();
		
		//dropCollection();
		
	}

}
