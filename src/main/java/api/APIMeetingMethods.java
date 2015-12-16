package api;

import Framework.ExternalVariablesManager;
import db.MongoDBManager;
import entities.MeetingEntity;
import org.json.JSONObject;
import sun.util.calendar.BaseCalendar;

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
        apiManager = new APIManager();
        mongoDBManager = new MongoDBManager();
    }

    /**
     * this method deleteWithToken a meeting
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
     * @param meetingEntity
     * @param roomName
     */
    public void postMeeting(MeetingEntity meetingEntity, String roomName){
        String userAuthentication = ExternalVariablesManager.getInstance().getAuthenticationExchange();

        SimpleDateFormat current= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = current.format(date);

        String deleteMeetingEndPoint = buildEndPoint(roomName, meetingEntity.getSubject());
        given().header("Authentication", "Basic "+userAuthentication).
                parameters("organizer", meetingEntity.getOrganizer(),
                            "title", meetingEntity.getSubject(),
                            "start", currentDate+"T"+meetingEntity.getFrom()+":00.000Z",
                            "end", currentDate+"T"+meetingEntity.getTo()+":00.000Z",
                            "location", roomName,
                            "roomEmail", roomName+"@forest1.local",
                            "resources","["+roomName+"@forest1.local]",
                            "attendees","["+meetingEntity.getAttendees()+"]").
                post(deleteMeetingEndPoint);
    }
}
