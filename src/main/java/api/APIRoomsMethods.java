package api;

import db.DBRoomsMethods;
import entities.RoomEntity;
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
     * @param actualRoomEntity
     * @param newRoomEntity
     */
    public void putRoom(RoomEntity actualRoomEntity, RoomEntity newRoomEntity)
    {
        String capacityString = newRoomEntity.getCapacity();
        int capacity = 0;
        if(!(capacityString.equals(""))) {
            capacity = Integer.parseInt(capacityString);
        }
        String roomId = dbRoomsMethods.getRoomId(actualRoomEntity.getDisplayName());
        apiManager = APIManager.getInstance();
        given().
                contentType("application/json").
                header("Authorization", "jwt " + apiManager.getToken()).
                body("{\"customDisplayName\":" +"\"" +newRoomEntity.getDisplayName()+ "\",\"code\":\""+newRoomEntity.getCode()+"\",\"capacity\":"+capacity+"}").
                put("/rooms/" + roomId);
    }
}
