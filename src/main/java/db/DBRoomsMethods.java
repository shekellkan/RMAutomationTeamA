package db;

import org.apache.log4j.Logger;

import java.util.ArrayList;

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

    public ArrayList<String> likeFilterByCriteria(String field, String criteria) {
        ArrayList<String> roomsList =  mongoDBManager.likeFilterByCriteria("rooms",field,criteria);
        mongoDBManager.close();
        return roomsList;
    }
}
