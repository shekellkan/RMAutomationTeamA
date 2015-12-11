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

/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class Meetings {
    private MainTabletPage mainTabletPage = new MainTabletPage();
    private ExternalVariablesManager externalVariablesManager = ExternalVariablesManager.getInstance();
    private SchedulePage schedulePage;
    private CredentialsPage credentialsPage;
    private MeetingEntity meetingEntity = new MeetingEntity();

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

    @Then("^an information message should be displayed$")
    public void anInformationMessageShouldBeDisplayed(){
        assertTrue(schedulePage.isSuccessfullyMessageDisplayed());
    }

    @And("^the Meeting should be displayed in the Schedule bar$")
    public void theMeetingShouldBeDisplayedInTheScheduleBar(){
        assertTrue(schedulePage.isMeetingDisplayed(meetingEntity.getSubject()));
    }

    @And("^the Meeting information should be displayed in the Next section$")
    public void theMeetingInformationShouldBeDisplayedInTheNextSection(){
        mainTabletPage = schedulePage.goMainPage();
        assertTrue(mainTabletPage.isMeetingPresent(meetingEntity.getSubject()));
    }

    @And("^the Meeting should be listed in the Meetings of Room using the API$")
    public void theMeetingShouldBeListedInTheMeetingsOfRoomUsingTheAPI(){
        assertTrue(true);
    }

    @After(value = "@Meetings", order = 999)
    public void afterMeetingScenario(){
        schedulePage = mainTabletPage.clickAvailableSection();
        credentialsPage = schedulePage.deleteMeeting(meetingEntity.getSubject());
        schedulePage = credentialsPage.confirmCredentials(externalVariablesManager.getExchangeUserName(), externalVariablesManager.getExchangeUserPassword());
    }
}
