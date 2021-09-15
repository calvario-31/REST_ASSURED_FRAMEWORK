package endpoints;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import models.Model;
import utilities.Log;
import utilities.endpointhelpers.RequestFilter;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public abstract class EndPoint {
    protected final String GET = "GET";
    protected final String POST = "POST";
    protected final String PUT = "PUT";
    protected final String PATCH = "PATCH";
    protected final String DELETE = "DELETE";
    private final String baseUrl = "https://conduit.productionready.io/api";
    private RequestSpecification request;
    private RequestSpecification requestSpecification;
    private Response response;
    private JsonPath jsonPath;
    private String token;

    public EndPoint() {
        assignLog();
        createNewRequest();
    }

    public EndPoint(String token) {
        this();
        this.token = token;
    }

    public void setToken(String token) {
        Log.info("Assigning the token to the header");
        assignHeader("Authorization", "Token " + token);
    }

    protected void createNewRequest() {
        request = RestAssured
                .given()
                .filter(new RequestFilter())
                .spec(requestSpecification);
        if (token != null) {
            setToken(token);
        }
    }

    protected void assignHeader(String key, String value) {
        request.header(key, value);
    }

    protected void assignBodyParameter(String model) {
        request.body(model);
    }

    protected void assignPathParameter(String key, String path) {
        request.pathParam(key, path);
    }

    protected ResponseBody getResponseBody() {
        return response.getBody();
    }

    protected List<Model> getResponseBodyList(String path) {
        return response.jsonPath().getList(path);
    }

    protected void apiCallManager(String url, String method) {
        switch (method) {
            case GET:
                response = request.get(url);
                break;
            case POST:
                response = request.post(url);
                break;
            case PUT:
                response = request.put(url);
                break;
            case PATCH:
                response = request.patch(url);
                break;
            case DELETE:
                response = request.delete(url);
                break;
            default:
                response = null;
                Log.error("Bad method name");
        }
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

    public long getResponseTime() {
        Log.info("Verifying the response time");
        return response.getTime();
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
        } catch (Exception e) {
            e.printStackTrace();
            Log.error("Failed initializing the logs");
        }
    }
}
