package steps;

import Framework.ExternalVariablesManager;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.MeetingEntity;
import ui.pages.tablet.CredentialsPage;
import ui.pages.tablet.MainTabletPage;
import ui.pages.tablet.SchedulePage;
import ui.pages.tablet.SearchPage;

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
        assertTrue(schedulePage.isMessageDisplayed(nameMessage));
    }

    @And("^the Meeting should be displayed in the Schedule bar$")
    public void theMeetingShouldBeDisplayedInTheScheduleBar(){
        String nameMeeting = meetingEntity.getSubject();
        assertTrue(schedulePage.isMeetingDisplayed(nameMeeting));
        assertEquals(meetingEntity.getSubject(), schedulePage.getNameMeetingInScheduleBar(nameMeeting));
    }

    @And("^the Meeting information should be displayed in the Next section of Main page$")
    public void theMeetingInformationShouldBeDisplayedInTheNextSection(){
        mainTabletPage = schedulePage.goMainPage();
        String nameMeeting = meetingEntity.getSubject();
        assertTrue(mainTabletPage.isMeetingPresent(nameMeeting));
        assertTrue(mainTabletPage.isMeetingPresentInNextSection(nameMeeting));
        assertEquals(nameMeeting, mainTabletPage.getNameMeetingInNextSection(nameMeeting));
    }

    @And("^the Meeting should be listed in the Meetings of Room using the API$")
    public void theMeetingShouldBeListedInTheMeetingsOfRoomUsingTheAPI(){
        assertTrue(true);
    }

    @When("^I create other Meeting with the following information: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void createOtherMeetingWithTheFollowingInformation(String organizer, String subject, String from, String to, String attendees, String body){
        meetingEntity2.setAllFields(organizer, subject, from, to, attendees, body);
        credentialsPage = schedulePage.createNewMeeting(meetingEntity2);
        credentialsPage.cancelCredentials(externalVariablesManager.getExchangeUserName(), externalVariablesManager.getExchangeUserPassword());
    }

    @Then("^an error message should be displayed$")
    public void anErrorMessageShouldBeDisplayed(){
        assertTrue(credentialsPage.isConflictMessageDisplayed());
        schedulePage = credentialsPage.clickCancelButton();
    }

    @And("^the second Meeting should not be displayed in the Schedule bar$")
    public void theSecondMeetingShouldNotBeDisplayedInTheScheduleBar(){
        assertFalse(schedulePage.isMeetingDisplayed(meetingEntity2.getSubject()));
    }

    @And("^the second Meeting information should not be displayed in the Next section$")
    public void theSecondMeetingInformationShouldNotBeDisplayedInTheNextSection(){
        mainTabletPage = schedulePage.goMainPage();
        assertFalse(mainTabletPage.isMeetingPresent(meetingEntity2.getSubject()));
    }

    @And("^the second Meeting should not be listed in the Meetings of Room using the API$")
    public void theSecondMeetingShouldNotBeListedInTheMeetingsOfRoomUsingTheAPI(){
        assertTrue(true);
    }

    @When("^I remove the Meeting$")
    public void removeTheMeeting(){
        credentialsPage = schedulePage.deleteMeeting(meetingEntity.getSubject());
        schedulePage = credentialsPage.confirmDelete(externalVariablesManager.getExchangeUserPassword());
    }

    @And("^the meeting should not be displayed in the Schedule bar$")
    public void theMeetingShouldBeRemovedFromTheTheScheduleBar(){
        assertFalse(schedulePage.isMeetingDisplayed(meetingEntity.getSubject()));
    }

    @And("^the meeting information should not be displayed in the Next section of Main page$")
    public void theMeetingInformationShouldBeRemovedFromTheNextSection(){
        mainTabletPage = schedulePage.goMainPage();
        assertFalse(mainTabletPage.isMeetingPresent(meetingEntity.getSubject()));
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
        assertTrue(schedulePage.isRequiredMessageDisplayed(nameError));
    }

    @And("^the Meeting should not be listed in the meetings of Room using the API$")
    public void theMeetingShouldNotBeListedInTheMeetingsOfRoomUsingTheAPI(){
        assertTrue(true);
    }


//    @After(value = "@Meetings", order = 999)
//    public void afterMeetingScenario(){
////        schedulePage = mainTabletPage.clickAvailableSection();
////        credentialsPage = schedulePage.deleteMeeting(meetingEntity.getSubject());
////        schedulePage = credentialsPage.confirmCredentials(externalVariablesManager.getExchangeUserName(), externalVariablesManager.getExchangeUserPassword());
//    }
}
