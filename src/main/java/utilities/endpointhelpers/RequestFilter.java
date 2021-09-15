package utilities.endpointhelpers;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.internal.support.Prettifier;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import utilities.Log;

public class RequestFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        Response response = ctx.next(requestSpec, responseSpec);

        String token = requestSpec.getHeaders().getValue("Authorization");
        if (token == null) {
            token = "--";
        }

        String payload = new Prettifier().getPrettifiedBodyIfPossible(requestSpec);

        Log.printRequest(requestSpec.getURI(),
                requestSpec.getMethod(),
                requestSpec.getContentType(),
                token,
                payload,
                response.getStatusCode(),
                response.getTime(),
                response.getBody().asPrettyString());
        return response;
    }
}
