package db;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.apache.log4j.Logger;
import org.bson.Document;
import java.util.ArrayList;
import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.asList;
/**
 * User: Jean Carlo Rodriguez
 * Date: 12/11/15
 * Time: 11:05 AM
 */
public class DBRoomsMethods {
    MongoDBManager mongoDBManager;
    final static Logger logger = Logger.getLogger(DBRoomsMethods.class);
    public DBRoomsMethods() {
        logger.info("Using DBRoomsMethods");
        mongoDBManager = MongoDBManager.getInstance();
    }
    /**
     * This method allows filter rooms by a criteria
     * @param field
     * @param criteria
     * @return an ArrayList of strings
     */
    public ArrayList<String> likeFilterByCriteria(String field, String criteria) {
        ArrayList<String> roomsList = mongoDBManager.likeFilterByCriteria("rooms",field,criteria);
        mongoDBManager.close();
        return roomsList;
    }
    /**
     * This method allows filter rooms by a criteria
     * @param criteria
     * @return an ArrayList of String
     */
    public ArrayList<String> filterRoomsByCriteria(String criteria) {
        final ArrayList<String> roomsList = new ArrayList<String>();
        MongoCollection<Document> roomsCollation = MongoDBManager.getInstance().getCollection("rooms");
        FindIterable<Document> rooms = roomsCollation
                .find(new Document("$or", asList( new Document(
                        "customDisplayName", new BasicDBObject("$regex", criteria)),
                        new Document("code", new BasicDBObject("$regex", criteria)),
                        new Document("DisplayName", new BasicDBObject("$regex", criteria)))));
        rooms.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                roomsList.add(document.get("customDisplayName").toString());
            }
        });
        return roomsList;
    }
    /**
     * This method allows get room id using the displayName
     * @param value
     * @return a String
     */
    public String getRoomId(String value) {
        String roomId = MongoDBManager.getInstance().getId("rooms", "customDisplayName", value);
        return roomId;
    }
}