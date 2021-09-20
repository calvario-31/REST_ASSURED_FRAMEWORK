package articles.comments;

import endpoints.articles.CommentsEndPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;

import static utilities.endpointhelpers.SchemaProvider.getAllCommentsSchemaPath;

public class GetAllCommentTest extends Base {
    private CommentsEndPoint commentsEndPoint;
    private String articleId;

    @Test(dataProvider = "comment data", groups = {"regression"})
    public void getCommentTest(String schemaJsonPath) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        articleId = commons.generateNewArticle(token).getSlug();

        for(int i =0; i<10; i++) {
            commons.generateComment(token, articleId);
        }

        commentsEndPoint = new CommentsEndPoint(token);
        commentsEndPoint.getComments(articleId);

        Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(commentsEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(commentsEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @DataProvider(name = "comment data")
    public Object[][] getCommentDataProvider() {
        return new Object[][]{
                {getAllCommentsSchemaPath()}
        };
    }
}
