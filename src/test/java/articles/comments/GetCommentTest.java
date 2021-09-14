package articles.comments;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.comments.CommentsEndPoint;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;

import static utilities.SchemaProvider.getAllCommentsSchemaPath;

public class GetCommentTest extends Base {
    private CommentsEndPoint commentsEndPoint;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "comment data", groups = {"smoke"})
    public void getCommentTest(String schemaJsonPath) {
        commentsEndPoint.getComments();

        Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(commentsEndPoint.getResponseTime() < 8000L);
        softAssert.assertTrue(commentsEndPoint.verifySchema(schemaJsonPath));
        softAssert.assertAll();
    }

    @Override
    public void initEndPoints() {
        String articleId = new ArticlesEndPoint(token).generateArticleId();
        commentsEndPoint = new CommentsEndPoint(token, articleId);
    }

    @DataProvider(name = "comment data")
    public Object[][] getCommentDataProvider() {
        return new Object[][]{
                {getAllCommentsSchemaPath()}
        };
    }
}
