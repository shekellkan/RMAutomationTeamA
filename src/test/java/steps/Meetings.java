package steps;

import Framework.ExternalVariablesManager;
import api.APIMeetingMethods;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.MeetingEntity;
import entities.RoomEntity;
import ui.pages.tablet.CredentialsPage;
import ui.pages.tablet.MainTabletPage;
import ui.pages.tablet.SchedulePage;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertEquals;

/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class Meetings {
    private MainTabletPage mainTabletPage = new MainTabletPage();
    private ExternalVariablesManager externalVariablesManager = ExternalVariablesManager.getInstance();
    private SchedulePage schedulePage;
    private CredentialsPage credentialsPage;
    private MeetingEntity meetingEntity = new MeetingEntity();
    private MeetingEntity meetingEntity2 = new MeetingEntity();
    private APIMeetingMethods apiMeetingMethods = new APIMeetingMethods();
    public RoomEntity roomEntity;

    public Meetings(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }

    @Given("^I navigate to Available section$")
    public void navigate_available_sections(){
        schedulePage = mainTabletPage.clickAvailableSection();
    }

    @When("^I create a Meeting with the following information: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void createAMeetingWithTheFollowingInformation(String organizer, String subject, String from, String to, String attendees, String body){
        meetingEntity.setAllFields(organizer, subject, from, to, attendees, body);
        credentialsPage = schedulePage.createNewMeeting(meetingEntity);
        schedulePage = credentialsPage.confirmCredentials(externalVariablesManager.getExchangeUserName(), externalVariablesManager.getExchangeUserPassword());
    }

    @Then("^an information message should be displayed \"([^\"]*)\"$")
    public void anInformationMessageShouldBeDisplayed(String nameMessage){
        assertTrue(schedulePage.isMessageDisplayed(nameMessage), "confirmation message is displayed");
    }

    @And("^the Meeting should be displayed in the Schedule bar$")
    public void theMeetingShouldBeDisplayedInTheScheduleBar(){
        String nameMeeting = meetingEntity.getSubject();
        assertTrue(schedulePage.isMeetingDisplayed(nameMeeting), "meeting is displayed in schedule bar");
        assertEquals(meetingEntity.getSubject(), schedulePage.getNameMeetingInScheduleBar(nameMeeting), "nameMeeting is equal to meeting schedule bar");
    }

    @And("^the Meeting information should be displayed in the Next section of Main page$")
    public void theMeetingInformationShouldBeDisplayedInTheNextSection(){
        mainTabletPage = schedulePage.goMainPage();
        String nameMeeting = meetingEntity.getSubject();
        assertTrue(mainTabletPage.isMeetingPresent(nameMeeting), "meeting is present in main page");
        assertTrue(mainTabletPage.isMeetingPresentInNextSection(nameMeeting), "meeting is present in next section");
        assertEquals(nameMeeting, mainTabletPage.getNameMeetingInNextSection(nameMeeting), "nameMeeting is equal to nameMeeting in next section");
    }

    @And("^the Meeting should be listed in the Meetings of Room using the API$")
    public void theMeetingShouldBeListedInTheMeetingsOfRoomUsingTheAPI(){
        String nameRoom = mainTabletPage.getMainTitle();
        String nameMeeting = meetingEntity.getSubject();
        assertEquals(nameRoom, apiMeetingMethods.getMeetingValues(nameMeeting, "location", nameRoom), "nameRoom is equal to nameRoom in meeting");
        assertEquals(nameMeeting, apiMeetingMethods.getMeetingValues(nameMeeting, "title", nameRoom), "nameMeeting is equal to title meeting");
    }

    @When("^I create other Meeting with the following information: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void createOtherMeetingWithTheFollowingInformation(String organizer, String subject, String from, String to, String attendees, String body){
        meetingEntity2.setAllFields(organizer, subject, from, to, attendees, body);
        credentialsPage = schedulePage.createNewMeeting(meetingEntity2);
        credentialsPage.cancelCredentials(externalVariablesManager.getExchangeUserName(), externalVariablesManager.getExchangeUserPassword());
    }

    @Then("^an error message should be displayed$")
    public void anErrorMessageShouldBeDisplayed(){
        assertTrue(credentialsPage.isConflictMessageDisplayed(), "error message is displayed");
        schedulePage = credentialsPage.clickCancelButton();
    }

    @And("^the second Meeting should not be displayed in the Schedule bar$")
    public void theSecondMeetingShouldNotBeDisplayedInTheScheduleBar(){
        assertFalse(schedulePage.isMeetingDisplayed(meetingEntity2.getSubject()), "the second meeting is not present in schedule bar");
    }

    @And("^the second Meeting information should not be displayed in the Next section$")
    public void theSecondMeetingInformationShouldNotBeDisplayedInTheNextSection(){
        mainTabletPage = schedulePage.goMainPage();
        assertFalse(mainTabletPage.isMeetingPresent(meetingEntity2.getSubject()), "the second meeting is not present in next section in main page");
    }

    @And("^the second Meeting should not be listed in the Meetings of Room using the API$")
    public void theSecondMeetingShouldNotBeListedInTheMeetingsOfRoomUsingTheAPI(){
        assertFalse(apiMeetingMethods.isMeetingPresent(meetingEntity2.getSubject(),"title", mainTabletPage.getMainTitle()), "the second meeting is not listed in the api");
    }

    @When("^I remove the Meeting$")
    public void removeTheMeeting(){
        credentialsPage = schedulePage.deleteMeeting(meetingEntity.getSubject());
        schedulePage = credentialsPage.confirmDelete(externalVariablesManager.getExchangeUserPassword());
    }

    @And("^the meeting should not be displayed in the Schedule bar$")
    public void theMeetingShouldBeRemovedFromTheTheScheduleBar(){
        assertFalse(schedulePage.isMeetingDisplayed(meetingEntity.getSubject()), "the meeting is removed from the schedule bar");
    }

    @And("^the meeting information should not be displayed in the Next section of Main page$")
    public void theMeetingInformationShouldBeRemovedFromTheNextSection(){
        mainTabletPage = schedulePage.goMainPage();
        assertFalse(mainTabletPage.isMeetingPresent(meetingEntity.getSubject()), "the meeting is removed from the next section of main page");
    }

    @When("^I update the meeting information: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void updateTheMeetingInformation(String organizer, String subject, String from, String to, String attendees, String body){
        String nameMeetingToUpdate = meetingEntity.getSubject();
        meetingEntity.setAllFields(organizer, subject, from, to, attendees, body);
        schedulePage.selectMeeting(nameMeetingToUpdate);
        credentialsPage = schedulePage.updateNewMeeting(meetingEntity);
        schedulePage = credentialsPage.confirmUpdate(externalVariablesManager.getExchangeUserPassword());
    }

    @When("^I create unsuccessfully a meeting with the following information: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void createUnsuccessfullyAMeetingWithTheFollowingInformation(String organizer, String subject, String from, String to, String attendees, String body){
        meetingEntity.setAllFields(organizer, subject, from, to, attendees, body);
        schedulePage.missingInformationMeeting(meetingEntity);
    }

    @Then("^an error \"([^\"]*)\" message should be displayed$")
    public void anErrorMessageShouldBeDisplayed(String nameError){
        assertTrue(schedulePage.isRequiredMessageDisplayed(nameError), "an error message should be displayed");
    }

    @And("^the Meeting should not be listed in the meetings of Room using the API$")
    public void theMeetingShouldNotBeListedInTheMeetingsOfRoomUsingTheAPI(){
        assertFalse(apiMeetingMethods.isMeetingPresent(meetingEntity.getSubject(),"title", mainTabletPage.getMainTitle()), "the meeting not should be listed using api");
    }

    @And("^I have a Meeting with the following information: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iHaveAMeetingWithTheFollowingInformation(String organizer, String subject, String from, String to, String attendees, String body){
        String administrator = externalVariablesManager.getExchangeUserName();
        meetingEntity.setAllFields(administrator, subject, from, to, attendees, body);
        apiMeetingMethods.createMeeting(meetingEntity, roomEntity);
    }

    @After(value = "@Meetings")
    public void afterMeetingScenario(){
        apiMeetingMethods.deleteMeeting(mainTabletPage.getMainTitle(), meetingEntity.getSubject());
    }
}
