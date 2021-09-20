package articles.comments;

import endpoints.articles.CommentsEndPoint;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Commons;

public class DeleteCommentTest extends Base {
    private CommentsEndPoint commentsEndPoint;
    private String articleId;
    private int commentId;

    @Test(groups = {"regression"})
    public void testName() {
        commons = new Commons();
        token = commons.generateNewUser().getToken();
        articleId = commons.generateNewArticle(token).getSlug();
        commentId = commons.generateComment(token, articleId).getId();

        commentsEndPoint = new CommentsEndPoint(token);
        commentsEndPoint.deleteComment(articleId, commentId);

        Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(commentsEndPoint.getResponseTime() < 8000L);
        softAssert.assertAll();
    }
}
