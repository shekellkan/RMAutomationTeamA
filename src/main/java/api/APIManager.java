package api;

import Framework.ExternalVariablesManager;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by ArielWagner on 10/12/2015.
 */
public class APIManager {

    public static APIManager instance;
    final static Logger logger = Logger.getLogger(APIManager.class);

    public static APIManager getInstance() {
        if(instance==null)
        {
            instance = new APIManager();
            logger.info("API Manager initialized");
            RestAssured.baseURI = "https://172.20.208.194:4040";
            RestAssured.useRelaxedHTTPSValidation();
        }
        return instance;
    }

    /**
     * This method allows get a JSONObject
     * @param endPoint
     * @param id
     * @return a JSONObject
     */
    public JSONObject getJson(String endPoint, String id) {
        Response response = given().when().get(endPoint + "/" + id);
        JSONObject jsonObject = new JSONObject(response.asString());
        return jsonObject;
    }

    //Todo
    public String getToken()
    {
        String user = ExternalVariablesManager.getInstance().getAdminUserName();
        String password = ExternalVariablesManager.getInstance().getAdminUserPassword();
        String response = given().log().all().contentType("application/json").
                body("{\"username\":\""+user+"\",\"password\":\""+password+"\",\"authentication\": \"local\"}").
                when().
                post("/login").asString();
        JSONObject tokenJson = new JSONObject(response);
        return tokenJson.getString("token");
    }

    public void delete(String endPoint,String id)
    {
        given().log().all().
                headers("Authorization", "jwt "+getToken()).
                when().delete(endPoint+id).
                then().log().all().
                statusCode(200);
    }
}
