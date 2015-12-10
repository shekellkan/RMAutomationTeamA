package steps;

import Models.RoomModel;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ui.pages.admin.ConferenceRoomsPage;
import ui.pages.admin.MainAdminPage;
import ui.pages.admin.RoomInfoPage;
import org.testng.Assert;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class Room {

    MainAdminPage mainAdminPage;
    ConferenceRoomsPage conferenceRoomsPage;
    RoomInfoPage roomInfoPage;
    RoomModel roomModel;
    String displayNameRoom;

    public Room(MainAdminPage mainAdminPage, RoomModel roomModel) {
        this.mainAdminPage = mainAdminPage;
        this.roomModel = roomModel;
    }

    @When("^I open to \"([^\"]*)\" Room for edit$")
    public void openRoomForEdit(String displayName) {
        displayNameRoom = displayName;
        roomInfoPage = conferenceRoomsPage.selectRoom(displayName);
    }

    @And("^I edit the displayName \"([^\"]*)\" ,code \"([^\"]*)\" and capacity \"([^\"]*)\"$")
    public void editDataRoom(String displayName, String code, String capacity) {
        roomInfoPage.editRoom(displayName, code, capacity);
        roomModel.setAllFields(displayName, code, capacity);
        conferenceRoomsPage = roomInfoPage.clickSaveRoom();
        roomInfoPage = conferenceRoomsPage.selectRoom(displayName);
    }

    @Then("^a information message should be displayed$")
    public void informationMessageDisplayed() {
        Assert.assertEquals(conferenceRoomsPage.getRoomModifiedMessagePopUp(), "Room successfully Modified");
    }

    @And("^the Room data should be the edited$")
    public void roomDataShouldBeTheEdited() {
        String actualDisplayName = roomInfoPage.getDisplayName();
        String actualCode = roomInfoPage.getCode();
        String actualCapacity = roomInfoPage.getCapacity();
        Assert.assertEquals(actualDisplayName, roomModel.getDisplayName());
        Assert.assertEquals(actualCode, roomModel.getCode());
        Assert.assertEquals(actualCapacity, roomModel.getCapacity());
    }

    @And("^the Room edited should be obtained using the API$")
    public void theRoomEditedShouldBeObtainedUsingTheAPI() {

    }

    @After("@EditRoom")
    public void goBeforeDataRoom(){
        roomInfoPage.clearDataEntered(displayNameRoom);
        conferenceRoomsPage = roomInfoPage.clickSaveRoom();
    }
}
