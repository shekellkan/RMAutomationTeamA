package db;

import com.mongodb.BasicDBObject;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.apache.log4j.Logger;
import org.bson.Document;
import java.util.ArrayList;

/**
 * Created by ArielWagner on 10/12/2015.
 */
public class DBMethods {

    final static Logger logger = Logger.getLogger(DBMethods.class);

    public DBMethods() {
        logger.info("MongoDB methods");
    }

    /**
     * This method allows filter rooms by a criteria
     * @param criteria
     * @return an ArrayList of String
     */
    public ArrayList<String> filterRoomsByCriteria(String criteria) {
        ArrayList<String> roomsList = new ArrayList<String>();
        MongoCollection<Document> roomsCollation = MongoDBManager.getInstance().getCollection("rooms");
        FindIterable<Document> rooms = roomsCollation
                .find(new Document(
                        "displayName", new BasicDBObject("$regex", criteria)));
        rooms.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                roomsList.add(document.get("displayName").toString());
            }
        });
        return roomsList;
    }
}
