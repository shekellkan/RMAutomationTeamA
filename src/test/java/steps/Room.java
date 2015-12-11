package steps;

import db.DBRoomsMethods;
import entities.RoomEntity;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ui.pages.admin.ConferenceRoomsPage;
import ui.pages.admin.MainAdminPage;
import ui.pages.admin.RoomInfoPage;
import org.testng.Assert;

import java.util.ArrayList;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class Room {

    MainAdminPage mainAdminPage;
    ConferenceRoomsPage conferenceRoomsPage;
    RoomInfoPage roomInfoPage;
    RoomEntity roomEntity;
    String displayNameRoom;
    DBRoomsMethods dbRoomsMethods;
    String displayNameRoomDB;

    public Room(MainAdminPage mainAdminPage, RoomEntity roomEntity) {
        this.mainAdminPage = mainAdminPage;
        this.roomEntity = roomEntity;
    }

    @When("^I open to \"([^\"]*)\" Room for edit$")
    public void openRoomForEdit(String displayName) {
        conferenceRoomsPage = new ConferenceRoomsPage();
        displayNameRoom = displayName;
        roomInfoPage = conferenceRoomsPage.selectRoom(displayName);
    }

    @And("^I edit the displayName \"([^\"]*)\" ,code \"([^\"]*)\" and capacity \"([^\"]*)\"$")
    public void editDataRoom(String displayName, String code, String capacity) {
        roomInfoPage.editRoom(displayName, code, capacity);
        roomEntity.setAllFields(displayName, code, capacity);
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
        Assert.assertEquals(actualDisplayName, roomEntity.getDisplayName());
        Assert.assertEquals(actualCode, roomEntity.getCode());
        Assert.assertEquals(actualCapacity, roomEntity.getCapacity());
    }

    @And("^the Room edited should be obtained using the API$")
    public void theRoomEditedShouldBeObtainedUsingTheAPI() {

    }


    @When("^I search a Room by \"([^\"]*)\"$")
    public void searchARoomBy(String criteria) {

        conferenceRoomsPage = roomInfoPage.clickSaveRoom();
        conferenceRoomsPage.setFilterByRoom(criteria);
        dbRoomsMethods = new DBRoomsMethods();
        ArrayList<String> roomNames = dbRoomsMethods.likeFilterByCriteria("displayName", criteria);
        for(String name: roomNames) {
            System.out.println("name...." + name);
            if(!(name.equals(null))) {
                displayNameRoomDB = name;
            }
        }
    }

    @Then("^the Room or Rooms \"([^\"]*)\" should be listed$")
    public void theRoomOrRoomsShouldBeListed(String rooms) {
    }

    @When("^I go to Out of Order Planning Tab$")
    public void goToOutOfOrderPlanningTab() {
    }

    @And("^I configure the Room with the option out of order \"([^\"]*)\" at the time \"([^\"]*)\" to \"([^\"]*)\"$")
    public void iConfigureTheRoomWithTheOptionOutOfOrderAtTheTimeTo(String arg0, String arg1, String arg2) {
    }

    @After("@EditRoom")
    public void goBeforeDataRoom(){
        roomInfoPage.clearDataEntered(displayNameRoom);
        conferenceRoomsPage = roomInfoPage.clickSaveRoom();
        mainAdminPage = new MainAdminPage();
        mainAdminPage.getLeftMenuPage().goToResources();
    }

    @After("@FilterRoom")
    public void goBeforeDataRoomAfterOfFilter(){
        roomInfoPage = conferenceRoomsPage.selectRoom(roomEntity.getDisplayName());
        roomInfoPage.clearDataEntered(displayNameRoom);
        conferenceRoomsPage = roomInfoPage.clickSaveRoom();
        mainAdminPage = new MainAdminPage();
        mainAdminPage.getLeftMenuPage().goToResources();

    }
}
