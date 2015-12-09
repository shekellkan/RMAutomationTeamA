package ui.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by ArielWagner on 08/12/2015.
 */
public class JsonReader {

    private JSONObject jsonObjectMain;
    public String filePath = new File("src/main/resources/Environments.json").getAbsolutePath();
    final static Logger logger = Logger.getLogger(JsonReader.class);

    public JsonReader() {
        parseJSON(filePath);
    }

    /**
     * Parses the json file into a JSONObject
     * @param filePath
     */
    private void parseJSON (String filePath) {
        try {
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(filePath);
            jsonObjectMain = (JSONObject) jsonParser.parse(reader);
        } catch (FileNotFoundException ex) {
            logger.error("FileNotFoundException when reading the configuration file ", ex);
        } catch (ParseException ex) {
            logger.error("ParseException when reading the configuration file ", ex);
        }  catch (IOException ex) {
            logger.error("IOException when reading the configuration file ", ex);
        } catch (NullPointerException ex) {
            logger.error("NullPointerException when reading the configuration file ", ex);
        }
    }

    /**
     * This method allows get the key value from a JSON object
     * @param jsonObject
     * @param key
     * @return a String
     */
    private String getKeyValueFromJSONObject(JSONObject jsonObject, String key) {
        return (String)jsonObject.get(key);
    }

    /**
     * This method allows get the JSON Object value From JSON Object
     * @param jsonObject
     * @param key
     * @return a JSON Object
     */
    private JSONObject getJSONObjectValueFromJSONObject(JSONObject jsonObject, String key) {
        return (JSONObject) jsonObject.get(key);
    }

    /**
     * This method allows get a JSON Object from a JSON object by id
     * @param objectName
     * @param idKey
     * @param idValue
     * @param key
     * @return a JSON Object
     */
    private JSONObject getJSONObjectFromJSONObjectById(String objectName, String idKey, String idValue, String key) {
        JSONObject jsonObject = getJSONObjectFromArrayById(objectName, idKey, idValue);
        return getJSONObjectValueFromJSONObject(jsonObject, key);
    }

    /**
     * This method allows get the jsonObject which key and value matches the given parameters from a jsonArray
     * @param objectName
     * @param idKey
     * @param idValue
     * @return a JSON Object
     */
    private JSONObject getJSONObjectFromArrayById(String objectName, String idKey, String idValue) {
        JSONObject jsonObject = null;
        JSONArray array = (JSONArray)jsonObjectMain.get(objectName);
        for(Object object : array) {
            jsonObject = (JSONObject)object;
            if(jsonObject.get(idKey).equals(idValue)) {
                break;
            }
        }
        return jsonObject;
    }

    /**
     * This method allows get the jsonObject from the jsonArray and then gets a value given the key
     * @param objectName
     * @param idKey
     * @param idValue
     * @param key
     * @return a String
     */
    public String getKeyValue(String objectName, String idKey, String idValue, String key) {
        JSONObject jsonObject = getJSONObjectFromArrayById(objectName, idKey, idValue);
        return getKeyValueFromJSONObject(jsonObject, key);
    }

    /**
     * This method allows get the key value from a JSON internal
     * @param objectName
     * @param idKey
     * @param idValue
     * @param nameJSON
     * @param key
     * @return a String
     */
    public String getKeyValueFromJSONInternal(String objectName, String idKey, String idValue, String nameJSON, String key) {
        JSONObject jsonObject = getJSONObjectFromJSONObjectById(objectName, idKey, idValue, nameJSON);
        return getKeyValueFromJSONObject(jsonObject, key);
    }
}
