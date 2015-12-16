package api;

import db.DBOutOfOrdersMethods;
import db.DBRoomsMethods;
import db.MongoDBManager;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by ArielWagner on 14/12/2015.
 */
public class APIOutOfOrdersMethods {

    APIManager apiManager;
    DBOutOfOrdersMethods dbOutOfOrdersMethods;
    MongoDBManager mongoDBManager;
    DBRoomsMethods dbRoomsMethods;

    public APIOutOfOrdersMethods() {
        apiManager = APIManager.getInstance();
        dbOutOfOrdersMethods = new DBOutOfOrdersMethods();
        mongoDBManager = new MongoDBManager();
        dbRoomsMethods = new DBRoomsMethods();
    }

    /**
     * This method allows get a JSONObject according to out of order Id
     * @param value
     * @return a JSONObject (Out of Order)
     */
    public JSONObject getJson(String value) {
        DBOutOfOrdersMethods dbOutOfOrdersMethods = new DBOutOfOrdersMethods();
        String outOfOrderId = dbOutOfOrdersMethods.getOutOfOrderId(value);
        JSONObject jsonObject = apiManager.getJson("/out-of-orders/" + outOfOrderId);
        return jsonObject;
    }

    /**
     * This method allows get the service id
     * @return
     */
    public String getServiceId() {
        return mongoDBManager.getId("services", "type", "exchange");
    }

    /**
     * This method allows get a "Out Of Orders" array of room specified
     * @param displayName
     * @return a JSONArray
     */
    public JSONArray getJsonOutOfOrdersOfRoom(String displayName) {
        String serviceId = getServiceId();
        String roomId = dbRoomsMethods.getRoomId(displayName);
        JSONArray jsonObject = apiManager.getArrayJson("/services/" + serviceId
        + "/rooms/" + roomId + "/out-of-orders");
        return jsonObject;
    }

    /**
     * This method allows get the "out of order" id
     * @param displayName
     * @param title
     * @return a String (outOfOrderId)
     */
    public String getJsonOfOutOfOrder(String displayName, String title) {
        JSONArray jsonArray = getJsonOutOfOrdersOfRoom(displayName);
        JSONObject outOfOrder;
        String outOfOrderId = null;
        for(int index = 0; index < jsonArray.length(); index++) {
            outOfOrder = jsonArray.getJSONObject(index);
            if(outOfOrder.getString("title").equals(title)) {
                outOfOrderId = outOfOrder.getString("_id");
            }
        }
        return outOfOrderId;
    }

    /**
     * This method allows deleteWithToken an "out of order"
     * @param displayName
     * @param title
     */
    public void deleteOutOfOrder(String displayName, String title) {
        apiManager = APIManager.getInstance();
        String serviceId = getServiceId();
        String roomId = dbRoomsMethods.getRoomId(displayName);
        String outOfOrderId = getJsonOfOutOfOrder(displayName, title);
        apiManager.deleteWithToken("/services/" + serviceId
                + "/rooms/" + roomId + "/out-of-orders/" + outOfOrderId);
    }
}
