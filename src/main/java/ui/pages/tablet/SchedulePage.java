package ui.pages.tablet;

import entities.MeetingEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.BasePageObject;
import ui.common.CommonMethods;


/**
 * Created by MiguelTerceros on 12/10/2015.
 */
public class SchedulePage extends BasePageObject {
    /**
     * declare variables of class
     */
    private TopMenuPage topMenuPage;
    private MeetingEntity meetingEntity;
    /**
     * declare WebElements
     */
    @FindBy(xpath = "//div[contains(@class,'item-title')]/span")
    WebElement scheduleLabel;
    @FindBy(xpath = "//div[contains(@class,'form-group')]/input[contains(@id,'txtOrganizer')]")
    WebElement organizerInput;
    @FindBy(xpath = "//div[contains(@class,'form-group')]/input[contains(@id,'txtSubject')]")
    WebElement subjectInput;
    @FindBy(xpath = "//label[contains(text(),'From')]/../input[contains(@type,'time')]")
    WebElement fromInput;
    @FindBy(xpath = "//label[contains(text(),'To')]/../input[contains(@type,'time')]")
    WebElement toInput;
    @FindBy(xpath = "//div[contains(@class,'angucomplete-holder')]/input")
    WebElement attendeesInput;
    @FindBy(xpath = "//div[contains(@class,'form-group')]/textarea[contains(@id,'txtBody')]")
    WebElement bodyTextArea;
    @FindBy(xpath = "//div[contains(@class,'form-bar')]/button[contains(@class,'item-btn')]")
    WebElement createButton;

    WebElement meetingLabel;
    WebElement meetingForDelete;
    @FindBy(xpath = "//div[contains(@class,'form-bar')]/button/span[contains(text(),'Remove')]")
    WebElement removeButton;
    WebElement messageElement;
    @FindBy(xpath = "//div[contains(@class,'form-bar')]/button/span[contains(text(),'Update')]")
    WebElement updateButton;
    /************** REQUIRED MESSAGES **************/
    WebElement fieldRequiredMessage;

    /**
     * This method is the constructor
     */
    public SchedulePage() {
        waitUntilPageObjectIsLoaded();
        topMenuPage = new TopMenuPage();
    }

    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(scheduleLabel));
    }

    /***************** TOP MENU ****************/
    /**
     * navigate to MainPage
     * @return new instance of MainTabletPage
     */
    public MainTabletPage goMainPage(){
        return topMenuPage.clickInHome();
    }

    /**
     * navigate to SchedulePage
     * @return new instance of SchedulePage
     */
    public SchedulePage goSchedulePage(){
        return this;
    }

    /**
     * navigate to SearchPage
     * @return new instance of SearchPage
     */
    public SearchPage goSearchPage(){
        return topMenuPage.clickInSearch();
    }

    /****************** BODY *****************/
    /**
     * set the value in the field Organizer
     * @param organizer
     * @return the same instance of SchedulePage
     */
    public SchedulePage setOrganizerInput(String organizer){
        organizerInput.clear();
        organizerInput.sendKeys(organizer);
        return this;
    }

    /**
     * set the value in the field Subject
     * @param subject
     * @return the same instance of SchedulePage
     */
    public SchedulePage setSubjectInput(String subject){
        subjectInput.clear();
        subjectInput.sendKeys(subject);
        return this;
    }

    /**
     * set the value in the field From
     * @param from
     * @return the same instance of SchedulePage
     */
    public SchedulePage setFromInput(String from){
        fromInput.clear();
        fromInput.sendKeys(from);
        return this;
    }

    /**
     * set the value in the field To
     * @param to
     * @return the same instance of SchedulePage
     */
    public SchedulePage setToInput(String to){
        toInput.clear();
        toInput.sendKeys(to);
        return this;
    }

    /**
     * set the value for a email in the Attendees field
     * @param attendees
     * @return the same instance of SchedulePage
     */
    public SchedulePage setAttendeesInput(String attendees){
        attendeesInput.clear();
        attendeesInput.sendKeys(attendees);
        return this;
    }

    /**
     * set the value in the Body textArea
     * @param body
     * @return the same instance of SchedulePage
     */
    public SchedulePage setBodyTextArea(String body){
        bodyTextArea.clear();
        bodyTextArea.sendKeys(body);
        return this;
    }

    /**
     * click in the Create button
     * @return new instance of CredentialsPage
     */
    public CredentialsPage clickCreateButton(){
        createButton.click();
        return new CredentialsPage();
    }

    /**
     * this method create a meeting
     * @param meetingEntity
     * @return new instance of CredentialsPage
     */
    public CredentialsPage createNewMeeting(MeetingEntity meetingEntity){
        this.meetingEntity = meetingEntity;
        setOrganizerInput(meetingEntity.getOrganizer());
        setSubjectInput(meetingEntity.getSubject());
        setFromInput(meetingEntity.getFrom());
        setToInput(meetingEntity.getTo());
        setAttendeesInput(meetingEntity.getAttendees());
        setBodyTextArea(meetingEntity.getBody());
        return clickCreateButton();
    }

    /**
     * this method build a path for found a meeting
     * @param nameMeeting
     * @return path
     */
    public String buildMeetingDisplay(String nameMeeting){
        return "//div[contains(@class,'vis-item-overflow')]/div[contains(@class,'vis-item-content')]/span[contains(text(),'"+nameMeeting+"')]";
    }

    /**
     * this method verify that a meeting is displayed
     * @param nameMeeting
     * @return true or false
     */
    public boolean isMeetingDisplayed(String nameMeeting){
        try{
            meetingLabel = driver.findElement(By.xpath(buildMeetingDisplay(nameMeeting)));
            meetingLabel.isDisplayed();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    /**
     * click in a meeting that is present in the schedule bar
     * @param nameMeeting
     * @return the same instance of SchedulePage
     */
    public SchedulePage selectMeeting(String nameMeeting){
        meetingForDelete = driver.findElement(By.xpath(buildMeetingDisplay(nameMeeting)));
        meetingForDelete.click();
        return this;
    }

    /**
     * click in the Remove button
     * @return new instance of CredentialsPage
     */
    public CredentialsPage clickRemoveButton(){
        removeButton.click();
        return new CredentialsPage();
    }

    /**
     * deleteWithToken a meeting
     * @param nameMeeting
     * @return new instance of CredentialsPage
     */
    public CredentialsPage deleteMeeting(String nameMeeting){
        selectMeeting(nameMeeting);
        return clickRemoveButton();
    }

    /**
     * this method verify that a information message is displayed
     * @param nameMessage
     * @return true or false
     */
    public boolean isMessageDisplayed(String nameMessage){
        messageElement = driver.findElement(By.xpath(CommonMethods.buildMessageElementLocator(nameMessage)));
        return messageElement.isDisplayed();
    }

    /**
     * click in Update button
     * @return new instance of CredentialsPage
     */
    public CredentialsPage clickUpdateButton(){
        updateButton.click();
        return new CredentialsPage();
    }

    /**
     * update the information of a meeting
     * @param meetingEntity
     * @return new instance of CredentialsPage
     */
    public CredentialsPage updateNewMeeting(MeetingEntity meetingEntity){
        this.meetingEntity = meetingEntity;
        setSubjectInput(meetingEntity.getSubject());
        setFromInput(meetingEntity.getFrom());
        setToInput(meetingEntity.getTo());
        setAttendeesInput(meetingEntity.getAttendees());
        setBodyTextArea(meetingEntity.getBody());
        return clickUpdateButton();
    }

    /**
     * this method build the path for a required message
     * @param fieldRequired
     * @return path
     */
    public String buildMessageRequired(String fieldRequired){
        return "//small[contains(@class,'text-warnings') and contains(text(),'"+fieldRequired+"')]";
    }

    /**
     * this method verify that a required message is displayed
     * @param fieldRequired
     * @return true or false
     */
    public boolean isRequiredMessageDisplayed(String fieldRequired){
        fieldRequiredMessage = driver.findElement(By.xpath(buildMessageRequired(fieldRequired)));
        return fieldRequiredMessage.isDisplayed();
    }

    /**
     * try to create a meeting with missing information
     * @param meetingEntity
     * @return the same instance of SchedulePage
     */
    public SchedulePage missingInformationMeeting(MeetingEntity meetingEntity){
        this.meetingEntity = meetingEntity;
        setOrganizerInput(meetingEntity.getOrganizer());
        setSubjectInput(meetingEntity.getSubject());
        setFromInput(meetingEntity.getFrom());
        setToInput(meetingEntity.getTo());
        setAttendeesInput(meetingEntity.getAttendees());
        setBodyTextArea(meetingEntity.getBody());
        createButton.click();
        return this;
    }

    public String getNameMeetingInScheduleBar(String nameMeeting){
        meetingLabel = driver.findElement(By.xpath(buildMeetingDisplay(nameMeeting)));
        return meetingLabel.getText();
    }
}
