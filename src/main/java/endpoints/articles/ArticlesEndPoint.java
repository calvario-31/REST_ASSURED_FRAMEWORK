package endpoints.articles;

import endpoints.EndPoint;
import models.articles.ArticleResponseModel;
import models.articles.GetAllArticlesModel;
import models.articles.GetArticleModel;
import utilities.Log;

public class ArticlesEndPoint extends EndPoint {
    private final String articlePath = "articles";
    private final String articleIdPath = "articles/{articleId}";

    public ArticlesEndPoint(String token) {
        super(token);
    }

    public ArticleResponseModel createArticle(String payload) {
        createNewRequest();
        assignBodyParameter(payload);
        Log.info("Calling create article endpoint");
        apiCallManager(articlePath, POST);
        return getArticleResponseBodyAsModel();
    }

    public void deleteArticle(String articleId) {
        createNewRequest();
        assignArticleId(articleId);
        Log.info("Calling delete article endpoint");
        apiCallManager(articleIdPath, DELETE);
    }

    public ArticleResponseModel getArticle(String articleId) {
        createNewRequest();
        assignArticleId(articleId);
        Log.info("Calling get article endpoint");
        apiCallManager(articleIdPath, GET);
        return getArticleResponseBodyAsModel();
    }

    public GetAllArticlesModel getAllArticles() {
        createNewRequest();
        Log.info("Calling get all articles endpoint");
        apiCallManager(articlePath, GET);
        return getAllArticlesResponseBodyAsModel();
    }

    public ArticleResponseModel updateArticle(String articleId, String payload) {
        createNewRequest();
        assignArticleId(articleId);
        assignBodyParameter(payload);
        Log.info("Calling put articles endpoint");
        apiCallManager(articleIdPath, PUT);
        return getArticleResponseBodyAsModel();
    }

    private void assignArticleId(String articleId) {
        assignPathParameter("articleId", articleId);
    }

    private ArticleResponseModel getArticleResponseBodyAsModel() {
        return getResponseBody().as(GetArticleModel.class).getArticle();
    }

    private GetAllArticlesModel getAllArticlesResponseBodyAsModel() {
        return getResponseBody().as(GetAllArticlesModel.class);
    }
}
