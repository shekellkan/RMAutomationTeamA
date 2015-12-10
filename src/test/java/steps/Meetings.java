package steps;

import Framework.ExternalVariablesManager;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
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
    private SearchPage searchPage;

    @Given("^I navigate to Available section$")
    public void navigate_available_sections(){
        schedulePage = mainTabletPage.clickAvailableSection();
    }

    @When("^I create a Meeting with the following information: \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void iCreateAMeetingWithTheFollowingInformation(String organizer, String subject, String from, String to, String attendees, String body){
        credentialsPage = schedulePage.createNewMeeting(organizer, subject, from, to, attendees, body);
        schedulePage = credentialsPage.confirmCredentials(externalVariablesManager.getExchangeUserName(), externalVariablesManager.getExchangeUserPassword());
    }

    @Then("^an information message should be displayed$")
    public void anInformationMessageShouldBeDisplayed(){
        assertTrue(schedulePage.isSuccessfullyMessageDisplayed());
    }

    @And("^the Meeting should be displayed in the Schedule bar$")
    public void theMeetingShouldBeDisplayedInTheScheduleBar(){

    }

    @And("^the Meeting information should be displayed in the Next section$")
    public void theMeetingInformationShouldBeDisplayedInTheNextSection(){

    }

    @And("^the Meeting should be listed in the Meetings of Room using the API$")
    public void theMeetingShouldBeListedInTheMeetingsOfRoomUsingTheAPI(){

    }
}
