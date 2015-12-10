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
    private String tabletUserName;
    private String tabletUserPassword;

    private String exchangeUserName;
    private String exchangeUserPassword;

    private String envId;
    private String roomManagerService;

    private JsonReader jsonReader;
    private static ExternalVariablesManager instance;

    protected ExternalVariablesManager(){ initialize();}

    public static ExternalVariablesManager getInstance(){
        if(instance == null){
            instance = new ExternalVariablesManager();
        }
        return instance;
    }

    private void initialize(){
        //get environment system property
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
    }

    public String getBrowserName(){
        return browserName;
    }

    public String getAdminURL(){
        return adminURL;
    }

    public String getTabletURL(){
        return tabletURL;
    }

    public String getRoomManagerService(){
        return roomManagerService;
    }

    public String getAdminUserName(){
        return adminUserName;
    }

    public String getAdminUserPassword(){
        return adminUserPassword;
    }

    public String getTabletUserName(){
        return tabletUserName;
    }

    public String getTabletUserPassword(){
        return tabletUserPassword;
    }

    public String getExchangeUserName(){
        return exchangeUserName;
    }

    public String getExchangeUserPassword() {
        return exchangeUserPassword;
    }

    public String getMainAdminURL() {
        return mainAdminURL;
    }
}