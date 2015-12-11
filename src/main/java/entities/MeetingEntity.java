package entities;

/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class MeetingEntity {
    private String organizer;
    private String subject;
    private String from;
    private String to;
    private String attendees;
    private String body;

    public void setOrganizer(String organizer){
        this.organizer = organizer;
    }
    public String getOrganizer(){
        return organizer;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getSubject(){
        return subject;
    }

    public void setFrom(String from){
        this.from = from;
    }
    public String getFrom(){
        return from;
    }

    public void setTo(String to){
        this.to = to;
    }
    public String getTo(){
        return to;
    }

    public void setAttendees(String attendees){
        this.attendees = attendees;
    }
    public String getAttendees(){
        return attendees;
    }

    public void setBody(String body){
        this.body = body;
    }
    public String getBody(){
        return body;
    }

    public void setAllFields(String organizer, String subject, String from, String to, String attendees, String body){
        this.organizer = organizer;
        this.subject = subject;
        this.from = from;
        this.to = to;
        this.attendees = attendees;
        this.body = body;
    }
}
