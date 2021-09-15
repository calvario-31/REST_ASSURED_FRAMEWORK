package articles.comments;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.CommentsEndPoint;
import models.Model;
import models.articles.ArticleResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.endpointhelpers.JsonPayloadProvider;

import static utilities.endpointhelpers.SchemaProvider.getCommentSchemaPath;

public class CreateCommentTest extends Base {
    private CommentsEndPoint commentsEndPoint;
    private ArticleResponseModel newArticleResponse;

    @Test(dataProvider = "comment data", groups = {"smoke"})
    public void createCommentTest(Model payload, String schemaJsonPath) {
        newArticleResponse = new ArticlesEndPoint(token).generateNewArticle().getArticle();
        commentsEndPoint = new CommentsEndPoint(token);

        commentsEndPoint.createComment(newArticleResponse.getSlug(), payload);

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
