package articles;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.CommentsEndPoint;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.Base;
import utilities.Commons;
import utilities.endpointhelpers.JsonPayloadProvider;

import java.util.ArrayList;
import java.util.List;

public class ArticleEndToEndTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private CommentsEndPoint commentsEndPoint;
    private String articleId;

    @Test(dataProvider = "article data", groups = {"smoke"})
    public void articleEndToEndTest(String payloadCreateArticle, String payloadUpdateArticle, String payloadComment) {
        commons = new Commons();
        token = commons.generateNewUser().getToken();

        articlesEndPoint = new ArticlesEndPoint(token);
        //create
        articleId = articlesEndPoint.createArticle(payloadCreateArticle).getSlug();
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        //update
        articlesEndPoint.updateArticle(articleId, payloadUpdateArticle);
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        //get
        articlesEndPoint.getArticle(articleId);
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        //get all
        articlesEndPoint.getAllArticles();
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        //create comment
        commentsEndPoint = new CommentsEndPoint(token);

        List<Integer> createdIdListed = new ArrayList<>();
        for(int i =0; i<10; i++) {
            int commentId = commentsEndPoint.createComment(articleId, payloadComment).getId();
            Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
            createdIdListed.add(commentId);
        }

        //get all comments
        commentsEndPoint.getComments(articleId);
        Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        //delete comment
        for(int id: createdIdListed) {
            commentsEndPoint.deleteComment(articleId, id);
            Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        }
        //delete article
        articlesEndPoint.deleteArticle(articleId);
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
    }

    @DataProvider(name = "article data")
    public Object[][] createArticleDataProvider() {
        JsonPayloadProvider jsonPayloadProvider = new JsonPayloadProvider();
        return new Object[][]{
                {jsonPayloadProvider.getArticleJson(), jsonPayloadProvider.getArticleJson(),
                        jsonPayloadProvider.getCommentJson()}
        };
    }
}
