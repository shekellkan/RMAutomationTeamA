package steps;

import api.APIRoomsMethods;
import db.DBRoomsMethods;
import entities.ResourceEntity;
import entities.RoomEntity;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import ui.pages.admin.ConferenceRoomsPage;
import ui.pages.admin.MainAdminPage;
import ui.pages.admin.RoomAssociationResourcePage;
import ui.pages.admin.RoomInfoPage;
import ui.pages.admin.OutOfOrderPlanningPage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ArielWagner on 09/12/2015.
 */
public class Room {

    MainAdminPage mainAdminPage;
    ConferenceRoomsPage conferenceRoomsPage;
    RoomInfoPage roomInfoPage;
    RoomAssociationResourcePage roomAssociationResourcePage;
    OutOfOrderPlanningPage outOfOrderPlanningPage;
    RoomEntity roomEntity;
    ResourceEntity resourceEntity;
    String displayNameRoom;
    DBRoomsMethods dbRoomsMethods;
    String displayNameRoomDB;
    APIRoomsMethods apiRoomsMethods;

    public Room(MainAdminPage mainAdminPage, RoomEntity roomEntity, ResourceEntity resourceEntity) {
        this.mainAdminPage = mainAdminPage;
        this.roomEntity = roomEntity;
        this.resourceEntity = resourceEntity;
    }

    @When("^I open \"([^\"]*)\" Room for edit$")
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
    }

    @Then("^a information message should be displayed$")
    public void informationMessageDisplayed() {
        Assert.assertEquals(conferenceRoomsPage.getRoomModifiedMessagePopUp(), "Room successfully Modified");
    }

    @And("^the Room data should be the edited$")
    public void roomDataShouldBeTheEdited() {
        roomInfoPage = conferenceRoomsPage.selectRoom(roomEntity.getDisplayName());
        String actualDisplayName = roomInfoPage.getDisplayName();
        String actualCode = roomInfoPage.getCode();
        String actualCapacity = roomInfoPage.getCapacity();
        Assert.assertEquals(actualDisplayName, roomEntity.getDisplayName());
        Assert.assertEquals(actualCode, roomEntity.getCode());
        Assert.assertEquals(actualCapacity, roomEntity.getCapacity());
        conferenceRoomsPage = roomInfoPage.clickSaveRoom();
    }

    @And("^the Room edited should be obtained using the API$")
    public void theRoomEditedShouldBeObtainedUsingTheAPI() {
        dbRoomsMethods = new DBRoomsMethods();
        apiRoomsMethods = new APIRoomsMethods();
        JSONObject jsonObject = apiRoomsMethods.getJson(roomEntity.getDisplayName());
        String actualDisplayName = jsonObject.getString("customDisplayName");
        String actualCode = jsonObject.getString("code");
        String actualCapacity = String.valueOf(jsonObject.getInt("capacity"));
        Assert.assertEquals(actualDisplayName, roomEntity.getDisplayName());
        Assert.assertEquals(actualCode, roomEntity.getCode());
        Assert.assertEquals(actualCapacity, roomEntity.getCapacity());
    }


    @When("^I search a Room by \"([^\"]*)\"$")
    public void searchARoomBy(String criteria) {
        conferenceRoomsPage.setFilterByRoom(criteria);
    }

    @Then("^the Room or Rooms according to \"([^\"]*)\" should be listed$")
    public void theRoomOrRoomsShouldBeListed(String criteria) {
        dbRoomsMethods = new DBRoomsMethods();
        ArrayList<String> roomDisplayNames = dbRoomsMethods.filterRoomsByCriteria(criteria);
        ArrayList<String> roomsList = conferenceRoomsPage.getRoomsContainer();
        Collections.sort(roomDisplayNames);
        Collections.sort(roomsList);
        int position = 0;
        for(String roomDisplayName : roomDisplayNames) {
            Assert.assertEquals(roomsList.get(position), roomDisplayName);
            position++;
        }
    }

    @When("^I go to Out of Order Planning Tab$")
    public void goToOutOfOrderPlanningTab() {
        outOfOrderPlanningPage = roomInfoPage.goToOutOfOrderPlanning();
    }

    @And("^I configure the Room with the option out of order \"([^\"]*)\" at the time \"([^\"]*)\" to \"([^\"]*)\"$")
    public void configureTheRoomWithTheOptionOutOfOrderAtTheTimeTo(String outOfOrder, String arg1, String arg2) {
        outOfOrderPlanningPage.selectOutOfOrder(outOfOrder);
    }

    @After("@EditRoom")
    public void goBeforeDataRoom(){
        roomInfoPage = conferenceRoomsPage.selectRoom(roomEntity.getDisplayName());
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

    @And("^I select the Resource in the Conference Room page header$")
    public void iSelectTheResourceInTheConferenceRoomPageHeader()
    {
        conferenceRoomsPage = new ConferenceRoomsPage();
        conferenceRoomsPage.clickOnResourceButton(resourceEntity);
    }

    @And("^I go to Resource Association tab$")
    public void IGoToResourceAssociationTab()
    {
        roomAssociationResourcePage = roomInfoPage.goToResourceAssociations();
    }

    @And("^I associate the Resource with quantity \"([^\"]*)\"$")
    public void iAssociateTheResourceWithQuantity(String quantity)
    {
        roomAssociationResourcePage.associateResource(resourceEntity,quantity);
        roomAssociationResourcePage.clickSaveRoom();
    }
    // Todo
    @Then("^the Resource should be displayed with the assigned quantity in the list$")
    public void theResourceShouldBeDisplayedWithTheAssignedQuantityInTheList()
    {
    }
}
