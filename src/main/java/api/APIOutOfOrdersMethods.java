package api;

import db.DBOutOfOrdersMethods;
import org.json.JSONObject;

/**
 * Created by ArielWagner on 14/12/2015.
 */
public class APIOutOfOrdersMethods {

    APIManager apiManager;
    DBOutOfOrdersMethods dbOutOfOrdersMethods;

    public APIOutOfOrdersMethods() {
        apiManager = APIManager.getInstance();
        dbOutOfOrdersMethods = new DBOutOfOrdersMethods();
    }

    /**
     * This method allows get a JSONObject according to out of order Id
     * @param value
     * @return a JSONObject (Out of Order)
     */
    public JSONObject getJson(String value) {
        DBOutOfOrdersMethods dbOutOfOrdersMethods = new DBOutOfOrdersMethods();
        String outOfOrderId = dbOutOfOrdersMethods.getOutOfOrderId(value);
        JSONObject jsonObject = apiManager.getJson("/out-of-orders/" + outOfOrderId);
        return jsonObject;
    }
}
