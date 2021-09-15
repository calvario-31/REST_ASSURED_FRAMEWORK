package endpoints.articles;

import endpoints.EndPoint;
import models.Model;
import models.articles.GetAllArticlesModel;
import models.articles.GetArticleModel;
import utilities.Log;
import utilities.endpointhelpers.JsonPayloadProvider;

public class ArticlesEndPoint extends EndPoint {
    private final String articlePath = "articles";
    private final String articleIdPath = "articles/{articleId}";

    public ArticlesEndPoint(String token) {
        super(token);
    }

    public GetArticleModel createArticle(Model payload) {
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

    public GetArticleModel getArticle(String articleId) {
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

    public GetArticleModel updateArticle(String articleId, Model payload) {
        createNewRequest();
        assignArticleId(articleId);
        assignBodyParameter(payload);
        Log.info("Calling put articles endpoint");
        apiCallManager(articleIdPath, PUT);
        return getArticleResponseBodyAsModel();
    }

    public GetArticleModel generateNewArticle() {
        return createArticle(new JsonPayloadProvider().getArticleJson());
    }

    private void assignArticleId(String articleId) {
        assignPathParameter("articleId", articleId);
    }

    private GetArticleModel getArticleResponseBodyAsModel() {
        return getResponseBody().as(GetArticleModel.class);
    }

    private GetAllArticlesModel getAllArticlesResponseBodyAsModel() {
        return getResponseBody().as(GetAllArticlesModel.class);
    }
}
