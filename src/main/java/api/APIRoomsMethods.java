package api;

import com.jayway.restassured.response.Response;
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
        JSONObject jsonObject = apiManager.getJson("/rooms/"+roomId);
        return jsonObject;
    }

    /**
     * This method allows put a room
     * @param endPoint
     * @param id
     * @param displayName
     * @return a JSONObject
     */
    public JSONObject put(String endPoint,String id, String displayName)
    {
        Response response = given().
                header("Authorization", "jwt " + apiManager.getToken()).
                parameters("customDisplayName", displayName).
                put(endPoint + id);
        JSONObject jsonObject = new JSONObject(response.asString());
        return jsonObject;
    }
}
