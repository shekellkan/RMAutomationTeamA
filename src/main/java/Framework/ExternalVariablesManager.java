package Framework;

/**
 * Created by MiguelTerceros on 12/8/2015.
 */
public class ExternalVariablesManager {

    private String browserName;
    private String adminURL;
    private String mainAdminURL;
    private String adminUserName;
    private String adminUserPassword;

    private String tabletURL;
    private String loginTabletURL;
    private String tabletUserName;
    private String tabletUserPassword;

    private String exchangeUserName;
    private String exchangeUserPassword;

    private String envId;
    private String roomManagerService;

    private JsonReader jsonReader;
    private static ExternalVariablesManager instance;
    private String authenticationExchange;

    /**
     * construct of the class ExternalVariablesManager
     */
    protected ExternalVariablesManager(){ initialize();}

    /**
     * obtains the instance of ExternalVariablesManager
     * @return instance
     */
    public static ExternalVariablesManager getInstance(){
        if(instance == null){
            instance = new ExternalVariablesManager();
        }
        return instance;
    }

    /**
     * initialize all variables of a environment
     */
    private void initialize(){
        //get environment
        String environment = System.getProperty("envId");
        if(environment == null || environment.isEmpty()){
            envId = "RM1";
        }else{
            envId = environment;
        }
        //get browserName
        browserName = System.getProperty("browserName");
        if(browserName==null || browserName.isEmpty())
        {
            browserName = new JsonReader("/config.json").getKeyFromSingleJson("default browser");
        }

        //json reader
        jsonReader = new JsonReader("Environments.json");

        //URL first lvl
        adminURL = jsonReader.getKeyValue("Environments", "id", envId, "admin URL");
        mainAdminURL = jsonReader.getKeyValue("Environments", "id", envId, "admin main url");
        tabletURL = jsonReader.getKeyValue("Environments", "id", envId, "tablet URL");
        loginTabletURL = jsonReader.getKeyValue("Environments", "id", envId, "tablet login URL");
        roomManagerService = jsonReader.getKeyValue("Environments", "id", envId, "room manager service");

        //count admin
        adminUserName = jsonReader.getKeyValueFromJSONInternal("Environments", "id", envId, "admin user", "name");
        adminUserPassword = jsonReader.getKeyValueFromJSONInternal("Environments", "id", envId, "admin user", "password");
        //count tablet
        tabletUserName = jsonReader.getKeyValueFromJSONInternal("Environments", "id", envId, "tablet user", "name");
        tabletUserPassword = jsonReader.getKeyValueFromJSONInternal("Environments", "id", envId, "tablet user", "password");
        //count exchange
        exchangeUserName = jsonReader.getKeyValueFromJSONInternal("Environments", "id", envId, "exchange user", "name");
        exchangeUserPassword = jsonReader.getKeyValueFromJSONInternal("Environments", "id", envId, "exchange user", "password");
        authenticationExchange = jsonReader.getKeyValue("Environments", "id", envId, "Authentication Exchange");
    }

    /**
     * obtains the name of the browser
     * @return browserName
     */
    public String getBrowserName(){
        return browserName;
    }

    /**
     * obtains the URL of login page of admin
     * @return adminURL
     */
    public String getAdminURL(){
        return adminURL;
    }

    /**
     * obtains the URL of tablet page
     * @return tabletURL
     */
    public String getTabletURL(){
        return tabletURL;
    }

    /**
     * obtains the URL of login page of the tablet
     * @return loginTabletURL
     */
    public String getLoginTabletURL(){
        return loginTabletURL;
    }

    /**
     * obtains the URL of Room Manager Service
     * @return roomManagerService
     */
    public String getRoomManagerService(){
        return roomManagerService;
    }

    /**
     * obtains the User Name of admin page
     * @return adminUserName
     */
    public String getAdminUserName(){
        return adminUserName;
    }

    /**
     * obtains the User Password of admin page
     * @return adminUserPassword
     */
    public String getAdminUserPassword(){
        return adminUserPassword;
    }

    /**
     * obtains the User Name of the tablet page
     * @return tabletUSerName
     */
    public String getTabletUserName(){
        return tabletUserName;
    }

    /**
     * obtains the User Password of the tablet page
     * @return tabletUserPassword
     */
    public String getTabletUserPassword(){
        return tabletUserPassword;
    }

    /**
     * obtains the User Name of the account Server Exchange
     * @return exchangeUserName
     */
    public String getExchangeUserName(){
        return exchangeUserName;
    }

    /**
     * obtains the User Password of the account Server Exchange
     * @return exchangeUserPassword
     */
    public String getExchangeUserPassword() {
        return exchangeUserPassword;
    }

    /**
     * obtains the URL of main page admin
     * @return mainAdminURL
     */
    public String getMainAdminURL() {
        return mainAdminURL;
    }

    /**
     * obtains the authentication in base64 of exchange user
     * @return authenticationExchange
     */
    public String getAuthenticationExchange(){
        return authenticationExchange;
    }
}