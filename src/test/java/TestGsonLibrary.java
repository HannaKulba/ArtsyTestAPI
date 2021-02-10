import org.junit.jupiter.api.*;
import utils.Body;
import utils.model.Artist;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API tests for artsy.net")
public class TestGsonLibrary {

    private Artist artist = null;
    private Body body = null;
    private String json = null;

    @BeforeAll
    public void setUp() {
        artist = new Artist("Stiven", "Marks", "12.03.1980", null, "Boston", 25);
        body = new Body();
    }

    @Test
    @Order(1)
    public void testCreateJson() {
        json = body.createJsonUsingGson(artist);
    }

    @Test
    @Order(2)
    public void testParseJson() {
        body.parseJsonUsingGson(json);
    }

}
