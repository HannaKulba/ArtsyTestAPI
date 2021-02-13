package testArtsy;

import endpoints.EndPointArtsy;
import org.junit.jupiter.api.*;
import utilsAPI.ParseMethods;
import utils.Property;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API tests for artsy.net")
public class TestRequests extends BeforeRequest {

    private String GustavKlimtID = "";

    @Test
    @Order(1)
    @DisplayName("Check Gustav Klimt ID")
    public void checkArtistId() {
        response = sendRequest(EndPointArtsy.SEARCH + "Gustav+Klimt");
        GustavKlimtID = ParseMethods.getArtistIdFromSearch(response);
        String expectedGustavKlimtID = "4d8b92b64eb68a1b2c000414";

        Assertions.assertEquals(expectedGustavKlimtID, GustavKlimtID, "Values are not equal. Current ID: " + GustavKlimtID + ", expected: " + expectedGustavKlimtID);
    }

    @Test
    @Order(2)
    @DisplayName("Check hometown of Gustav Klimt")
    public void checkArtistHomeTown() {
        response = sendRequest(EndPointArtsy.ARTISTS + "/" + GustavKlimtID);
        String hometown = ParseMethods.getArtistHometown(response);
        String expectedHometown = "Baumgarten, Austria";

        Assertions.assertEquals(expectedHometown, hometown, "Values are not equal. Current hometown: " + hometown + ", expected: " + expectedHometown);
    }

    @Test
    @Order(3)
    @DisplayName("Check if Gustav Klimt has artwork 'Der Kuss (The Kiss)'")
    public void checkArtistHasArtwork() {
        response = sendRequest(EndPointArtsy.ARTWORKS + "/?artist_id=" + GustavKlimtID);
        String expectedArtWorkName = "Der Kuss (The Kiss)";

        Assertions.assertTrue(response.getBody().asString().contains(expectedArtWorkName), "Response doesn't contain artwork named " + expectedArtWorkName);
    }

    @Test
    @Order(4)
    @DisplayName("Check time of creation and update of image")
    public void checkImageCreatedTime() {
        String imageID = "54bfdb597261692b57fd0000";

        ArrayList<String> expectedTimes = new ArrayList<>(Arrays.asList("2015-01-21T17:01:13+00:00", "2015-01-21T17:01:13+00:00"));
        ArrayList<String> actualTimes = new ArrayList<>();

        response = sendRequest(EndPointArtsy.IMAGES + "/" + imageID);

        actualTimes.add(ParseMethods.getJsonObjectFromResponse(response).get("created_at").getAsString());
        actualTimes.add(ParseMethods.getJsonObjectFromResponse(response).get("updated_at").getAsString());

        Assertions.assertArrayEquals(expectedTimes.toArray(), actualTimes.toArray(), "Arrays are not equal");
    }

    @Test
    @Order(5)
    @DisplayName("Check name of user")
    public void checkUserName() throws IOException {
        response = sendRequest(EndPointArtsy.USERS + "/" + Property.getPropertyValue("user_id"));
        String username = ParseMethods.getJsonObjectFromResponse(response).get("name").getAsString();
        String expetedUsername = "Hanna";

        Assertions.assertEquals(expetedUsername, username, "Username is not " + expetedUsername + ". Current username is " + username);
    }

}
