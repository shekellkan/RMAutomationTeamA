package api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by ArielWagner on 10/12/2015.
 */
public class APIManager {

    final static Logger logger = Logger.getLogger(APIManager.class);

    public APIManager() {
        logger.info("API Manager initialized");
        RestAssured.baseURI = "https://172.20.208.194:4040";
        RestAssured.useRelaxedHTTPSValidation();
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
}
