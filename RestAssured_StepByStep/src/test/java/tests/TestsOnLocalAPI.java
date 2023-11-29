package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestsOnLocalAPI {

    @BeforeTest
    public void setup() {
        baseURI = "http://localhost:3000";
    }

    @Test
    public void get() {

        given().get("/users").then().statusCode(200).log().all();
    }

    @Test
    public void post() {
        JSONObject request = new JSONObject();

        request.put("firstName", "Elunia");
        request.put("lastName", "Gie");
        request.put("subjectId", 1);

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201);
    }

    @Test
    public void delete() {

        when().delete("/users/4").then().statusCode(200).log().all();
    }
}
