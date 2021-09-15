package articles;

import endpoints.articles.ArticlesEndPoint;
import endpoints.articles.CommentsEndPoint;
import models.articles.ArticleResponseModel;
import models.articles.CommentResponseModel;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.Base;
import utilities.endpointhelpers.JsonPayloadProvider;

import java.util.ArrayList;
import java.util.List;

public class ArticleEndToEndTest extends Base {
    private ArticlesEndPoint articlesEndPoint;
    private CommentsEndPoint commentsEndPoint;
    private CommentResponseModel commentResponseModel;
    private ArticleResponseModel articleResponse;

    @Test(dataProvider = "article data", groups = {"smoke"})
    public void articleEndToEndTest(String payloadUpdateArticle) {
        articlesEndPoint = new ArticlesEndPoint(token);
        //create
        articleResponse = articlesEndPoint.generateNewArticle();
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        //update
        articleResponse = articlesEndPoint.updateArticle(articleResponse.getSlug(), payloadUpdateArticle);
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        //get
        articlesEndPoint.getArticle(articleResponse.getSlug());
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        //get all
        articlesEndPoint.getAllArticles();
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
        //create comment
        commentsEndPoint = new CommentsEndPoint(token);

        List<Integer> createdIdListed = new ArrayList<>();
        for(int i =0; i<10; i++) {
            commentResponseModel = commentsEndPoint.generateComment(articleResponse.getSlug());
            Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
            createdIdListed.add(commentResponseModel.getId());
        }

        //get all comments
        commentsEndPoint.getComments(articleResponse.getSlug());
        Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        //delete comment
        for(int id: createdIdListed) {
            commentsEndPoint.deleteComment(articleResponse.getSlug(), id);
            Assert.assertTrue(commentsEndPoint.verifyStatusCode(200));
        }
        //delete article
        articlesEndPoint.deleteArticle(articleResponse.getSlug());
        Assert.assertTrue(articlesEndPoint.verifyStatusCode(200));
    }

    @DataProvider(name = "article data")
    public Object[][] createArticleDataProvider() {
        JsonPayloadProvider jsonPayloadProvider = new JsonPayloadProvider();
        return new Object[][]{
                {jsonPayloadProvider.getArticleJson()}
        };
    }
}
