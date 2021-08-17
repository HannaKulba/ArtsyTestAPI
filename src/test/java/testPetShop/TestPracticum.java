package testPetShop;

import com.google.gson.JsonParser;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPracticum {

    String baseUri = "https://petstore.swagger.io/v2";
    String status = "/pet/findByStatus";

    RequestSpecification rs = new RequestSpecBuilder()
            .setBaseUri(baseUri)
            .setContentType(ContentType.JSON)
            .log(LogDetail.METHOD)
            .log(LogDetail.URI)
            .log(LogDetail.HEADERS)
            .log(LogDetail.BODY)
            .build();


    @Test
    public void test_1() {
        Response response = given()
                .spec(rs)
                .when()
                .get(status + "?status=available");

        response.prettyPrint();

        String responseBody = response.getBody().asString();
        JsonParser parser = new JsonParser();
        Long id = parser.parse(responseBody).getAsJsonArray().get(0).getAsJsonObject().get("id").getAsLong();

        System.out.println(id);

    }

}
