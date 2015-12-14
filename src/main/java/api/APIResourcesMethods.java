package api;

import db.DBResourcesMethods;
import db.DBRoomsMethods;
import entities.ResourceEntity;
import entities.RoomEntity;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * User: Jean Carlo Rodriguez
 * Date: 12/12/15
 * Time: 3:28 PM
 */
public class APIResourcesMethods {
    APIManager apiManager;
    DBResourcesMethods dbResourcesMethods;
    DBRoomsMethods dbRoomsMethods;

    public APIResourcesMethods() {
        apiManager = new APIManager();
        dbResourcesMethods = new DBResourcesMethods();
        dbRoomsMethods = new DBRoomsMethods();
    }

    /**
     * This method return a resource json associated to the name
     * @param name
     * @return a JSONObject
     */
    public JSONObject getResourceJson(String name) {
        String resourceId = dbResourcesMethods.getResourceId(name);
        JSONObject jsonObject = apiManager.getJson("/resources/"+resourceId);
        return jsonObject;
    }

    /**
     * this method return true if the resources is present in the API
     * @param resourceEntity
     * @return
     */
    public boolean isResourcePresent(ResourceEntity resourceEntity){
        String resourceName = resourceEntity.getName();
        try{
            getResourceJson(resourceName).get("name");
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    /**
     * this method removes a resource from Room Manager
     * @param resourceEntity
     */
    public void removeResource(ResourceEntity resourceEntity){
        apiManager.delete("/resources/",dbResourcesMethods.getResourceId(resourceEntity.getName()));
    }

    /**
     * this method crates a resources in Room Manager
     * @param resourceEntity
     */
    public void createResource(ResourceEntity resourceEntity) {
        apiManager.postResource(resourceEntity);
    }

    public boolean isResourceAssociatedToTheRoom(RoomEntity roomEntity, ResourceEntity resourceEntity) {
        String roomID = dbRoomsMethods.getRoomId(roomEntity.getDisplayName());
        String resourceID = dbResourcesMethods.getResourceId(resourceEntity.getName());
        JSONArray array = apiManager.getArrayJson("/rooms/" + roomID + "/resources");
        //search the resource id in the array
        for(int i = 0;i<array.length();i++){
            if(array.getJSONObject(i).getString("resourceId").equals(resourceID)){
                return true;
            }
        }
        return false;
    }
}
