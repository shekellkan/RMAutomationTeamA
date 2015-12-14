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

    public APIMeetingMethods(){
        apiManager = new APIManager();
        mongoDBManager = new MongoDBManager();
    }

    public void deleteMeeting(String nameRoom, String nameMeeting){
        String userName = ExternalVariablesManager.getInstance().getExchangeUserName();
        String userPassword = ExternalVariablesManager.getInstance().getExchangeUserPassword();

        deleteMeetingEndPoint = buildEndPoint(nameRoom, nameMeeting);
        apiManager.deleteBasic(deleteMeetingEndPoint+"/"+getMeetingId(nameMeeting), userName, userPassword);
    }

    public String getMeetingValues(String nameMeeting, String value, String nameRoom) {
        meetingEndPoint = buildEndPoint(nameRoom, nameMeeting);
        JSONObject jsonObject = apiManager.getJson(meetingEndPoint, getMeetingId(nameMeeting));
        return jsonObject.getString(value);
    }

    public String getServiceId(){
        return mongoDBManager.getId("services", "type", "exchange");
    }

    public String getRoomId(String nameRoom){
        return mongoDBManager.getId("rooms", "displayName", nameRoom);
    }

    public String getMeetingId(String nameMeeting){
        return mongoDBManager.getId("meetings", "title", nameMeeting);
    }

    public String buildEndPoint(String nameRoom, String nameMeeting){
        serviceId = getServiceId();
        roomId = getRoomId(nameRoom);
        meetingId = getMeetingId(nameMeeting);

        return "/services/"+serviceId+"/rooms/"+roomId+"/meetings";
        //return "/services/"+serviceId+"/rooms/"+roomId+"/meetings/"+meetingId;
    }

    public void postMeeting(MeetingEntity meetingEntity, String roomName){
        String userName = ExternalVariablesManager.getInstance().getExchangeUserName();
        String userPassword = ExternalVariablesManager.getInstance().getExchangeUserPassword();

        SimpleDateFormat current= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = current.format(date);

        String deleteMeetingEndPoint = buildEndPoint(roomName, meetingEntity.getSubject());
        given().auth().basic(userName,userPassword).
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
