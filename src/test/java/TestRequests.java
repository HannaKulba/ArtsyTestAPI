import org.junit.jupiter.api.*;
import pages.ParseMethods;
import utils.EndPoint;
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
        response = sendRequest(EndPoint.SEARCH + "Gustav+Klimt");
        GustavKlimtID = ParseMethods.getArtistIdFromSearch(response);

        Assertions.assertEquals("4d8b92b64eb68a1b2c000414", GustavKlimtID);
    }

    @Test
    @Order(2)
    @DisplayName("Check hometown of Gustav Klimt")
    public void checkArtistHomeTown() {
        response = sendRequest(EndPoint.ARTISTS + "/" + GustavKlimtID);
        String hometown = ParseMethods.getArtistHometown(response);

        Assertions.assertEquals("Baumgarten, Austria", hometown);
    }

    @Test
    @Order(3)
    @DisplayName("Check if Gustav Klimt has artwork 'Der Kuss (The Kiss)'")
    public void checkArtistHasArtwork() {
        response = sendRequest(EndPoint.ARTWORKS + "/?artist_id=" + GustavKlimtID);

        Assertions.assertTrue(response.getBody().asString().contains("Der Kuss (The Kiss)"));
    }

    @Test
    @Order(4)
    @DisplayName("Check time of creation and update of image")
    public void checkImageCreatedTime() {
        String imageID = "54bfdb597261692b57fd0000";

        ArrayList<String> expectedTimes = new ArrayList<>(Arrays.asList("2015-01-21T17:01:13+00:00", "2015-01-21T17:01:13+00:00"));
        ArrayList<String> actualTimes = new ArrayList<>();

        response = sendRequest(EndPoint.IMAGES + "/" + imageID);

        actualTimes.add(ParseMethods.getJsonObjectFromResponse(response).get("created_at").getAsString());
        actualTimes.add(ParseMethods.getJsonObjectFromResponse(response).get("updated_at").getAsString());

        Assertions.assertArrayEquals(expectedTimes.toArray(), actualTimes.toArray());
    }

    @Test
    @Order(5)
    @DisplayName("Check name of user")
    public void checkUserName() throws IOException {
        response = sendRequest(EndPoint.USERS + "/" + Property.getPropertyValue("user_id"));
        String username = ParseMethods.getJsonObjectFromResponse(response).get("name").getAsString();

        Assertions.assertEquals("Hanna", username);
    }

}
