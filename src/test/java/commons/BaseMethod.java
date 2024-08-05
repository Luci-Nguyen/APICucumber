package commons;
 
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BaseMethod {
    private static RequestSpecification request;
    private static String baseUrl;

    public void verifyStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    public void verifyMatch(Response response, String key, int value) {
        response.then().body("key", equalTo(value));
    }

    public void verifyMatch(Response response, String key, String value) {
        response.then().body("key", equalTo("value"));
    }

    public void verifyMatch(Response response, String key, Object... expectedValues) {
        // Extract the list from the response body using the key
        List<?> actualList = response.jsonPath().getList(key);
        // Verify the list contains all expected values
        assertThat(actualList, containsInAnyOrder(expectedValues));
    }

    public RequestSpecification setHeader(String token) {
        return RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");
    }

    public RequestSpecification getURI(String uri) {
        request = RestAssured.given().baseUri(uri);
        return request;
    }

    public RequestSpecification setHeader() {
        return RestAssured.given()
                .header("Accept", "application/json");
    }

    public RequestSpecification body(String body) {
        return RestAssured.given()
                .body(body);
    }

    public RequestSpecification setParam(String key, Object value) {
        return request.pathParam(key, value);
    }

    public Response get(String endpoint) {
        return request.when().get(endpoint);
    }

    public Response getAll(String endpoint) {
        return request.when().get(endpoint);
    }

    public Response post(String endpoint) {
        return request.when().post(endpoint);
    }

    public Response put(String endpoint) {
        return request.when().put(endpoint);
    }

    public Response patch(String endpoint) {
        return request.when().patch(endpoint);
    }

    public Response delete(String endpoint) {
        return request.when().delete(endpoint);
    }

}
