package endpoints.articles;

import endpoints.EndPoint;
import utilities.JsonPayloadProvider;
import utilities.Log;

public class ArticlesEndPoint extends EndPoint {
    private final String articlePath = "articles";
    private final String articleIdPath = "articles/{articleId}";

    public ArticlesEndPoint(String token) {
        super(token);
    }

    public void createArticle(String payload) {
        createNewRequest();
        assignBodyParameter(payload);
        Log.info("Calling create article endpoint");
        apiCallManager(articlePath, POST);
    }

    public void deleteArticle(String articleId) {
        createNewRequest();
        assignArticleId(articleId);
        Log.info("Calling delete article endpoint");
        apiCallManager(articleIdPath, DELETE);
    }

    public void getArticle(String articleId) {
        createNewRequest();
        assignArticleId(articleId);
        Log.info("Calling get article endpoint");
        apiCallManager(articleIdPath, GET);
    }

    public void getAllArticles() {
        createNewRequest();
        Log.info("Calling get all articles endpoint");
        apiCallManager(articlePath, GET);
    }

    public void updateArticle(String payload, String articleId) {
        createNewRequest();
        assignArticleId(articleId);
        assignBodyParameter(payload);
        Log.info("Calling put articles endpoint");
        apiCallManager(articleIdPath, PUT);
    }

    public String getArticleId() {
        return getFieldFromResponse("article.slug");
    }

    public String generateArticleId() {
        createArticle(new JsonPayloadProvider().getArticleJson());
        return getArticleId();
    }

    private void assignArticleId(String articleId) {
        assignPathParameter("articleId", articleId);
    }
}
