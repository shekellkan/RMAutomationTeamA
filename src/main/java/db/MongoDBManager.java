package db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/7/15
 * Time: 11:13 AM
 */
public class MongoDBManager {
    private static MongoDBManager instance;
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDBManager getInstance(){
        if(instance==null)
        {
            instance = new MongoDBManager();
            mongoClient = new MongoClient("172.20.208.194",27017);
            database = mongoClient.getDatabase("roommanager");
        }

        return instance;
    }

    public MongoCollection getCollection(String collectionName){
        return database.getCollection(collectionName);
    }
    public void close(){
        mongoClient.close();
    }
}
