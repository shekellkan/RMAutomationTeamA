package api;

import Framework.ExternalVariablesManager;
import com.jayway.restassured.RestAssured;
import db.MongoDBManager;
import entities.MeetingEntity;
import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.util.calendar.BaseCalendar;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by MiguelTerceros on 12/14/2015.
 */
public class APIMeetingMethods {
    APIManager apiManager;
    MongoDBManager mongoDBManager;

    String serviceId;
    String roomId;
    String meetingId;
    String deleteMeetingEndPoint;
    String meetingEndPoint;

    /**
     * construct of APIMeetingMethods
     */
    public APIMeetingMethods(){
        apiManager = APIManager.getInstance();
        mongoDBManager = new MongoDBManager();
    }

    /**
     * this method delete a meeting
     * @param nameRoom
     * @param nameMeeting
     */
    public void deleteMeeting(String nameRoom, String nameMeeting){
        String userAuthentication = ExternalVariablesManager.getInstance().getAuthenticationExchange();

        deleteMeetingEndPoint = buildEndPoint(nameRoom, nameMeeting);
        apiManager.deleteBasic(deleteMeetingEndPoint, userAuthentication);
    }

    /**
     * this method obtains a value of the meeting json
     * @param nameMeeting
     * @param value
     * @param nameRoom
     * @return
     */
    public String getMeetingValues(String nameMeeting, String value, String nameRoom) {
        meetingEndPoint = buildEndPoint(nameRoom, nameMeeting);
        JSONObject jsonObject = apiManager.getJson(meetingEndPoint);
        return jsonObject.getString(value);
    }

    /**
     * obtains the serviceId
     * @return serviceId
     */
    public String getServiceId(){
        return mongoDBManager.getId("services", "type", "exchange");
    }

    /**
     * obtains the roomId
     * @param nameRoom
     * @return roomId
     */
    public String getRoomId(String nameRoom){
        return mongoDBManager.getId("rooms", "displayName", nameRoom);
    }

    /**
     * obtains the meetingId
     * @param nameMeeting
     * @return meetingId
     */
    public String getMeetingId(String nameMeeting){
        return mongoDBManager.getId("meetings", "title", nameMeeting);
    }

    /**
     * this method construct the path for a meeting in a room specific
     * @param nameRoom
     * @param nameMeeting
     * @return
     */
    public String buildEndPoint(String nameRoom, String nameMeeting){
        serviceId = getServiceId();
        roomId = getRoomId(nameRoom);
        meetingId = getMeetingId(nameMeeting);

        return "/services/"+serviceId+"/rooms/"+roomId+"/meetings/"+meetingId;
    }

    /**
     * this method create a meeting
     * @param meeting
     * @param roomName
     */
    public void postMeeting(MeetingEntity meeting, String roomName){
        SimpleDateFormat current= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = current.format(date);

        JSONObject test = new JSONObject();
        test.put("organizer", meeting.getOrganizer());
        test.put("title", meeting.getSubject());
        test.put("start", currentDate+"T"+meeting.getFrom()+":00.000Z");
        test.put("end", currentDate+"T"+meeting.getTo()+":00.000Z");
        test.put("location", roomName);
        test.put("roomEmail", roomName+"@forest1.local");
        test.put("resources", new JSONArray().put(roomName+"@forest1.local"));
        test.put("attendees", new JSONArray().put(meeting.getAttendees()));

        String createEndPoint = buildEndPointForCreate(roomName);
        apiManager.createMeeting(test, createEndPoint);
    }

    /**
     * this method verify that a meeting is present in a conference room
     * @param nameMeeting
     * @param value
     * @param nameRoom
     * @return true or false
     */
    public boolean isMeetingPresent(String nameMeeting,String value, String nameRoom) {
        meetingEndPoint = buildEndPoint(nameRoom, nameMeeting);
        JSONObject jsonObject = apiManager.getJson(meetingEndPoint);
        if(jsonObject.has(value)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * this method construct the path for to create a meeting in a room specific
     * @param nameRoom
     * @return
     */
    public String buildEndPointForCreate(String nameRoom){
        serviceId = getServiceId();
        roomId = getRoomId(nameRoom);

        return "/services/"+serviceId+"/rooms/"+roomId+"/meetings";
    }
}
