package articles.comments;

import endpoints.articles.CommentsEndPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;
import utilities.endpointhelpers.JsonPayloadProvider;

import static utilities.endpointhelpers.SchemaProvider.getCommentSchemaPath;

public class CreateCommentTest extends Base {
    private CommentsEndPoint commentsEndPoint;
    private String articleId;

    @Test(dataProvider = "comment data", groups = {"regression"})
    public void createCommentTest(String payload, String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        articleId = commons.generateNewArticle(token).getSlug();

        commentsEndPoint = new CommentsEndPoint(token);
        commentsEndPoint.createComment(articleId, payload);

        Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(commentsEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(commentsEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "comment data")
    public Object[][] getCommentDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getCommentJson(), getCommentSchemaPath()}
        };
    }
}
