package endpoints;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Model;
import utilities.Log;

import java.io.FileOutputStream;
import java.io.PrintStream;

public abstract class EndPoint {
    protected final String baseUrl = "https://conduit.productionready.io/api";
    protected RequestSpecification request;
    protected RequestSpecification requestSpecification;
    protected Response response;
    protected JsonPath jsonPath;
    protected String token;
    protected final String GET = "GET";
    protected final String POST = "POST";
    protected final String PUT = "PUT";
    protected final String PATCH = "PATCH";
    protected final String DELETE = "DELETE";

    public EndPoint() {
        assignLog();
    }

    public EndPoint(String token) {
        this();
        this.token = token;
    }

    protected void createNewRequest() {
        request = RestAssured.given().spec(requestSpecification);
    }

    protected void assignHeader(String key, String value) {
        request.header(key, value);
    }

    protected void assignBodyParameter(Model model) {
        request.body(model);
    }

    protected void assignToken(){
        Log.info("Assigning the token to the header");
        assignHeader("Authorization", "Token " + token);
    }

    public boolean verifyStatusCode(int statusCode) {
        Log.info("Verifying the status code");
        try {
            response.then().assertThat().statusCode(statusCode);
            return true;
        } catch (Exception e) {
            Log.error("Unexpected status code");
            e.printStackTrace();
            return false;
        }
    }

    public boolean verifySchema(String path) {
        Log.info("Verifying the schema");
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(path));
            return true;
        } catch (Exception e) {
            Log.error("Schema validation failed");
            e.printStackTrace();
            return false;
        }
    }

    public String getFieldFromResponse(String path) {
        jsonPath = responseToJsonPath(response);
        return jsonPath.getString(path);
    }

    private JsonPath responseToJsonPath(Response response) {
        String responseString = response.then().extract().response().asString();
        return new JsonPath(responseString);
    }

    private void assignLog() {
        String logPath = "src/test/resources/logs/restAssuredLogs.log";
        try {
            Log.info("Setting the log");

            PrintStream log = new PrintStream(new FileOutputStream(logPath));

            Log.info("Create request specification");
            requestSpecification = new RequestSpecBuilder()
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setBaseUri(baseUrl)
                    .setContentType(ContentType.JSON)
                    .setAccept(ContentType.JSON)
                    .build();
        } catch (Exception e){
            e.printStackTrace();
            Log.error("Failed initializing the logs");
        }
    }

    protected abstract void apiCallManager(String resource, String method);
}
