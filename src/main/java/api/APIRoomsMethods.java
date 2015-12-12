package api;

import db.DBRoomsMethods;
import org.json.JSONObject;

/**
 * Created by ArielWagner on 11/12/2015.
 */
public class APIRoomsMethods {

    APIManager apiManager;
    DBRoomsMethods dbRoomsMethods;

    public APIRoomsMethods() {
        apiManager = new APIManager();
        dbRoomsMethods = new DBRoomsMethods();
    }

    /**
     * This method allows get a JSONObject room
     * @param value
     * @return a JSONObject
     */
    public JSONObject getJson(String value) {
        String roomId = dbRoomsMethods.getRoomId(value);
        JSONObject jsonObject = apiManager.getJson("/rooms", roomId);
        return jsonObject;
    }
}
