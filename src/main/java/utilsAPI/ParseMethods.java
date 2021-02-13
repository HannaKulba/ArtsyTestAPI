package utilsAPI;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import utils.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseMethods {

    public static JsonObject getJsonObjectFromResponse(Response response) {
        return new JsonParser().parse(response.getBody().asString()).getAsJsonObject();
    }

    public static String getAXAPPToken(Response response) {
        String xapppToken = getJsonObjectFromResponse(response).get("token").getAsString();
        Log.info("Token: " + xapppToken);
        return xapppToken;
    }

    public static String getArtistLinkFromSearch(Response response) {
        Log.info("Search results: " + response.getBody().asString());
        String artistLink = getJsonObjectFromResponse(response)
                .get("_embedded").getAsJsonObject()
                .get("results").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("_links").getAsJsonObject()
                .get("self").getAsJsonObject()
                .get("href").getAsString();
        Log.info("Artist link from search: " + artistLink);
        return artistLink;
    }

    public static String getArtistIdFromSearch(Response response) {
        String artistId = "";
        String artistLink = getArtistLinkFromSearch(response);
        Matcher m = Pattern.compile("[\\w+|\\d+]{24}").matcher(artistLink);
        while (m.find()) {
            artistId = m.group(0);
        }
        Log.info("Artist id from search: " + artistId);
        return artistId;
    }

    public static String getArtistHometown(Response response) {
        return getJsonObjectFromResponse(response).get("hometown").getAsString();
    }
}
