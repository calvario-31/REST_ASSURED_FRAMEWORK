package endpoints.tags;

import endpoints.EndPoint;
import models.tags.TagsModel;
import utilities.Log;

public class TagsEndPoint extends EndPoint {
    private final String tagsPath = "tags";

    public TagsEndPoint() {
        super();
    }

    public TagsModel getTags() {
        createNewRequest();
        Log.info("Calling get tags endpoint");
        apiCallManager(tagsPath, GET);
        return getTagsResponseBodyAsModel();
    }

    private TagsModel getTagsResponseBodyAsModel() {
        return getResponseBody().as(TagsModel.class);
    }
}
