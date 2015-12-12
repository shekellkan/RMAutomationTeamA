package db;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;
import static com.mongodb.client.model.Filters.eq;
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
        instance = null;
    }
    public ArrayList<String> likeFilterByCriteria(String collection, String field,String criteria) {
        final String fieldName = field;
        final ArrayList<String> list = new ArrayList<String>();
        MongoCollection<Document> roomsCollation = MongoDBManager.getInstance().getCollection(collection);
        FindIterable<Document> rooms = roomsCollation
                .find(new Document(fieldName, new BasicDBObject("$regex", criteria)));
        rooms.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                list.add(document.get(fieldName).toString());
            }
        });
        return list;
    }
    /**
     * This method allows get Id
     * @param collation
     * @param findBy
     * @param value
     * @return a String
     */
    public String getId(String collation, String findBy, String value) {
        final Document[] idObject = new Document[1];
        String id;
        MongoCollection<Document> roomsCollation = MongoDBManager.getInstance().getCollection(collation);
        FindIterable<Document> idList = roomsCollation.find(eq(findBy, value));
        idList.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                idObject[0] = document;
            }
        });
        if(idObject[0]!=null)
            id = idObject[0].get("_id").toString();
        else
            id= "";
        return id;
    }
}