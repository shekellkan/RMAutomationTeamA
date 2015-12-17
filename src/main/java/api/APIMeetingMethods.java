package api;

import Framework.ExternalVariablesManager;
import db.MongoDBManager;
import entities.MeetingEntity;
import entities.RoomEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;


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
     * @param meeting
     * @param room
     */
    public void createMeeting(MeetingEntity meeting, RoomEntity room){
        SimpleDateFormat current= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = current.format(date);
        String userAuthentication = ExternalVariablesManager.getInstance().getAuthenticationExchange();

        JSONObject test = new JSONObject();
        test.put("organizer", meeting.getOrganizer());
        test.put("title", meeting.getSubject());
        test.put("start", currentDate+"T"+changeHour(meeting.getFrom())+":00.000Z");
        test.put("end", currentDate+"T"+changeHour(meeting.getTo())+":00.000Z");
        test.put("location", room.getDisplayName());
        test.put("roomEmail", room.getDisplayName()+"@forest1.local");
        test.put("resources", new JSONArray().put(room.getDisplayName()+"@forest1.local"));
        test.put("attendees", new JSONArray().put(meeting.getAttendees()));

        String createEndPoint = buildEndPointForCreate(room.getDisplayName());
        apiManager.postMeeting(test, createEndPoint, userAuthentication);
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
    private String buildEndPointForCreate(String nameRoom){
        serviceId = getServiceId();
        roomId = getRoomId(nameRoom);

        return "/services/"+serviceId+"/rooms/"+roomId+"/meetings";
    }

    /**
     * this method add +4h to currentHour for the time zone
     * @param currentHour
     * @return newHour
     */
    private String changeHour(String currentHour){
        String newHour;
        int hour = Integer.parseInt(currentHour.split(":")[0]);
        String minute = currentHour.split(":")[1];
        int otherHour;
        int anotherHour;

        if(hour < 10){
            otherHour = hour + 4;
            if(otherHour < 10){
                newHour = "0"+otherHour+":"+minute;
            }else{
                newHour = otherHour+":"+minute;
            }
        }else{
            anotherHour = hour + 4;
            newHour = anotherHour+":"+minute;
        }
        return newHour;
    }
}
