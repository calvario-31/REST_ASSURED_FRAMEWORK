package endpoints.articles.comments;

import endpoints.EndPoint;
import utilities.Log;

public class CommentsEndPoint extends EndPoint {
    private final String commentPath = "articles/{articleId}/comments";
    private final String commentIdPath = "articles/{articleId}/comments/{idComment}";
    private final String articleId;

    public CommentsEndPoint(String token, String articleId) {
        super(token);
        this.articleId = articleId;
    }

    public void getComments() {
        createNewRequest();
        assignArticleId();
        Log.info("Calling get comments endpoint");
        apiCallManager(commentPath, GET);
    }

    public void createComment(String payload) {
        createNewRequest();
        assignArticleId();
        assignBodyParameter(payload);
        Log.info("Calling post comments endpoint");
        apiCallManager(commentPath, POST);
    }

    public void deleteComment(String idComment) {
        createNewRequest();
        assignArticleId();
        assignIdComment(idComment);
        Log.info("Calling delete comment endpoint");
        apiCallManager(commentIdPath, DELETE);
    }

    public String getCommentId() {
        return getFieldFromResponse("comment.id");
    }

    private void assignArticleId() {
        assignPathParameter("articleId", articleId);
    }

    private void assignIdComment(String idComment) {
        assignPathParameter("idComment", idComment);
    }
}
