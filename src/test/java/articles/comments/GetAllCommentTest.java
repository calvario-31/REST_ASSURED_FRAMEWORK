package articles.comments;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.CommentsEndPoint;
import models.articles.ArticleResponseModel;
import models.articles.CommentResponseModel;
import models.articles.GetAllCommentsModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.Base;
import utilities.Log;

import static utilities.endpointhelpers.SchemaProvider.getAllCommentsSchemaPath;

public class GetAllCommentTest extends Base {
    private CommentsEndPoint commentsEndPoint;
    private ArticleResponseModel newArticleResponse;
    private GetAllCommentsModel allCommentsResponse;

    @Test(dataProvider = "comment data", groups = {"regression"})
    public void getCommentTest(String schemaJsonPath) {
        newArticleResponse = new ArticlesEndPoint(token).generateNewArticle().getArticle();
        commentsEndPoint = new CommentsEndPoint(token);

        for(int i =0; i<10; i++) {
            commentsEndPoint.generateComment(newArticleResponse.getSlug());
        }

        allCommentsResponse = commentsEndPoint.getComments(newArticleResponse.getSlug());

        for(CommentResponseModel comment: allCommentsResponse.getComments()) {
            Log.debug(comment.getBody());
        }

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
