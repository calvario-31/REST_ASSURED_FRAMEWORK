package endpoints.tags;

import endpoints.EndPoint;
import utilities.Log;

public class TagsEndPoint extends EndPoint {
    private final String tagsPath = "tags";

    public TagsEndPoint() {
        super();
    }

    public void getTags() {
        createNewRequest();
        Log.info("Calling get tags endpoint");
        apiCallManager(tagsPath, GET);
    }
}
