import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import pages.ParseMethods;
import utils.ArtsyAPISpecification;
import utils.Body;
import utils.EndPoint;
import utils.Property;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BeforeRequest {
    protected static RequestSpecification requestSpec = ArtsyAPISpecification.getRequestSpecification();
    protected Response response = null;

    protected Response sendRequest(String URL) {
        try {
            response = given()
                    .spec(requestSpec)
                    .when()
                    .header("X-Access-Token", Property.getPropertyValue("access_token"))
                    .get(URL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @BeforeAll
    void setUp() {
        Response response = given().spec(requestSpec)
                .when()
                .body(Body.getRequestTokenBody())
                .post(EndPoint.TOKEN);
        String token = ParseMethods.getAXAPPToken(response);
        requestSpec.header("x-xapp-token", token);
    }

}
