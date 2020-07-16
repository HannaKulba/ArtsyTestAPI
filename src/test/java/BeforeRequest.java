import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import pages.ParseMethods;
import utils.Bodies;
import utils.EndPoints;
import utils.ArtsyAPISpecification;

import static io.restassured.RestAssured.given;

public class BeforeRequest {
    protected static RequestSpecification requestSpec = ArtsyAPISpecification.getRequestSpecification();

    @BeforeAll
    static void setUp() {
        Response response = given().spec(requestSpec)
                .when()
                .body(Bodies.getRequestTokenBody())
                .post(EndPoints.TOKEN);
        String token = ParseMethods.getAXAPPToken(response);
        requestSpec.header("x-xapp-token", token);
    }

}
