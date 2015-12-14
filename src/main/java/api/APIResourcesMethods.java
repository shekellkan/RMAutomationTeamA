package api;

import db.DBResourcesMethods;
import db.DBRoomsMethods;
import entities.ResourceEntity;
import org.json.JSONObject;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/12/15
 * Time: 3:28 PM
 */
public class APIResourcesMethods {
    APIManager apiManager;
    DBResourcesMethods dbResourcesMethods;

    public APIResourcesMethods() {
        apiManager = new APIManager();
        dbResourcesMethods = new DBResourcesMethods();
    }

    /**
     * This method allows get a JSONObject room
     * @param name
     * @return a JSONObject
     */
    public JSONObject getResourceJson(String name) {
        String resourceId = dbResourcesMethods.getResourceId(name);
        JSONObject jsonObject = apiManager.getJson("/resources", resourceId);
        return jsonObject;
    }

    public boolean isResourcePresent(ResourceEntity resourceEntity)
    {
        String resourceName = resourceEntity.getName();
        try{
            getResourceJson(resourceName).get("name");
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }
    public void removeResource(ResourceEntity resourceEntity)
    {
        apiManager.delete("/resources/",dbResourcesMethods.getResourceId(resourceEntity.getName()));
    }
}
