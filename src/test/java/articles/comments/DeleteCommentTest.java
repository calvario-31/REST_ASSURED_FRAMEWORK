package articles.comments;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.comments.CommentsEndPoint;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.JsonPayloadProvider;

public class DeleteCommentTest extends Base {
    private CommentsEndPoint commentsEndPoint;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        initEndPoints();
    }

    @Test(dataProvider = "comment data", groups = {"smoke"})
    public void testName(String payload) {
        commentsEndPoint.createComment(payload);
        String commentId = commentsEndPoint.getCommentId();
        commentsEndPoint.deleteComment(commentId);

        Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(commentsEndPoint.getResponseTime() < 8000L);
        softAssert.assertAll();
    }

    @Override
    public void initEndPoints() {
        String articleId = new ArticlesEndPoint(token).generateArticleId();
        commentsEndPoint = new CommentsEndPoint(token, articleId);
    }

    @DataProvider(name = "comment data")
    public Object[][] deleteCommentDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getCommentJson()}
        };
    }
}
