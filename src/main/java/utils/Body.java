package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

public class Body {

    public static String getRequestTokenBody() {
        JsonObject jsonObject = new JsonObject();
        try {
            String client_id = "client_id";
            String client_secret = "client_secret";

            jsonObject.addProperty(client_id, Property.getPropertyValue(client_id));
            jsonObject.addProperty(client_secret, Property.getPropertyValue(client_secret));
        } catch (IOException e) {
            Log.error(e);
        }
        return jsonObject.toString();
    }

    public String createJsonUsingGson(Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        Log.info(json);
        return json;
    }

    public void parseJsonUsingGson(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();

        for (String key : jsonObject.keySet()){
            Log.info("Key: " + key + ", value: " + jsonObject.get(key));
        }

    }
}
