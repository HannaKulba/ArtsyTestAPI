package testPetShop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import endpoints.EndPointPetShop;
import io.restassured.specification.RequestSpecification;
import model.Category;
import model.Pet;
import model.TagPet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.Log;
import utilsAPI.PetShopApiSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static utilsAPI.Status.AVAILABLE;
import static utilsAPI.Status.SOLD;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("API tests for petstore")
public class TestPetShop {

    private static RequestSpecification requestSpec = PetShopApiSpecification.getRequestSpecification();
    private static  File jsonSchema = new File("src/test/resources/json/petJsonSchema.json");
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void testValidateJsonScheme() {
        TagPet tag = new TagPet(35, "British cat");
        Category category = new Category(1, "Cat");
        Pet pet = new Pet(5489, category, "Missi", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), AVAILABLE);

        given().spec(requestSpec)
                .when()
                .body(pet)
                .post(EndPointPetShop.PET)
                .then()
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));
    }

    @Test
    public void testPOJO() {
        TagPet tag = new TagPet(7805, "Mainecoon");
        Category category = new Category(1, "Cat");
        String imageURL = "https://i.pinimg.com/originals/5c/2c/44/5c2c44f16da1caf134c00bc4f9c72ea0.jpg";
        Pet pet = new Pet(7574746, category, "Hardy", new ArrayList<>(Collections.singletonList(imageURL)), new ArrayList<>(Collections.singletonList(tag)), SOLD);

        String jsonBody = "";
        try {
            jsonBody = OBJECT_MAPPER.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            Log.error("Can't create jsonBody", e);
        }

        given().spec(requestSpec)
                .when()
                .body(jsonBody)
                .post(EndPointPetShop.PET)
                .then()
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));

    }

}
