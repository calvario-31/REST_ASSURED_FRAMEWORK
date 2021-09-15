package articles.comments;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.CommentsEndPoint;
import models.articles.ArticleResponseModel;
import models.articles.CommentResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.endpointhelpers.JsonPayloadProvider;

public class DeleteCommentTest extends Base {
    private CommentsEndPoint commentsEndPoint;
    private CommentResponseModel commentResponse;
    private ArticleResponseModel newArticleResponse;

    @Test(dataProvider = "comment data", groups = {"regression"})
    public void testName(String payload) {
        newArticleResponse = new ArticlesEndPoint(token).generateNewArticle().getArticle();
        commentsEndPoint = new CommentsEndPoint(token);
        commentResponse = commentsEndPoint.createComment(newArticleResponse.getSlug(), payload).getComment();

        commentsEndPoint.deleteComment(newArticleResponse.getSlug(), commentResponse.getId());

        Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        softAssert = new SoftAssert();
        softAssert.assertTrue(commentsEndPoint.getResponseTime() < 8000L);
        softAssert.assertAll();
    }

    @DataProvider(name = "comment data")
    public Object[][] deleteCommentDataProvider() {
        return new Object[][]{
                {new JsonPayloadProvider().getCommentJson()}
        };
    }
}
