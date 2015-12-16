package api;

import db.DBRoomsMethods;
import org.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by ArielWagner on 11/12/2015.
 */
public class APIRoomsMethods {

    APIManager apiManager;
    DBRoomsMethods dbRoomsMethods;

    public APIRoomsMethods() {
        apiManager = APIManager.getInstance();
        dbRoomsMethods = new DBRoomsMethods();
    }

    /**
     * This method allows get a JSONObject room
     * @param value
     * @return a JSONObject
     */
    public JSONObject getJson(String value) {
        String roomId = dbRoomsMethods.getRoomId(value);
        JSONObject jsonObject = apiManager.getJson("/rooms/"+ roomId);
        return jsonObject;
    }

    /**
     * This method allows put a Room
     * @param roomId
     * @param displayName
     * @param capacity
     */
    public void putRoom(String roomId, String displayName, int capacity)
    {
        apiManager = APIManager.getInstance();
        given().
                contentType("application/json").
                header("Authorization", "jwt " + apiManager.getToken()).
                body("{\"customDisplayName\":" +"\"" +displayName+ "\",\"code\":\"\",\"capacity\":"+capacity+"}").
                put("/rooms/" + roomId);
    }
}
