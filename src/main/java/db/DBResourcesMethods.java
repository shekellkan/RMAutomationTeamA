package db;

import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/11/15
 * Time: 10:44 AM
 */
public class DBResourcesMethods {
    MongoDBManager mongoDBManager;
    final static Logger logger = Logger.getLogger(DBResourcesMethods.class);

    public DBResourcesMethods() {
        logger.info("Using DBResourcesMethods");
        mongoDBManager = MongoDBManager.getInstance();
    }

    public ArrayList<String> likeFilterByCriteria(String field, String criteria) {
        ArrayList<String> resourceList =  mongoDBManager.likeFilterByCriteria("resourcemodels",field,criteria);
        mongoDBManager.close();
        return resourceList;
    }
}
